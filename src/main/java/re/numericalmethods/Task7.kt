package re.numericalmethods

class Task7: DoubleValuesUtils {

    private val N = 10

    private fun tridiagonalMatrix(h: Double, y0: Double, yn: Double, xN: ArrayList<Double>): ArrayList<Double> {
        //-y'' - 2xy' - 2y = -4x, y(0) = 1, y(0.5) = 1.279
        val p = (0..N).map { getCorrectDouble(-2.0 * xN[it], 4) }
        val q = (0 until N).map { -2.0 }
        val r = (0..N).map { getCorrectDouble(-4.0 * xN[it], 4) }

        val a = (0 until N).map { 1.0 }.toDoubleArray()
        val b = (0 until N).map { 1.0 }.toDoubleArray()
        val c = (0 until N).map { 1.0 }.toDoubleArray()
        val f = (0 until N).map { 0.0 }.toDoubleArray()

        for (i in 0 until N) {
            a[i] = getCorrectDouble(1.0 / 2.0 * (1.0 + h / 2.0 * p[i]), 4)
            b[i] = getCorrectDouble(1.0 / 2.0 * (1.0 - h / 2.0 * p[i]), 4)
            c[i] = getCorrectDouble((1.0 + h * h / 2.0 * q[i]), 4)
            f[i] = getCorrectDouble(h * h / 2.0 * r[i], 4)
        }
        f[N - 1] = yn
        f[0] = y0

        val alpha = (0 until N).map { 0.0 }.toDoubleArray()
        val beta =  (0 until N).map{ f[0] }.toDoubleArray()
        for (j in 0 until N - 1) {
            alpha[j + 1] = getCorrectDouble(b[j] / (c[j] - a[j] * alpha[j]), 4)
            beta[j + 1] = getCorrectDouble((a[j] * beta[j] + f[j]) / (c[j] - a[j] * alpha[j]), 4)
        }

        val y = ArrayList((0 until N).map { 0.0 }.toList())
        y[N - 1] = yn
        y[0] = y0
        println(alpha.toList())
        println(beta.toList())
        for (j in N - 1 downTo 1) y[j - 1] = getCorrectDouble(alpha[j] * y[j] + beta[j], 4)

        val err = (1 until N - 1).map { getCorrectDouble(a[it] * y[it - 1] - c[it]*y[it] + b[it]*y[it + 1], 4) }
        println(f.toList())
        println(err.toList())

        return y
    }

    fun run() {
        val a = 0.0
        val b = 0.5
        val h = getCorrectDouble((b - a) / N, 4)
        val x = (0..N).map { getCorrectDouble(a + it * h, 4) }
        println(tridiagonalMatrix(h,1.0, 1.279, x as ArrayList<Double>))
    }

}