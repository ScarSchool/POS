package at.scarc.androidApp.clients

import at.scarc.androidApp.models.TimeSlot
import retrofit2.Call
import retrofit2.http.*

interface TimeSlotClient {
    companion object {
        const val path = "/api/timeSlots"
    }

    @GET(path)
    fun getAll() : Call<List<TimeSlot>>

    @GET("${path}/{id}")
    fun getOneById(@Path("id") id: Long?): Call<TimeSlot?>

    @Headers("content-type: application/json")
    @PUT(path)
    fun save(@Body timeSlot: TimeSlot): Call<TimeSlot>
}