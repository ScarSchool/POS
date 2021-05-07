package com.example.wtmobile.ui.event
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.wtmobile.R
import com.example.wtmobile.data.API
import com.example.wtmobile.data.models.Event
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_event_create.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateEvents : AppCompatActivity()  {

    lateinit var etDate : EditText
    lateinit var etLabel : EditText
    lateinit var etPrice : EditText
    lateinit var btnAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_create)
    }

    override fun onStart() {
        super.onStart()
        etDate = findViewById(R.id.etDate)
        etLabel = findViewById(R.id.etLabel)
        etPrice = findViewById(R.id.etPrice)
        btnAdd = findViewById(R.id.button_add_event)

        btnAdd.setOnClickListener {
            var event : Event = Event(
                -1,
                etDate.text.toString(),
                etLabel.text.toString(),
                etPrice.text.toString().toDouble(),
                null
            )

            GlobalScope.launch {
                try {
                    var result : Event = API.getAPI().createEvent(event)

                    Snackbar.make(etDate.rootView, "Successfully created a new event with id " + result.id, Snackbar.LENGTH_LONG)
                        .show()
                } catch(ex : Exception) {
                    Snackbar.make(etDate.rootView, "Could not create new event: " + ex.message, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}