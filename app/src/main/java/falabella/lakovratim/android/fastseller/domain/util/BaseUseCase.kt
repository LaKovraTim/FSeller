package falabella.lakovratim.android.fastseller.domain.util

import android.accounts.NetworkErrorException
import kotlinx.coroutines.*

abstract class BaseUseCase<in Request, out Response> {

    private var job = Job()
    private var uiScope =  CoroutineScope(Dispatchers.Main + job)

    @Throws(Exception::class)
    abstract suspend fun doOnBackground(params: Request): Response

    open fun execute(
        params: Request,
        onResult: (Response) -> Unit,
        onFailure: (Failure) -> Unit
    ) {
        uiScope.launch {

            runCatching {
                withContext(Dispatchers.IO) {
                    doOnBackground(params)
                }
            }.onSuccess {
                onResult(it)
            }.onFailure { e ->
                onFailure(
                    when (e) {
                        is NetworkErrorException -> Failure.NetworkConnection
                        else -> Failure.Error(e.message ?: e.toString())
                    }
                )
            }
        }
    }

    open fun dispose() {
        job.cancel()
    }
}