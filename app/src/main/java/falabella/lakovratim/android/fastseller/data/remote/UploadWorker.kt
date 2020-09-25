package falabella.lakovratim.android.fastseller.data.remote

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UploadWorker @Inject constructor(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            throw RuntimeException()
            Log.d("WORK_MANAGER", inputData.getString("algo") ?: "")
            Result.success()
        } catch (throwable: Throwable) {
            Log.d("WORK_MANAGER", "failure: ${inputData.getString("algo")})")
            Result.retry()
        }
    }

//    override fun doWork(): Result {
//        return try {
//            throw RuntimeException()
//            Log.d("WORK_MANAGER", inputData.getString("algo") ?: "")
//            Result.success()
//        } catch (throwable: Throwable) {
//            Log.d("WORK_MANAGER", "failure: ${inputData.getString("algo")})")
//            Result.retry()
//        }
//    }
}

val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
    .setInitialDelay(10, TimeUnit.SECONDS)
    .setInputData(
        Data.Builder().apply {
            putString("algo", "cualquier cosa")
        }.build()
    ).setConstraints(
        Constraints.Builder()
            .apply {
                setRequiredNetworkType(NetworkType.CONNECTED)
                setRequiresBatteryNotLow(true)
                setRequiresStorageNotLow(true)
            }.build()
    )
    .setBackoffCriteria(
        BackoffPolicy.LINEAR,
        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
        TimeUnit.MILLISECONDS
    )
    .build()