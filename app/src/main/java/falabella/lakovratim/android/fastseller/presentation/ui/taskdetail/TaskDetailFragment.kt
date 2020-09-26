package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentTaskDetailBinding
import falabella.lakovratim.android.fastseller.domain.model.OrderOptions
import falabella.lakovratim.android.fastseller.presentation.appComponent
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment
import falabella.lakovratim.android.fastseller.presentation.util.OrderMenu
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


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showFilters() {
        binding.recyclerViewOptions.adapter = orderOptionsAdapter.apply {
            items = listOf(
                OrderOptions(
                    OrderMenu.SeeMap(),
                    resources.getDrawable(R.drawable.ic_place, null),
                    getString(R.string.text_see_map)
                ),
                OrderOptions(
                    OrderMenu.Delivered(),
                    resources.getDrawable(R.drawable.ic_check, null),
                    "Registrar\nvisita"
                ),

                OrderOptions(
                    OrderMenu.Refuse(), resources.getDrawable(R.drawable.ic_tv_off_rounded, null),
                    getString(R.string.text_refuse)
                ),
            )
            option = ::orderOptions

        }
    }


    private fun orderOptions(option: OrderMenu) {
        when (option) {
            is OrderMenu.SeeMap -> openWaze()
            is OrderMenu.Delivered ->     {
                findNavController().navigate(R.id.action_taskDetailFragment_to_bottomSheetDeliveryFragment)
            }
            is OrderMenu.Postpone -> {
                findNavController().navigate(R.id.action_taskDetailFragment_to_bottomSheetPostponeFragment)
            }
            is OrderMenu.Refuse -> {

            }
        }

    }

    private fun openWaze() {
        try {
            val uri = "waze://?ll=40.761043, -73.980545&navigate=yes"
            requireActivity().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
        } catch (ex: Exception) {
            requireActivity().startActivity( Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze")))
        }
    }
}

