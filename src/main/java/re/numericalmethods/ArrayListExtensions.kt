package re.numericalmethods

class ArrayListExtensions {

    companion object {
        fun arrayListDifference(a: ArrayList<Double>, b: ArrayList<Double>): ArrayList<Double>? {
            if (a.count() != b.count()) return null
            return (0 until a.count()).mapTo(ArrayList()) { (a[it] - b[it]) }
        }

        fun arrayListMultiply(a: ArrayList<Double>, x: Double): ArrayList<Double> {
            return a.map { it * x } as ArrayList<Double>
        }
    }

}
