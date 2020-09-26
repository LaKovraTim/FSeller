package falabella.lakovratim.android.fastseller.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import falabella.lakovratim.android.fastseller.data.local.TABLE_WORK_ORDER

@Entity(tableName = TABLE_WORK_ORDER)
data class WorkOrderResponse(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @Expose
    var attempts: ArrayList<Attempt>?,
    @Expose
    var comment: String?,
    @Expose
    var creationDate: String?,
    @Expose
    var customer: Customer?,
    @Expose
    var deliveryDate: String?,
    @Expose
    var number: Int?,
    @Expose
    var products: ArrayList<Product>?,
    @Expose
    var sellerId: String?,
    @Expose
    var status: String?
)