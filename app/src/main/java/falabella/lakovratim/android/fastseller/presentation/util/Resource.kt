@file:Suppress("UNCHECKED_CAST", "unused")

package falabella.lakovratim.android.fastseller.presentation.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import falabella.lakovratim.android.fastseller.domain.util.Failure
import kotlin.reflect.KFunction0

sealed class Resource<T>(
    val data: T? = null,
    val failure: Failure? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class Error<T>(failure: Failure) : Resource<T>(failure = failure)
}

fun <T, R> MutableLiveData<T>.subscribe(
    owner: LifecycleOwner,
    loading: () -> Unit,
    success: (R) -> Unit,
    error: (Failure) -> Unit
) {
    this.observe(owner, Observer {
        when (it) {
            is Resource.Loading<*> -> loading()
            is Resource.Success<*> -> success(it.data as R)
            is Resource.Error<*> -> error(it.failure!!)
        }
    })
}

fun <T : Resource<R>, R> MutableLiveData<T>.handleValue(
    success: (R) -> Unit,
    error: (Failure) -> Unit,
    getData: KFunction0<Unit>
) {
    this.value?.failure?.also {
        error(it)
    } ?: this.value?.data?.also {
        success(it)
    } ?: getData()
}