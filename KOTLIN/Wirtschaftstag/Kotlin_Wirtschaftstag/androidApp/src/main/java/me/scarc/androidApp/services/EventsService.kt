package me.scarc.androidApp.services

import me.scarc.androidApp.data.Event
import retrofit2.Call
import retrofit2.http.GET

interface EventsService {
    @GET("Events/")
    suspend fun listAll(): Call<Event>
}