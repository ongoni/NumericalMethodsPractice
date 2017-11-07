package re.numericalmethods

class Task3: DoubleValuesUtils {

    private fun f(x: Double) = x * Math.cos(x)

    private fun interpolationPolynom(x: Double, prevX: Double, nextX: Double, prevF: Double, nextF: Double) =
            ((x - nextX) / (prevX - nextX)) * prevF + ((x - prevX) / (nextX - prevX)) * nextF

    private fun findPair(value: Double, values: MutableMap<Double, Double>) =
        Pair(
                values.keys.firstOrNull { x -> x <= value } ?: values.keys.min(),
                values.keys.firstOrNull { x -> x > value } ?: values.keys.max()
        )

    private fun printHeader(with: String) {
        println("x $with f(x) $with L(x)")
    }

    private fun printTableRow(argument: Double, polynomValue: Double, with: String) {
        println("${argument.format(8)} $with ${f(argument).format(8)} $with ${polynomValue.format(8)}")
    }

    private fun firstMethod(a: Double, b: Double, n: Int) {
        val functionValues = mutableMapOf<Double, Double>()

        var arg: Double
        (0..n).forEach {
            arg = getCorrectDouble(
                    ((a + b) / 2) + ((b - a) / 2) * Math.cos((((2 * it + 1) * Math.PI) / (2 * (n + 1)))),
                    8
            )
            functionValues.put(
                    arg,
                    f(arg)
            )
        }

        var pair: Pair<Double?, Double?>
        printHeader("\t\t\t\t")
        (0..n).forEach {
            arg = getCorrectDouble(a + it * ((b - a) / n), 8)
            pair = findPair(arg, functionValues)
            printTableRow(
                    arg,
                    interpolationPolynom(
                            arg,
                            pair.first!!,
                            pair.second!!,
                            functionValues[pair.first!!]!!,
                            functionValues[pair.second!!]!!
                    ),
                    "\t\t"
            )
        }
    }

    fun run() {
        print("enter n: ")
        val n = readLine()!!.toInt()
        val a = 0.0
        val b = 2 * Math.PI

        firstMethod(a, b, n)

    }

}