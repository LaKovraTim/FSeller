package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("email")
    var email: String?,
    @SerializedName("phone")
    var phone: String?
)