package falabella.lakovratim.android.fastseller.presentation.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */
fun <T, L : LiveData<T>> FragmentActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun <T, L : LiveData<T>> Fragment.observeFragment(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))