package me.scarc.androidApp.web

import me.scarc.androidApp.services.EventsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    var BASE_URL = "http://172.16.228.1:8080/"

    private val retrofit get() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val events get() = retrofit.create(EventsService::class.java)
}