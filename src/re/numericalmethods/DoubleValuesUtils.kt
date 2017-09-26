package re.numericalmethods

interface DoubleValuesUtils {

    fun Double.format(count: Int) = String.format("%.${count}f", this)

    fun getCorrectDouble(value: Double, count: Int) : Double {
        return Math.round(value * (Math.pow(10.0, count.toDouble()))) / (Math.pow(10.0, count.toDouble()))
    }
}