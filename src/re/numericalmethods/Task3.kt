package re.numericalmethods

class Task3: DoubleValuesUtils {

    private fun f(x: Double) = x * Math.cos(x)

    private fun linearInterpolation(x: Double, prevX: Double, nextX: Double, prevF: Double, nextF: Double) =
            ((x - nextX) / (prevX - nextX)) * prevF + ((x - prevX) / (nextX - prevX)) * nextF

    private fun findPair(value: Double, values: MutableMap<Double, Double>) =
        Pair(
                values.keys.first { x -> x <= value },
                values.keys.first { x -> x > value }
        )

    private fun firstMethod(a: Double, b: Double, n: Int) {
        val functionValues = mutableMapOf<Double, Double>()
        val h = (b - a) / n

        var arg: Double
        (0..50).forEach {
            arg = getCorrectDouble(it * Math.PI / 13, 8)
            functionValues.put(arg, getCorrectDouble(f(arg), 8))
        }

        var pair: Pair<Double, Double>
        (0..n).forEach {
            arg = getCorrectDouble(a + it * h, 8)
            pair = findPair(arg, functionValues)
            println("L($arg) = ${linearInterpolation(
                    arg,
                    pair.first,
                    pair.second,
                    functionValues[pair.first]!!,
                    functionValues[pair.second]!!)
            }")
        }
    }

    fun run() {
        print("enter n: ")
        val n = readLine()!!.toInt()
//        print("enter N: ")
//        val N = readLine()!!.toInt()
        val a = 0.0; val b = 2 * Math.PI

        firstMethod(a, b, n)

    }

}