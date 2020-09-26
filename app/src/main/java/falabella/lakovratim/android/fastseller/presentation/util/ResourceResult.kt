package falabella.lakovratim.android.fastseller.presentation.util

/**
 * @author   Andres Lobosz
 */
sealed class ResourceResult<T> {
    data class OnSuccess<T>(val value: T) : ResourceResult<T>()
    data class OnError<T>(val throwable: Throwable) : ResourceResult<T>()
    class OnLoading<T> : ResourceResult<T>()
    class OnCancel<T> : ResourceResult<T>()
    class OnEmpty<T> : ResourceResult<T>()
}