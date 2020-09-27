package falabella.lakovratim.android.fastseller.domain.repository

import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import java.io.File

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */
interface IRepository {
    suspend fun getOrders(sellerId: String): List<WorkOrder>
    suspend fun changeOrderState(workerIds: Pair<String,List<String>>): Boolean
    suspend fun sendOrder(
        sellerId: String,
        workerId: String?,
        image: File?,
        comment: String?,
        lalitude: Double?,
        longitude: Double?,
        success: Boolean
    ): Boolean
}