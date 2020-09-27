package falabella.lakovratim.android.fastseller.domain.model


import com.google.gson.annotations.SerializedName

data class Receiver(
    @SerializedName("firstName")
    var firstName: Any?,
    @SerializedName("secondName")
    var secondName: Any?
)