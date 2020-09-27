package falabella.lakovratim.android.fastseller.presentation.util

sealed class Filter {
    object Active : Filter()
    object Retry : Filter()
    object Cancel : Filter()
    object All: Filter()
}