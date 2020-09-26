package falabella.lakovratim.android.fastseller.data.remote

import falabella.lakovratim.android.fastseller.domain.model.AttempRequest
import falabella.lakovratim.android.fastseller.domain.model.WorkOrderResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CounterAPI {
    @POST("/servicio")
    suspend fun makeTransaction(@Body request: AttempRequest): WorkOrderResponse

}