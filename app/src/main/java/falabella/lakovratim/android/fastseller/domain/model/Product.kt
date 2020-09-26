package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    var description: String?,
    @SerializedName("sku")
    var sku: String?
)