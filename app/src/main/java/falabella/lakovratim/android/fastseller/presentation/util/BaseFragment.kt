@file:Suppress("MemberVisibilityCanBePrivate")

package falabella.lakovratim.android.fastseller.presentation.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): T

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setBinding(inflater, container)
        return binding.root
    }

    open fun showProgress() {
    }

    open fun hideProgress() {
    }
}