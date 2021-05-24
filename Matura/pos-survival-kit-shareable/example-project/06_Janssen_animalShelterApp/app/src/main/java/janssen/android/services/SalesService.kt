package janssen.android.services

import janssen.android.models.Sales
import retrofit2.http.*

interface SalesService {
    @GET("/sales")
    suspend fun listAll(): List<Sales>

    @GET("/sales/{id}")
    suspend fun get(@Path("id") id: Long): Sales

    @POST("/sales")
    suspend fun create(@Body sales: Sales): Sales

    @PUT("/sales/{id}")
    suspend fun update(@Path("id") id: Long, @Body sales: Sales): Sales

    @DELETE("/sales/{id}")
    suspend fun delete(@Path("id") id: Long)
}
