package janssen.android.services

import janssen.android.models.Animal
import retrofit2.http.*

interface AnimalService {
    @GET("/animals")
    suspend fun listAll(): List<Animal>

    @GET("/animals/{id}")
    suspend fun get(@Path("id") id: Long): Animal

    @POST("/animals")
    suspend fun create(@Body animal: Animal): Animal

    @PUT("/animals/{id}")
    suspend fun update(@Path("id") id: Long, @Body animal: Animal): Animal

    @DELETE("/animals/{id}")
    suspend fun delete(@Path("id") id: Long)
}
