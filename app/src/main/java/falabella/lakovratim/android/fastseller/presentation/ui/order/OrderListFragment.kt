package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentOrderListBinding
import falabella.lakovratim.android.fastseller.domain.model.OrderFilter
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.presentation.appComponent
import falabella.lakovratim.android.fastseller.presentation.ui.MainActivityViewModel
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment
import falabella.lakovratim.android.fastseller.presentation.util.Filter
import falabella.lakovratim.android.fastseller.presentation.util.Resource
import falabella.lakovratim.android.fastseller.presentation.util.extension.*
import javax.inject.Inject

class OrderListFragment : BaseFragment<FragmentOrderListBinding>(),
    OrderListAdapter.ActionListener {

    @Inject
    lateinit var orderListAdapter: OrderListAdapter

    @Inject
    lateinit var orderListFilterAdapter: OrderListFilterAdapter

    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }

    private var isListInModeSelect: Boolean = false

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderListBinding = FragmentOrderListBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
    }

    private fun handleWorkOrders(resource: Resource<List<WorkOrder>>?) {
        when (resource) {
            is Resource.Success -> {
                binding.progressInclude.gone()
                viewModel.workOrders.value?.data?.let {
                    showWorkOrders(it)
                }
            }
            is Resource.Error -> {
                binding.progressInclude.gone()
            }
            is Resource.Loading -> {
                binding.progressInclude.visible()
            }
        }
    }

    private fun handleChangeOrder(resource: Resource<Boolean>?) {
        when (resource) {
            is Resource.Success -> {
                if (resource.data == true)
                    showDialog("Estas listo para comenzar!! Recuerda llevar tu mascarilla.")
                else
                    showDialog("Ha ocurrido un error, intenta nuevamente.")

                binding.progressInclude.gone()
            }
            is Resource.Error -> {
                showDialog("Ha ocurrido un error, intenta nuevamente.")
                binding.progressInclude.gone()
            }
            is Resource.Loading -> {
                binding.progressInclude.visible()

            }
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                "Ok"
            ) { dialog, _ -> dialog.dismiss()
                viewModel.getOrders()
            }
            .create()
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.workOrders, ::handleWorkOrders)
        observe(viewModel.changeOrderState, ::handleChangeOrder)
        viewModel.getOrders()

        binding.imageSelectInList.setOnClickListener {
            isListInModeSelect = !isListInModeSelect
            when {
                isListInModeSelect -> {
                    (it as ImageView).setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.blue)
                    )
                    binding.orderSelectedConfirm.visible()
                }
                else -> {
                    (it as ImageView).setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.textColor)
                    )
                    binding.orderSelectedConfirm.gone()
                }
            }
            it.invalidate()
            orderListAdapter.changeItems(isListInModeSelect)
        }

        binding.orderSelectedConfirm.setOnClickListener {
            //TODO get only selected items for activation call service
            val selectedItems = orderListAdapter.getSelectedItems()
            binding.imageSelectInList.performClick()
            viewModel.changeOrdersState(selectedItems.map { it.id })
        }

        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                requireActivity().hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterOrdered(newText)
                return true
            }
        })

        binding.orderListSwipe.setColorSchemeResources(
            R.color.colorPrimaryDark,
            R.color.colorPrimary,
            R.color.colorAccent
        )
        binding.orderListSwipe.setOnRefreshListener {
            viewModel.getOrders()
            hideProgress()
        }

    }

    private fun showWorkOrders(data: List<WorkOrder>) {
        binding.orderRecycler.adapter = orderListAdapter.apply {
            actionListener = this@OrderListFragment
            items = data
        }
        showFilters()
    }


    override fun hideProgress() {
        super.hideProgress()
        binding.orderListSwipe.isRefreshing = false
    }

    private fun showFilters() {
        binding.recyclerViewFilter.adapter = orderListFilterAdapter.apply {
            items = listOf(
                OrderFilter(Filter.Retry, getString(R.string.text_filter_active)),
                OrderFilter(Filter.All, getString(R.string.text_filter_all)),
                OrderFilter(Filter.Cancel, getString(R.string.text_filter_cancel)))
            filter = ::order
            previousPosition=0
            filterOrdered("EN_TRANSITO")
        }
    }

    override fun onEmptyFilter(isEmpty: Boolean) {
        if (isEmpty) {
            binding.orderRecycler.invisible()
            binding.orderEmptyList.visible()

        } else {
            binding.orderRecycler.visible()
            binding.orderEmptyList.invisible()

        }
    }

    override fun onSelectItem(item: WorkOrder) {
        viewModel.setWorkOrderSelected(item)

        if (item.isCancelled()) {
            Toast.makeText(
                requireContext(),
                "No puedes gestionar un pedido cancelado",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController().navigate(R.id.action_orderListFragment_to_taskDetailFragment)
        }
    }


    private fun order(option: Filter) {
        when (option) {
            is Filter.All -> filterOrdered(null)
            is Filter.Retry -> filterOrdered("EN_TRANSITO")
            is Filter.Cancel -> filterOrdered("CANCELADA")
        }
    }

    private fun filterOrdered(value: String?) = orderListAdapter.filter.filter(value)


}