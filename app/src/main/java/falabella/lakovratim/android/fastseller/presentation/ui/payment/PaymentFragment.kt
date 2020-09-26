package falabella.lakovratim.android.fastseller.presentation.ui.payment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import falabella.lakovratim.android.fastseller.databinding.FragmentPaymentLayoutBinding
import falabella.lakovratim.android.fastseller.presentation.appComponent
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment
import falabella.lakovratim.android.fastseller.presentation.util.extension.invisible
import falabella.lakovratim.android.fastseller.presentation.util.extension.visible
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentFragment : BaseFragment<FragmentPaymentLayoutBinding>() {
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPaymentLayoutBinding =
        FragmentPaymentLayoutBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageFala.setOnClickListener {
            binding.textView16.invisible()
            binding.textView17.invisible()
            binding.imageView8.invisible()
            binding.textView19.invisible()
            binding.checkCMR.visible()
            delay()
        }

        binding.include.headerBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }



    private fun delay(){
        lifecycleScope.launch() {
            whenStarted {
                delay(2000)
            requireActivity().onBackPressed()
            }
        }
    }



}