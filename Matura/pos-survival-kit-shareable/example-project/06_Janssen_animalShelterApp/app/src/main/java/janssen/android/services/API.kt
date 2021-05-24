package janssen.android.services

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object API {
    var BASE_URL = "http://localhost:8080"

    private val retrofit: Retrofit get() {
        val json = ObjectMapper()
        json.registerModule(JavaTimeModule())
        json.disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
        json.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        json.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(json))
            .build()
    }

    val sales get() = retrofit.create(SalesService::class.java)
    val animalShelters get() = retrofit.create(AnimalShelterService::class.java)
    val animals get() = retrofit.create(AnimalService::class.java)

}
