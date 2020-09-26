package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Receiver(
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("run")
    var run: String?,
    @SerializedName("secondName")
    var secondName: String?
)