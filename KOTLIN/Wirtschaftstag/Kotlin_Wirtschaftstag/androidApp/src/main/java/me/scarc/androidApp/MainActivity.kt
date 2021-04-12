package me.scarc.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.scarc.androidApp.adapter.EventsAdapter
import me.scarc.androidApp.data.Pupil
import me.scarc.shared.Greeting

fun greet() : String {
    return  Greeting().greeting();
}

class MainActivity : AppCompatActivity() {
    private lateinit var eventsAdapter: EventsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eventsAdapter = EventsAdapter(mutableListOf())

        var user = Pupil(12, "herbert", "geheim", "haha@haha.com", listOf())
    }
}
