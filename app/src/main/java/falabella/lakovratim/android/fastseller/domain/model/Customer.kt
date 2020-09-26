package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("address")
    var address: Address?,
    @SerializedName("contact")
    var contact: Contact?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("receiver")
    var `receiver`: Receiver?,
    @SerializedName("secondName")
    var secondName: String?
)