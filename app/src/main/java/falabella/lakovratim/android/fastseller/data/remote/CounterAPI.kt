package falabella.lakovratim.android.fastseller.data.remote

import falabella.lakovratim.android.fastseller.domain.model.AttempRequest
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface CounterAPI {
    @GET("/sellers/{sellerId}/work-orders/")
    suspend fun getOrders(@Path("sellerId") sellerId: String): List<WorkOrder>

    @POST("/sellers/{sellerId}/work-orders/{workOrderId}/retries")
    suspend fun sendOrder(
        @Path("sellerId") sellerId: String,
        @Path("workOrderId") orderId: String,
        @Part image: File?,
        @Part comment: String?,
        @Part latitud: Double?,
        @Part longitud: Double?,
        @Part success: Boolean
    ): Response<Void>?
}