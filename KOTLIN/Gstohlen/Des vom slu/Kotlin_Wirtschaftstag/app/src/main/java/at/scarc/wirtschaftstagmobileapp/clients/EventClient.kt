package at.scarc.androidApp.clients

import at.scarc.androidApp.models.Event
import retrofit2.Call
import retrofit2.http.*

interface EventClient {
    companion object {
        const val path = "/api/events"
    }

    @GET(path)
    fun getAll() : Call<List<Event>>

    @GET("$path/{id}")
    fun getOneById(@Path("id") id: Long?): Call<Event?>

    @Headers("content-type: application/json")
    @PUT(path)
    fun save(@Body event: Event): Call<Event>
}