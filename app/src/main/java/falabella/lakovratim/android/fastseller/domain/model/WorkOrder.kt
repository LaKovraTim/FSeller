package falabella.lakovratim.android.fastseller.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import falabella.lakovratim.android.fastseller.data.local.TABLE_WORK_ORDER

@Entity(tableName = TABLE_WORK_ORDER)
data class WorkOrder(

    @SerializedName("cashOnDelivery")
    var cashOnDelivery: Boolean?,
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("commerceCode")
    var commerceCode: String?,
    @SerializedName("creationDate")
    var creationDate: String?,
    @SerializedName("customer")
    var customer: Customer?,
    @SerializedName("deliveryDate")
    var deliveryDate: String?,
    @PrimaryKey
    @SerializedName("id")
    var id: String,
    @SerializedName("products")
    var products: ArrayList<Product>?,
    @SerializedName("purchaseOrder")
    var purchaseOrder: Int?,
    @SerializedName("retries")
    var retries: ArrayList<Retry>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("total")
    var total: Int?,
    var isSelected: Boolean = false
)