package falabella.lakovratim.android.fastseller.domain.util


sealed class Failure {
    data class Error(val description: String) : Failure()
    object NetworkConnection : Failure()
}

