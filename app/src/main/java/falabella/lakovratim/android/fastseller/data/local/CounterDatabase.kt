package falabella.lakovratim.android.fastseller.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import falabella.lakovratim.android.fastseller.data.local.dao.WorkOrderDao
import falabella.lakovratim.android.fastseller.domain.model.WorkOrderResponse
import falabella.lakovratim.android.fastseller.presentation.util.Converters


@TypeConverters(Converters::class)
@Database(
    entities = [
       WorkOrderResponse::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class CounterDatabase : RoomDatabase() {
    abstract val workOrderDao: WorkOrderDao
}