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

class SalesUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        var sales = Sales() // replace with actual source
        findViewById<EditText>(R.id.id).setText("${sales.id}")
//        findViewById<EditText>(R.id.shelter).setText(sales.shelter)
//        findViewById<EditText>(R.id.animal).setText(sales.animal)
        findViewById<EditText>(R.id.price).setText("${sales.price}")
        findViewById<EditText>(R.id.date).setText("${sales.date}")
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
                sales = API.sales.update(sales.id, sales)
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "Sales updated!",
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
                            "Could not update the sales!",
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
