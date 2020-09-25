package falabella.lakovratim.android.fastseller.presentation.util.extension

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun Toolbar.setArrowUpToolbar(context: Activity) {
    (context as AppCompatActivity).setSupportActionBar(this)
    context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

fun Toolbar.setNoArrowUpToolbar(context: Activity) {
    (context as AppCompatActivity).setSupportActionBar(this)
    context.supportActionBar?.setDisplayHomeAsUpEnabled(false)
}