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

class SalesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        refresh(null)
    }

    override fun onResume() {
        super.onResume()
        refresh(null)
    }

    fun create(view: View?) {
        intent = Intent(this, SalesCreateActivity::class.java)
        startActivity(intent)
    }

    fun refresh(view: View?) {
        var contentView = findViewById<LinearLayout>(R.id.content)

        GlobalScope.launch {
            try {
                var sales = API.sales.listAll()

                runOnUiThread {
                    contentView.removeAllViews()
                    sales.forEach { sales -> contentView.addView(createCard("#${sales.id}", sales.toString())) }
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
            intent = Intent(this, SalesUpdateActivity::class.java)
            startActivity(intent)
        }

        return card
    }
}
