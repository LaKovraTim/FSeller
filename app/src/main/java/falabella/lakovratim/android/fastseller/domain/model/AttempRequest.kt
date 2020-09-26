package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName
import falabella.lakovratim.android.fastseller.domain.model.Location

data class AttempRequest(
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("location")
    var location: Location?,
    @SerializedName("orderNumber")
    var orderNumber: Int?,
    @SerializedName("sellerId")
    var sellerId: String?,
    @SerializedName("success")
    var success: Boolean?
)