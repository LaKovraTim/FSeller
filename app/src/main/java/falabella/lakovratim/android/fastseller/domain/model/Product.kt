package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("cantidad")
    var cantidad: Int?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("sku")
    var sku: String?
)