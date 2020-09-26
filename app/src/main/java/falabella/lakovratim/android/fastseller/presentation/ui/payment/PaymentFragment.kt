package falabella.lakovratim.android.fastseller.presentation.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import falabella.lakovratim.android.fastseller.databinding.FragmentPaymentLayoutBinding
import falabella.lakovratim.android.fastseller.presentation.appComponent
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment

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
}