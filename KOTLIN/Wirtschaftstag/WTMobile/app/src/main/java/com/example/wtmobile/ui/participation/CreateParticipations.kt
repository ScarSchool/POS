package com.example.wtmobile.ui.participation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.wtmobile.MainActivity
import com.example.wtmobile.R
import com.example.wtmobile.data.API
import com.example.wtmobile.data.models.Company
import com.example.wtmobile.data.models.Participation
import com.example.wtmobile.data.models.Responsible
import com.example.wtmobile.data.models.TimeSlot
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateParticipations : AppCompatActivity()  {

    lateinit var etPrice : EditText
    lateinit var etPaidAlready : EditText
    lateinit var etComment : EditText
    lateinit var etCompany : EditText
    lateinit var etResponsible : EditText
    lateinit var btnAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participation_create)
    }

    override fun onStart() {
        super.onStart()
        etPaidAlready = findViewById(R.id.etPaidAlready)
        etComment = findViewById(R.id.etComment)
        etPrice = findViewById(R.id.etPrice)
        etCompany = findViewById(R.id.etCompany)
        etResponsible = findViewById(R.id.etResponsible)
        btnAdd = findViewById(R.id.button_add_parti)

        btnAdd.setOnClickListener {
            var participation : Participation = Participation(
                -1,
                etPrice.text.toString().toDouble(),
                etPaidAlready.text.toString().toDouble(),
                etComment.text.toString(),
                MainActivity.globalEvent!!,
                Responsible(etResponsible.text.toString().toInt(), "", "", "", null, listOf()),
                listOf(),
                Company(etCompany.text.toString().toInt(), "", "", "", "", "", "", ""),
                listOf<TimeSlot>()
            )

            GlobalScope.launch {
                try {
                    var result : Participation = API.getAPI().createParticipation(participation)

                    Snackbar.make(etPaidAlready.rootView, "Successfully created a new participation with id " + result.id, Snackbar.LENGTH_LONG)
                        .show()
                } catch(ex : Exception) {
                    Snackbar.make(etPaidAlready.rootView, "Could not create new participation: " + ex.message, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}