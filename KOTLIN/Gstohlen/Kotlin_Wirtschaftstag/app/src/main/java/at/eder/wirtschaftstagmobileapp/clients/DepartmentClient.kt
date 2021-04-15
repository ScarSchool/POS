package at.scarc.androidApp.clients

import at.scarc.androidApp.models.Department
import retrofit2.Call
import retrofit2.http.*

interface DepartmentClient {
    companion object {
        const val path = "/api/departments"
    }

    @GET(path)
    fun getAll() : Call<List<Department>>

    @GET("$path/{id}")
    fun getOneById(@Path("id") id: Long?): Call<Department?>

    @Headers("content-type: application/json")
    @PUT(path)
    fun save(@Body department: Department): Call<Department>
}