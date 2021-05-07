package at.scarc.androidApp.clients

import at.scarc.androidApp.models.Mail
import retrofit2.Call
import retrofit2.http.*

interface MailClient {
    companion object {
        const val path = "/api/mails"
    }

    @GET(path)
    fun getAll() : Call<List<Mail>>

    @GET("$path/{id}")
    fun getOneById(@Path("id") id: Long?): Call<Mail?>

    @Headers("content-type: application/json")
    @PUT(path)
    fun save(@Body mail: Mail): Call<Mail>
}