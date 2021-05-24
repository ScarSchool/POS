package janssen.android.activities

import janssen.android.R
import janssen.android.services.API

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<EditText>(R.id.baseUrl).setText(API.BASE_URL)
    }

    fun showSales(view: View) {
        API.BASE_URL = findViewById<EditText>(R.id.baseUrl).text.toString()
        intent = Intent(this, SalesListActivity::class.java)
        startActivity(intent)
    }

    fun showAnimalShelters(view: View) {
        API.BASE_URL = findViewById<EditText>(R.id.baseUrl).text.toString()
        intent = Intent(this, AnimalShelterListActivity::class.java)
        startActivity(intent)
    }

    fun showAnimals(view: View) {
        API.BASE_URL = findViewById<EditText>(R.id.baseUrl).text.toString()
        intent = Intent(this, AnimalListActivity::class.java)
        startActivity(intent)
    }

}
