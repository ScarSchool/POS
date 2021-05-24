package com.example.srdp_tomassetti_kotlin.data

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import java.net.URL

interface API {
    companion object {
        private lateinit var instance: API
        private lateinit var URL: URL

        private fun setBaseUrl(url: URL) : API {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val micheal = ObjectMapper()
            micheal.findAndRegisterModules()
            micheal.disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            micheal.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            micheal.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            // val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(micheal))
                .client(httpClient.build())
                .build()
            instance = retrofit.create(API::class.java)
            URL = url
            return instance
        }

        fun setBaseUrl(url: String) : API {
            return setBaseUrl(URL(url))
        }

        fun getAPI(): API{
            if(::instance.isInitialized)
                return instance
            else
                throw UninitializedPropertyAccessException("Should not happen")
        }
    }

    @GET("todos/1")
    suspend fun getTodos1(): Todo
}