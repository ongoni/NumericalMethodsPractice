package re.numericalmethods.tasks

import re.numericalmethods.utils.DoubleValuesUtils

class Task2 : DoubleValuesUtils {

    private fun f(x: Double) = Math.exp(2 * x) - 2 * Math.sin(x)

    private fun rightRectangles(a: Double, n: Int, h: Double) : Double {
        var result = 0.0
        var x: Double

        (1..n).forEach {
            x = a + it * h
            result += h * f(x)
        }

        return result
    }

    private fun leftRectangles(a: Double, n: Int, h: Double) : Double {
        var result = 0.0
        var x: Double

        (0 until n).forEach {
            x = a + it * h
            result += h * f(x)
        }

        return result
    }

    private fun centralRectangles(a: Double, n: Int, h: Double) : Double {
        var result = 0.0
        var x: Double

        (0 until n).forEach {
            x = a + it * h
            result += h * f(x + h / 2)
        }

        return result
    }

    private fun trapeze(a: Double, b: Double, n: Int, h: Double): Double {
        var result = (h / 2) * (f(a) + f(b))
        var x: Double

        (1 until n).forEach {
            x = a + it * h
            result += h * f(x + h / 2)
        }

        return result
    }

    private fun simpson(a: Double, b: Double, n: Int, h: Double) : Double {
        val m = n / 2

        return (h / 3) * (
                f(a) + f(b)
                + 4 * (1 until 2 * m step 2).map { f(a + it * h) }.sum()
                + 2 * (2..2 * m - 2 step 2).map { f(a + it * h) }.sum()
                )
    }

    fun run() {
        print("enter n: ")
        val n = readLine()!!.toInt()
        val a = -Math.PI / 2; val b = 0.0
        val h = (b - a) / n

        println("exact value - " + (2.5 - Math.exp(-Math.PI) / 2))
        println("right rectangles - " + rightRectangles(a, n, h))
        println("left rectangles - " + leftRectangles(a, n, h))
        println("central rectangles - " + centralRectangles(a, n, h))
        println("trapeze - " + trapeze(a, b, n, h))
        println("Simpson's formula - " + simpson(a, b, n, h))
    }

}