package falabella.lakovratim.android.fastseller.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import falabella.lakovratim.android.fastseller.data.local.TABLE_WORK_ORDER

@Entity(tableName = TABLE_WORK_ORDER)
data class WorkOrder(
    @PrimaryKey
    var id: String,
    var comment: String?,
    var creationDate: String?,
    var customer: Customer?,
    var deliveryDate: String?,
    var products: ArrayList<Product>?,
    var purchaseOrder: Int?,
    var retries: ArrayList<Retry>?,
    var status: String?
)