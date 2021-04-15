package at.scarc.androidApp.clients

import at.scarc.androidApp.models.Company
import retrofit2.Call
import retrofit2.http.*

interface CompanyClient {
    companion object {
        const val path = "/api/companies"
    }

    @GET(path)
    fun getAll() : Call<List<Company>>

    @GET("$path/{id}")
    fun getOneById(@Path("id") id: Long?): Call<Company?>

    @Headers( "Content-Type: application/json" )
    @PUT(path)
    fun save(@Body company: Company): Call<Company>
}