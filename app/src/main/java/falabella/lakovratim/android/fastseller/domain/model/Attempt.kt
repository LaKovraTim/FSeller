package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Attempt(
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("location")
    var location: Location?
)