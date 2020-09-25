package falabella.lakovratim.android.fastseller.presentation.util.extension

import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.getValue() = this.editText?.text.toString().trim()

fun TextInputLayout.clear() {
    this.error = null
}




