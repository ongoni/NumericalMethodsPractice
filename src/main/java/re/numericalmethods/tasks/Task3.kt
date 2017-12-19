package re.numericalmethods.tasks

import re.numericalmethods.utils.DoubleValuesUtils

class Task3: DoubleValuesUtils {

    private fun f(x: Double) = x * Math.cos(x)

    private fun linerInterpolationPolynom(x: Double, prevX: Double, nextX: Double, prevF: Double, nextF: Double) =
            ((x - nextX) / (prevX - nextX)) * prevF + ((x - prevX) / (nextX - prevX)) * nextF

    public fun findPair(value: Double, values: MutableMap<Double, Double>) : Pair<Double?, Double?> =
            Pair(
                values.keys.firstOrNull { x -> x <= value } ?: values.keys.min(),
                values.keys.lastOrNull { x -> x > value } ?: values.keys.max()
            )

    private fun printHeader(with: String) {
        println("x $with f(x) $with L(x') $with f(x')")
    }

    private fun printTableRow(argument: Double, polynomValue: Double, functionValue: Double, with: String) {
        println("${argument.format(8)} $with ${f(argument).format(8)} $with ${polynomValue.format(8)} $with ${functionValue.format(8)}")
    }

    private fun linearInterpolation(a: Double, b: Double, n: Int) {
        val functionValues = mutableMapOf<Double, Double>()

        var arg: Double
        (0..n).forEach {
            arg = getCorrectDouble(
                    ((a + b) / 2) + ((b - a) / 2) * Math.cos((((2 * it + 1) * Math.PI) / (2 * (n + 1)))),
                    8
            )
            functionValues.put(
                    arg,
                    getCorrectDouble(f(arg), 8)
            )
        }

        var pair: Pair<Double?, Double?>
        printHeader("\t\t\t\t")
        (0..n).forEach {
            arg = getCorrectDouble(
                    ((a + b) / 2) + ((b - a) / 2) * Math.cos((((2 * it + 1) * Math.PI) / (2 * (n + 1)))),
                    8
            )
            pair = findPair(arg, functionValues)
//            println(pair)
            printTableRow(
                    arg,
                    linerInterpolationPolynom(
                            (pair.first!! + pair.second!!) / 2,
                            pair.first!!,
                            pair.second!!,
                            functionValues[pair.first!!]!!,
                            functionValues[pair.second!!]!!
                    ),
                    f((pair.first!! + pair.second!!) / 2),
                    "\t\t"
            )
        }
    }

    fun run() {
        print("enter n: ")
        val n = readLine()!!.toInt()
        val a = 0.0
        val b = 2 * Math.PI

        linearInterpolation(a, b, n)

    }

}