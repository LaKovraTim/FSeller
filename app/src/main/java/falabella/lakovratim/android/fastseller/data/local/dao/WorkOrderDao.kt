package falabella.lakovratim.android.fastseller.data.local.dao

import androidx.room.*
import falabella.lakovratim.android.fastseller.domain.model.WorkOrderResponse

/**
 * @author   Andres Lobosz
 * @date    25-09-20.
 */
@Dao
interface WorkOrderDao {
    @Query("SELECT * FROM workorders")
    suspend fun getOrder(): WorkOrderResponse

    @Query("DELETE FROM workorders")
    suspend fun deleteOrders()

    @Update
    fun updateOrder(workOrderResponse: WorkOrderResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrders(paymentMethods: WorkOrderResponse)

}