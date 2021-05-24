package janssen.android

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import kotlin.math.roundToInt

object Utils {

    fun convertDpToPx(context: Context, dp: Int): Float {
        return dp * context.resources.displayMetrics.density
    }

    fun convertPxToDp(context: Context, px: Int): Float {
        return px / context.resources.displayMetrics.density
    }

    fun createCard(context: Context, title: String, description: String): CardView {
        // create the card
        var card = CardView(context)
        card.radius = convertDpToPx(context, 4)
        card.cardElevation = convertDpToPx(context, 2)
        card.useCompatPadding = true
        var cardLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        cardLayoutParams.setMargins(convertDpToPx(context, 2).roundToInt())
        card.layoutParams = cardLayoutParams

        // add the layout
        var layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        var linearLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        linearLayoutParams.setMargins(convertDpToPx(context, 8).roundToInt())
        layout.layoutParams = linearLayoutParams
        card.addView(layout)

        // add the title
        var titleView = TextView(context)
        titleView.setTextColor(Color.parseColor("#FF3F51B5"))
        titleView.textSize = convertDpToPx(context, 11)
        titleView.text = title
        layout.addView(titleView)

        // add the description
        var descriptionView = TextView(context)
        descriptionView.textSize = convertDpToPx(context, 9)
        descriptionView.text = description
        layout.addView(descriptionView)

        return card
    }
}
