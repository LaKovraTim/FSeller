package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    var city: String?,
    @SerializedName("comuna")
    var comuna: String?,
    @SerializedName("location")
    var location: Location?,
    @SerializedName("number")
    var number: String?,
    @SerializedName("refers")
    var refers: String?,
    @SerializedName("region")
    var region: String?,
    @SerializedName("street")
    var street: String?,
    @SerializedName("type")
    var type: String?
)