package falabella.lakovratim.android.fastseller.presentation.util.extension

import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.widget.TextView

/**
 * @author   Andres Lobosz
 * @date    25-09-20.
 */
fun TextView.webLink(color: Int) {
    movementMethod = LinkMovementMethod.getInstance()
    linksClickable = true
    setLinkTextColor(Color.parseColor(resources.getString(color)))
}
