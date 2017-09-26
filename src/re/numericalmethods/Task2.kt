package re.numericalmethods

class Task2 : DoubleValuesUtils {

    private fun f(x: Double) = Math.exp(2 * x) - 2 * Math.sin(x)

    fun run() {
        val a = - Math.PI / 2; val b = 0
        print("enter n: ")
        val n = readLine()!!.toInt()

        val h = getCorrectDouble((b - a) / n, 10)
    }

}