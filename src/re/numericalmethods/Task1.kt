package re.numericalmethods

class Task1 {

    private fun f(x: Double) = (1.0 / Math.sqrt(x)) * Math.sin(Math.sqrt(x))

    private fun sum(x: Double, e: Double) : Double {
        var current = 1.0
        var result = 1.0
        var k = 0

        do {
            current *= - x / ((2 * k + 2) * (2 * k + 3))
            result += current
            k++
        } while (Math.abs(current) > e)

        return result - current
    }

    private fun rightDerivative(f: Double, fnext: Double, h: Double) : Double = (fnext - f) / h

    private fun leftDerivative(fprev: Double, f: Double, h: Double) : Double = (f - fprev) / h

    private fun centralDerivative(fprev: Double, fnext: Double, h: Double) : Double = (fnext - fprev) / (2 * h)

    private fun getCorrectDouble(value: Double) = Math.round(value * 1000.0) / 1000.0

    fun printAsTable(values: MutableMap<Double, MutableList<Double>>) {
        val firstKey = values.keys.first()
        val lastKey = values.keys.last()

        println("x\t\t f(x)\t\t sum(x)\t\t f+(x)\t\t f-(x)\t\t f+-(x)")

        println("$firstKey\t\t ${getCorrectDouble(values[firstKey]!![0])}\t\t " +
                "${getCorrectDouble(values[firstKey]!![1])}\t\t " +
                "-\t\t\t -\t\t\t -")

        values.filter { x -> x.value.size == 5 }.forEach {
            print("${it.component1()}\t\t ")
            it.value.forEach {
                print("${getCorrectDouble(it)}\t\t ")
            }
            println()
        }

        println("$lastKey\t\t ${getCorrectDouble(values[lastKey]!![0])}\t\t " +
                "${getCorrectDouble(values[lastKey]!![1])}\t\t " +
                "-\t\t\t -\t\t\t -")

    }

    fun fillFuncValuesMap(a: Double, b: Double, h: Double, e: Double) : MutableMap<Double, MutableList<Double>> {
        val funcValues: MutableMap<Double, MutableList<Double>> = mutableMapOf()
        var x = a

        while (x < b + h) {
            funcValues.put(x, mutableListOf())
            funcValues[x]?.add(this.sum(x, e))
            funcValues[x]?.add(this.f(x))

            x = getCorrectDouble(x + h)
        }

        x = getCorrectDouble(a + h)
        while (x < b) {
            funcValues[x]?.add(this.rightDerivative(
                    funcValues[x]!![0],
                    funcValues[getCorrectDouble(x + h)]!![0],
                    h
            ))
            funcValues[x]?.add(this.leftDerivative(
                    funcValues[getCorrectDouble(x - h)]!![0],
                    funcValues[x]!![0],
                    h
            ))
            funcValues[x]?.add(this.centralDerivative(
                    funcValues[getCorrectDouble(x - h)]!![0],
                    funcValues[getCorrectDouble(x + h)]!![0],
                    h
            ))

            x = getCorrectDouble(x + h)
        }

        return funcValues
    }

}

fun main(args: Array<String>) {
    val task = Task1()
    val values = task.fillFuncValuesMap(
            a = 0.0,
            b = 1.7,
            h = 0.1,
            e = 0.0001
    )
    task.printAsTable(values)

}