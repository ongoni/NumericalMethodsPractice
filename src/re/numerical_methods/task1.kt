package re.numerical_methods

class task1 {

    fun f(x: Double) = (1.0 / x) * Math.sin(x)

    fun summ(x: Double, e: Double) : Double {
        var current = 1.0
        var result = 1.0
        var k = 0
        do {
            current *= - x * x / ((2 * k + 2) * (2 * k + 3))
            result += current
            k++
        } while (Math.abs(current) > e)
        return result - current
    }

    fun left(x: Double) {

    }

    //fun show(x: Double, sumx: Double, fx: Double, )
}

fun main(args: Array<String>) {
    val task = task1()
    val a = 0.0
    val b = 1.7
    val e = 0.0001
    val h = 0.1
    var x = a
    var funcValues: MutableMap<Double, MutableList<Double>> = mutableMapOf()
    while (x <= b) {
        funcValues.put(x, mutableListOf())
        funcValues[x]?.add(task.f(x))
        funcValues[x]?.add(task.summ(x, e))
        if (x > a && x < b) {
            funcValues[x]?.add(task.f1(x))
        }
        x += h
    }
}