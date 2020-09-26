package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Retry(
    @SerializedName("comment")
    var comment: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("location")
    var location: Location?,
    @SerializedName("success")
    var success: String?
)