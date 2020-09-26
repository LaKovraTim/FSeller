package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lon")
    var lon: String?
)