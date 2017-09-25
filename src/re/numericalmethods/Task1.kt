package re.numericalmethods

class Task1 {

    fun f(x: Double) = (1.0 / Math.sqrt(x)) * Math.sin(Math.sqrt(x))

    fun sum(x: Double, e: Double) : Double {
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

    fun right(f: Double, fnext: Double, h: Double) : Double = (fnext - f) / h

    fun left(fprev: Double, f: Double, h: Double) : Double = (f - fprev) / h

    fun central(fprev: Double, fnext: Double, h: Double) : Double = (fnext - fprev) / (2 * h)

}

fun main(args: Array<String>) {
    val a = 0.0; val b = 1.7; val h = 0.1
    val e = 0.0001
    var x = a

    val task = Task1()
    val funcValues: MutableMap<Double, MutableList<Double>> = mutableMapOf()

    while (x <= b) {
        funcValues.put(x, mutableListOf())
        funcValues[x]?.add(task.sum(x, e))
        funcValues[x]?.add(task.f(x))

        x += h
    }

    x = a + h
    while (x < b) {
        funcValues[x]?.add(task.right(
                funcValues[x]!![0],
                funcValues[x + h]!![0],
                h
        ))
        funcValues[x]?.add(task.left(
                funcValues[x - h]!![0],
                funcValues[x]!![0],
                h
        ))
        funcValues[x]?.add(task.central(
                funcValues[x - h]!![0],
                funcValues[x + h]!![0],
                h
        ))
    }

}