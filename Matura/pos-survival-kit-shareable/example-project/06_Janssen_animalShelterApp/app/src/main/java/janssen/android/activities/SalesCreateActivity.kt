package janssen.android.activities

import janssen.android.R
import janssen.android.models.Sales
import janssen.android.services.API

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class SalesCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)
    }

    fun save(view: View) {
        var sales = Sales()
        sales.id = findViewById<EditText>(R.id.id).text.toString().toLong()
//        sales.shelter = findViewById<EditText>(R.id.shelter).text.toString()
//        sales.animal = findViewById<EditText>(R.id.animal).text.toString()
        sales.price = findViewById<EditText>(R.id.price).text.toString().toDouble()
        sales.date = LocalDate.parse(findViewById<EditText>(R.id.date).text.toString())

        GlobalScope.launch {
            try {
                sales = API.sales.create(sales)
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "Sales created!",
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
                            "Could not create the sales!",
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
