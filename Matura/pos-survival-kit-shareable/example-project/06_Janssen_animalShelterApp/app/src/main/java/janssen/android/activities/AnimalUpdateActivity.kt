package janssen.android.activities

import janssen.android.R
import janssen.android.models.Animal
import janssen.android.services.API

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import janssen.android.models.Gender
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class AnimalUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)

        var animal = Animal() // replace with actual source
        findViewById<EditText>(R.id.id).setText("${animal.id}")
        findViewById<EditText>(R.id.name).setText(animal.name)
        findViewById<EditText>(R.id.race).setText(animal.race)
        findViewById<EditText>(R.id.dateOfBirth).setText("${animal.dateOfBirth}")
        findViewById<EditText>(R.id.note).setText(animal.note)
        findViewById<EditText>(R.id.gender).setText(animal.gender?.name)
        findViewById<CheckBox>(R.id.sold).isChecked = animal.sold!!
    }

    fun save(view: View) {
        var animal = Animal()
        animal.id = findViewById<EditText>(R.id.id).text.toString().toLong()
        animal.name = findViewById<EditText>(R.id.name).text.toString()
        animal.race = findViewById<EditText>(R.id.race).text.toString()
        animal.dateOfBirth = LocalDate.parse(findViewById<EditText>(R.id.dateOfBirth).text.toString())
        animal.note = findViewById<EditText>(R.id.note).text.toString()
        animal.gender = Gender.valueOf(findViewById<EditText>(R.id.gender).text.toString())
        animal.sold = findViewById<CheckBox>(R.id.sold).isChecked

        GlobalScope.launch {
            try {
                animal = API.animals.update(animal.id, animal)
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "Animal updated!",
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
                            "Could not update the animal!",
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
