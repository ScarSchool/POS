package janssen.android.activities

import janssen.android.R
import janssen.android.models.AnimalShelter
import janssen.android.services.API

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalShelterCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animalshelter)
    }

    fun save(view: View) {
        var animalShelter = AnimalShelter()
        animalShelter.address = findViewById<EditText>(R.id.address).text.toString()
        animalShelter.phoneNumber = findViewById<EditText>(R.id.phoneNumber).text.toString()

        GlobalScope.launch {
            try {
                animalShelter = API.animalShelters.create(animalShelter)
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "AnimalShelter created!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
            catch (ex: Throwable) {
                Log.e("error", "error", ex)
                runOnUiThread {
                    Toast.makeText(
                            applicationContext,
                            "Could not create the animalShelter!",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun cancel(view: View) {
        finish()
    }
}
