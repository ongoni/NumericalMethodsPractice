package re.numericalmethods.tasks

import re.numericalmethods.utils.ArrayListExtensions.Companion.arrayListDifference
import re.numericalmethods.utils.ArrayListExtensions.Companion.arrayListMultiply
import re.numericalmethods.utils.DoubleValuesUtils
import java.lang.Math.abs

class Task4: DoubleValuesUtils {

    private var matrix = ArrayList<ArrayList<Double>>()
    private var xI = ArrayList<Double>()
    private var resultValues = ArrayList<Double>()
    private var n = 0

    private fun tridiagonalMatrix(inputMatrix: ArrayList<ArrayList<Double>>): ArrayList<Double> {
        xI.clear()
        matrix = inputMatrix
        n = matrix.count() - 1

        val pi = ArrayList<Double>()
        val qi = ArrayList<Double>()

        pi.add(matrix[0][1] / -matrix[0][0]) //gamma[i] == upperDiag / beta[i] == mainDiag
        qi.add(-1 * matrix[0][n + 1] / -matrix[0][0]) // sigma[i] == lastColumn / beta[i]

        for (i in 1 until n) { //вычисляем все значения для Pi, Qi
            val a = matrix[i][i - 1]
            val b = -matrix[i][i]
            val c = matrix[i][i + 1]
            pi.add(c / (b - a * pi[i - 1]))
            qi.add((a * qi[i - 1] - matrix[i][n + 1]) / (b - a * pi[i - 1]))
        }

        val lastA = matrix[n][n - 1]
        val lastB = -matrix[n][n]

        xI.add((lastA * qi[n - 1] - matrix[n][n + 1]) / (lastB - lastA * pi[n - 1]))

        for (i in n - 1 downTo 0) xI.add(pi[i] * xI[xI.count() - 1] + qi[i])
        xI.reverse()
        return xI.map { getCorrectDouble(it, 4) } as ArrayList<Double>
    }

    private fun gaussSolving(inputMatrix: ArrayList<ArrayList<Double>>): ArrayList<Double> {
        resultValues.clear()
        matrix = inputMatrix
        n = matrix.count() - 1
        straightBypass()
        reverseBypass()

        return resultValues.map { getCorrectDouble(it, 4) } as ArrayList<Double>
    }

    private fun straightBypass() {
        for (i in 0..n) {
            val tempMatrixString = matrix[i].map { value -> value / matrix[i][i] } as ArrayList<Double>

            for (j in i + 1..n) {
                matrix[j] = arrayListDifference(matrix[j],
                        arrayListMultiply(tempMatrixString, matrix[j][i] / matrix[i][i]))!!
            }
            matrix[i] = tempMatrixString
        }
    }

    private fun reverseBypass() {
        for (i in 0..n) resultValues.add(0.0)
        resultValues[n] = matrix[n][n + 1]

        for (i in n - 1 downTo 0) {
            val sum = (i + 1..n).sumByDouble { matrix[i][it] * resultValues[it] }
            resultValues[i] = matrix[i][n + 1] - sum
        }
    }

    private fun checkGaussSolution(): ArrayList<Double> {
        val errors = ArrayList<Double>()

        for (i in 0..n) {
            val sum = (0..n).sumByDouble { matrix[i][it] * resultValues[it] }
            errors.add(abs(getCorrectDouble(sum - matrix[i][n + 1], 5)))
        }

        return errors.map { getCorrectDouble(it, 4) } as ArrayList<Double>
    }

    fun run() {
        val inputArray1 = arrayListOf(
                arrayListOf(10.0, 0.1, 0.2, 1.0),
                arrayListOf(0.1, 9.0, 0.3, 1.0),
                arrayListOf(0.2, 0.3, 8.0, 1.0)
        )
        val inputArray2 = arrayListOf(
                arrayListOf(10.0, 0.1, 0.2, 1.0),
                arrayListOf(0.1, 9.0, 0.3, 1.0),
                arrayListOf(0.2, 0.3, 8.0, 1.0)
        )
        val inputArray3 = arrayListOf(
                arrayListOf(4.0, -2.0, 0.0, 1.0),
                arrayListOf(1.0, 2.0, 1.0, 1.0),
                arrayListOf(0.0, 3.0, 1.0, 1.0)
        )
        val inputArray4 = arrayListOf(
                arrayListOf(4.0, -2.0, 0.0, 1.0),
                arrayListOf(1.0, 2.0, 1.0, 1.0),
                arrayListOf(0.0, 3.0, 1.0, 1.0)
        )

        println("Метод Гаусса\n" + gaussSolving(inputArray1))
        println("Погрешность метода Гаусса\n" + checkGaussSolution())
        println("Метод прогонки\n" + tridiagonalMatrix(inputArray2))
        println()
        println("Метод Гаусса\n" + gaussSolving(inputArray3))
        println("Погрешность метода Гаусса\n" + checkGaussSolution())
        println("Метод прогонки\n" + tridiagonalMatrix(inputArray4))
    }

}
