package re.numericalmethods

class Task1 : DoubleValuesUtils {

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

    private fun leftDerivative(fprev: Double, f: Double, h: Double) : Double = (f - fprev) / h

    private fun rightDerivative(f: Double, fnext: Double, h: Double) : Double = (fnext - f) / h

    private fun centralDerivative(fprev: Double, fnext: Double, h: Double) : Double = (fnext - fprev) / (2 * h)

    private fun printHeader(with: String) {
        println("x$with sum(x)$with f(x)$with f-(x)$with f+(x)$with f+-(x)")
    }

    private fun printTableRow(key: Double, values: MutableList<Double>, with: String) {
        print("$key$with ")
        values.forEach {
            print("${it.format(5)}$with ")
        }
        println()
    }

    fun printAsTable(values: MutableMap<Double, MutableList<Double>>) {
        printHeader("\t\t\t")
        values.forEach {
            printTableRow(it.key, it.value, "\t\t  ")
        }
    }

    fun fillFuncValuesMap(a: Double, b: Double, h: Double, e: Double) : MutableMap<Double, MutableList<Double>> {
        val funcValues: MutableMap<Double, MutableList<Double>> = mutableMapOf()
        var x = a

        while (x < b + h) {
            funcValues.put(x, mutableListOf())
            funcValues[x]?.add(this.sum(x, e))
            funcValues[x]?.add(this.f(x))

            x = getCorrectDouble(x + h, 2)
        }

        x = getCorrectDouble(a + h, 2)
        while (x < b) {
            funcValues[x]?.add(this.leftDerivative(
                    funcValues[getCorrectDouble(x - h, 2)]!![0],
                    funcValues[x]!![0],
                    h
            ))
            funcValues[x]?.add(this.rightDerivative(
                    funcValues[x]!![0],
                    funcValues[getCorrectDouble(x + h, 2)]!![0],
                    h
            ))
            funcValues[x]?.add(this.centralDerivative(
                    funcValues[getCorrectDouble(x - h, 2)]!![0],
                    funcValues[getCorrectDouble(x + h, 2)]!![0],
                    h
            ))

            x = getCorrectDouble(x + h, 2)
        }

        return funcValues
    }

}