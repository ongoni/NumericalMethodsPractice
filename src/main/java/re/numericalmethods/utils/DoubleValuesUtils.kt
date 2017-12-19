package re.numericalmethods.utils

interface DoubleValuesUtils {

    fun Double.format(count: Int = 6) = String.format("%.${count}f", this)

    fun getCorrectDouble(value: Double, count: Int = 6) : Double
            = Math.round(value * (Math.pow(10.0, count.toDouble()))) / (Math.pow(10.0, count.toDouble()))
}