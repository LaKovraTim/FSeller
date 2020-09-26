package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentTaskDetailBinding
import falabella.lakovratim.android.fastseller.domain.model.OrderOptions
import falabella.lakovratim.android.fastseller.presentation.appComponent
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment
import javax.inject.Inject


class TaskDetailFragment : BaseFragment<FragmentTaskDetailBinding>() {

    @Inject
    lateinit var orderOptionsAdapter: OrderOptionsAdapter

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskDetailBinding = FragmentTaskDetailBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFilters()

        binding.include.headerBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun showFilters() {
        binding.recyclerViewOptions.adapter = orderOptionsAdapter.apply {
            items = listOf(
                OrderOptions(resources.getDrawable(R.drawable.ic_place, null), "Ver mapa"),
                OrderOptions(resources.getDrawable(R.drawable.ic_check, null), "Entregado"),
                OrderOptions(
                    resources.getDrawable(R.drawable.ic_schedule, null),
                    "Postergar\nPedido"
                ),
                OrderOptions(
                    resources.getDrawable(R.drawable.ic_tv_off_rounded, null),
                    "Rechazar\npedido"
                ),
            )
        }


    }

    private fun openWaze() {
        try {
            val url = "https://waze.com/ul?q=Hawaii"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"))
            startActivity(intent)
        }
    }
}

