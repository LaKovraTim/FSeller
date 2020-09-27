package falabella.lakovratim.android.fastseller.domain.model

import java.io.File

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */
data class Order(
    var sellerId: String,
    var workerId: String?,
    var image: File?,
    var comment: String?,
    var latitude: Double?,
    var longitude: Double?,
    var success: Boolean
)