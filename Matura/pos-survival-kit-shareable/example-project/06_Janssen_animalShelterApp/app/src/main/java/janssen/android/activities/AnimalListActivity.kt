package janssen.android.activities

import janssen.android.R
import janssen.android.Utils
import janssen.android.services.API

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        refresh(null)
    }

    override fun onResume() {
        super.onResume()
        refresh(null)
    }

    fun create(view: View?) {
        intent = Intent(this, AnimalCreateActivity::class.java)
        startActivity(intent)
    }

    fun refresh(view: View?) {
        var contentView = findViewById<LinearLayout>(R.id.content)

        GlobalScope.launch {
            try {
                var animals = API.animals.listAll()

                runOnUiThread {
                    contentView.removeAllViews()
                    animals.forEach { animal -> contentView.addView(createCard("#${animal.id}", animal.toString())) }
                }
            }
            catch (ex: Throwable) {
                runOnUiThread {
                    contentView.removeAllViews()
                    contentView.addView(Utils.createCard(
                        applicationContext,
                        "ERROR",
                        ex.message!!
                    ))
                }
            }
        }
    }

    fun createCard(title: String, description: String): CardView {
        var card = Utils.createCard(this, title, description)
        card.setOnClickListener {view ->
            intent = Intent(this, AnimalUpdateActivity::class.java)
            startActivity(intent)
        }

        return card
    }
}
