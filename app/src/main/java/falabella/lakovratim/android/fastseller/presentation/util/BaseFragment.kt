@file:Suppress("MemberVisibilityCanBePrivate")

package falabella.lakovratim.android.fastseller.presentation.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import falabella.lakovratim.android.fastseller.presentation.util.extension.gone
import falabella.lakovratim.android.fastseller.presentation.util.extension.visible
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
        with(requireContext()) {
            (binding.root.findViewById(
                resources.getIdentifier(
                    "progress_include",
                    "id",
                    packageName
                )
            ) as? ConstraintLayout?)?.visible()
        }
    }

    open fun hideProgress() {
        with(requireContext()) {
            (binding.root.findViewById(
                resources.getIdentifier(
                    "progress_include",
                    "id",
                    packageName
                )
            ) as? ConstraintLayout?)?.gone()
        }
    }
}