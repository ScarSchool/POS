package com.example.wtmobile.data

import com.example.wtmobile.data.models.User
import com.google.gson.GsonBuilder
import com.example.wtmobile.data.models.*
import com.example.wtmobile.data.models.Company
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.net.URL

interface API {
    companion object {
        private lateinit var instance: API
        private lateinit var URL: URL

        private fun setBaseUrl(url: URL): API {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
            instance = retrofit.create(API::class.java)
            URL = url
            return instance
        }

        fun setBaseUrl(url: String): API {
            return setBaseUrl(URL(url))
        }

        fun getAPI(): API {
            if (::instance.isInitialized)
                return instance
            else
                throw UninitializedPropertyAccessException("Should not happen")
        }
    }

    @GET("companies/")
    suspend fun getCompanies(): List<Company>

    @POST("companies/")
    suspend fun createCompany(@Body company: Company): Company

    @GET("departments/")
    suspend fun getDepartments(): List<Department>

    @GET("events/")
    suspend fun getEvents(): List<Event>

    @POST("events/")
    suspend fun createEvent(@Body event: Event): Event

    @GET("admins/")
    suspend fun getAdmins(): List<User>

    @GET("users?userType=all")
    suspend fun getUsers(): List<User>

    @GET("participations/")
    suspend fun getParticipations(@Query("eventId") eventId : String): List<Participation>

    @POST("participations/")
    suspend fun createParticipation(@Body parti : Participation) : Participation
}