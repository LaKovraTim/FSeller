package falabella.lakovratim.android.fastseller.presentation.util.extension

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText

/**
 * @author   Andres Lobosz
 * @date    26-06-20.
 */
fun EditText.addSpaceFilter() {
    this.filters = arrayOf(object : InputFilter {
        override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
            if (end == 1)
                source?.getOrNull(0)?.let {
                    if (Character.isWhitespace(it)) return ""

                }
            return null
        }
    })
}