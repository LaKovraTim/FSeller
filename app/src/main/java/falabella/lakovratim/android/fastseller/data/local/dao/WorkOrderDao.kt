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
    suspend fun getPaymentMethods(): WorkOrderResponse

    @Query("DELETE FROM workorders")
    suspend fun deleteAllPaymentMethods()

    @Update
    fun updateUsers(workOrderResponse: WorkOrderResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePaymentMethods(paymentMethods: WorkOrderResponse)

}