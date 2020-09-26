package falabella.lakovratim.android.fastseller.presentation.util

sealed class Filter {
    class Active : Filter()
    class Retry : Filter()
    class Cancel : Filter()
}