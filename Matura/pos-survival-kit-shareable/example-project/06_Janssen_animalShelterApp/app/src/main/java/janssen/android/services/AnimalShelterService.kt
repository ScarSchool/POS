package janssen.android.services

import janssen.android.models.AnimalShelter
import retrofit2.http.*

interface AnimalShelterService {
    @GET("/shelters")
    suspend fun listAll(): List<AnimalShelter>

    @GET("/shelters/{address}")
    suspend fun get(@Path("address") address: String): AnimalShelter

    @POST("/shelters")
    suspend fun create(@Body animalShelter: AnimalShelter): AnimalShelter

    @PUT("/shelters/{address}")
    suspend fun update(@Path("address") address: String, @Body animalShelter: AnimalShelter): AnimalShelter

    @DELETE("/shelters/{address}")
    suspend fun delete(@Path("address") address: String)
}
