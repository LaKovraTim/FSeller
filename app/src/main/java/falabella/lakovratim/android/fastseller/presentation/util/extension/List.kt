package falabella.lakovratim.android.fastseller.presentation.util.extension

fun <T> List<T>?.toArrayList(): ArrayList<T> {
    return ArrayList(this ?: listOf())
}