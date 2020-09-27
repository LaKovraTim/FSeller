package falabella.lakovratim.android.fastseller.data.remote

import falabella.lakovratim.android.fastseller.domain.model.AttempRequest
import falabella.lakovratim.android.fastseller.domain.model.OrderStateRequest
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface CounterAPI {
    @GET("capillary/v1/sellers/{sellerId}/work-orders")
    suspend fun getOrders(@Path("sellerId") sellerId: String): List<WorkOrder>

    @PATCH("capillary/v1/sellers/{sellerId}/work-orders")
    suspend fun changeOrderState(@Path("sellerId") sellerId: String, @Body request: OrderStateRequest): Response<Void>?

    @Multipart
    @POST("capillary/v1/sellers/{sellerId}/work-orders/{workOrderId}/retries")
    suspend fun sendOrder(
        @Path("sellerId") sellerId: String,
        @Path("workOrderId") workOrderId: String,
        @Part("image") image: File?,
        @Part("comment") comment: String?,
        @Part("latitud") latitud: Double?,
        @Part("longitud") longitud: Double?,
        @Part("success") success: Boolean
    ): Response<Void>?
}