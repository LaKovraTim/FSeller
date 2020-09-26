package falabella.lakovratim.android.fastseller.presentation.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import falabella.lakovratim.android.fastseller.domain.model.*
import java.util.*

/**
 * @author   Andres Lobosz
 * @date    25-09-20.
 */
class Converters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun addressFromString(value: String?): Address? =
        gson.fromJson(value, object : TypeToken<Address?>() {}.type)

    @TypeConverter
    fun fromAddress(address: Address): String? = gson.toJson(address)


    @TypeConverter
    fun attemptFromString(value: String?): Retry? =
        gson.fromJson(value, object : TypeToken<Retry?>() {}.type)

    @TypeConverter
    fun fromAttempt(attempt: Retry?): String? = gson.toJson(attempt)


    @TypeConverter
    fun contactFromString(value: String?): Contact? =
        gson.fromJson(value, object : TypeToken<Contact?>() {}.type)

    @TypeConverter
    fun fromContact(contact: Contact?): String? =
        gson.toJson(contact)

    @TypeConverter
    fun customerFromString(value: String?): Customer? =
        gson.fromJson(value, object : TypeToken<Customer?>() {}.type)

    @TypeConverter
    fun fromCustomer(customer: Customer?): String? =
        gson.toJson(customer)


    @TypeConverter
    fun locationFromString(value: String?): Location? =
        gson.fromJson(value, object : TypeToken<Location?>() {}.type)

    @TypeConverter
    fun fromLocation(location: Location?): String? =
        gson.toJson(location)


    @TypeConverter
    fun productFromString(value: String?): Product? =
        gson.fromJson(value, object : TypeToken<Product?>() {}.type)

    @TypeConverter
    fun fromProduct(product: Product?): String? =
        gson.toJson(product)

    @TypeConverter
    fun receiverFromString(value: String?): Receiver? =
        gson.fromJson(value, object : TypeToken<Receiver?>() {}.type)

    @TypeConverter
    fun fromReceiver(receiver: Receiver?): String? =
        gson.toJson(receiver)

    @TypeConverter
    fun workOrderFromString(value: String?): WorkOrder? =
        gson.fromJson(value, object : TypeToken<WorkOrder?>() {}.type)

    @TypeConverter
    fun fromWorkOrder(workOrderResponse: WorkOrder?): String? =
        gson.toJson(workOrderResponse)


    @TypeConverter
    fun fromStringProducts(value: String?): ArrayList<Product?>? =
        gson.fromJson<ArrayList<Product?>>(
            value,
            object : TypeToken<ArrayList<Product?>?>() {}.type
        )


    @TypeConverter
    fun fromArrayProduct(list: ArrayList<Product?>?): String? =
        gson.toJson(list)


    @TypeConverter
    fun fromStringAttempts(value: String?): ArrayList<Retry?>? =
        gson.fromJson<ArrayList<Retry?>>(
            value,
            object : TypeToken<ArrayList<Retry?>?>() {}.type
        )


    @TypeConverter
    fun fromArrayAttempt(list: ArrayList<Retry?>?): String? =
        gson.toJson(list)


    @TypeConverter
    fun fromStringWorkOrders(value: String?): ArrayList<WorkOrder?>? =
        gson.fromJson<ArrayList<WorkOrder?>>(
            value,
            object : TypeToken<ArrayList<Retry?>?>() {}.type
        )


    @TypeConverter
    fun fromArrayWorkOrder(list: ArrayList<WorkOrder?>?): String? =
        gson.toJson(list)
}