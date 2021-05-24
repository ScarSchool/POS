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

class AnimalShelterUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animalshelter)

        var animalShelter = AnimalShelter() // replace with actual source
        findViewById<EditText>(R.id.address).setText(animalShelter.address)
        findViewById<EditText>(R.id.phoneNumber).setText(animalShelter.phoneNumber)
    }

    fun save(view: View) {
        var animalShelter = AnimalShelter()
        animalShelter.address = findViewById<EditText>(R.id.address).text.toString()
        animalShelter.phoneNumber = findViewById<EditText>(R.id.phoneNumber).text.toString()

        GlobalScope.launch {
            try {
                animalShelter = API.animalShelters.update(animalShelter.address, animalShelter)
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "AnimalShelter updated!",
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
                            "Could not update the animalShelter!",
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
