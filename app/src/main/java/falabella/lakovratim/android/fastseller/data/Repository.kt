package falabella.lakovratim.android.fastseller.data

import falabella.lakovratim.android.fastseller.data.local.CounterDatabase
import falabella.lakovratim.android.fastseller.data.remote.CounterAPI
import falabella.lakovratim.android.fastseller.domain.model.OrderStateRequest
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import java.io.File
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    25-09-20.
 */
class Repository @Inject constructor(
    private val database: CounterDatabase,
    private val counterAPI: CounterAPI
) : IRepository {
    override suspend fun getOrders(sellerId: String): List<WorkOrder> =
        counterAPI.getOrders(sellerId)

    override suspend fun changeOrderState(workerIds: Pair<String,List<String>>): Boolean {
        val response = counterAPI.changeOrderState(workerIds.first, OrderStateRequest(workerIds.second))
        return response?.isSuccessful ?: false
    }

    override suspend fun sendOrder(
        sellerId: String,
        workerId: String?,
        image: File?,
        comment: String?,
        latitude: Double?,
        longitude: Double?,
        success: Boolean,
    ): Boolean {

        /*  val requestFile: RequestBody? =
              image?.let { RequestBody.create("multipart/form-data".toMediaTypeOrNull(), it) }
          val imagePart: MultipartBody.Part? =
              requestFile?.let { MultipartBody.Part.createFormData("image", image.name, it) }*/

        val response = counterAPI.sendOrder(
            sellerId,
            workerId!!,
            null
            /*createFileForTest()*/,
            comment,
            latitude,
            longitude,
            success
        )
        return response?.isSuccessful ?: false

    }
}
