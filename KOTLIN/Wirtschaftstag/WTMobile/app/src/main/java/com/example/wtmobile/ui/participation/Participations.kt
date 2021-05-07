package com.example.wtmobile.ui.participation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtmobile.MainActivity
import com.example.wtmobile.R
import com.example.wtmobile.data.API
import com.example.wtmobile.data.models.Event
import com.example.wtmobile.data.models.Participation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Participations : AppCompatActivity() {
    private var eventId : Int = -1
    private lateinit var event : Event

    private lateinit var rvParticipations : RecyclerView
    private lateinit var participationsAdapter : ParticipationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participations)

        eventId = MainActivity.globalEvent?.id ?: -1

        rvParticipations = findViewById(R.id.rvParticipations)
        rvParticipations.layoutManager = LinearLayoutManager(this)

        participationsAdapter = ParticipationAdapter(mutableListOf<Participation>())
        rvParticipations.adapter = participationsAdapter;

        findViewById<FloatingActionButton>(R.id.fab_add_participations).setOnClickListener {
            val intent = Intent(
                rvParticipations.rootView.context,
                CreateParticipations::class.java
            )

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ContextCompat.startActivity(rvParticipations.rootView.context, intent, null)
        }

        refreshData();
    }

    private fun refreshData() {
        GlobalScope.launch() {
            var participations = API.getAPI().getParticipations(eventId.toString());

            runOnUiThread {
                participations.forEach {
                    participationsAdapter.addParticipation(it)
                }
            }
        }
    }
}