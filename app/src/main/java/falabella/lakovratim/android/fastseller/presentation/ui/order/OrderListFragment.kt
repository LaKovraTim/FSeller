package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentOrderListBinding
import falabella.lakovratim.android.fastseller.domain.model.Customer
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
            }
            is Resource.Error -> {
                binding.progressInclude.gone()
            }
            is Resource.Loading -> {
                binding.progressInclude.visible()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.workOrders, ::handleWorkOrders)
        viewModel.getOrders()

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
            //TODO call to get order list
            hideProgress()
        }

        //TODO remove
        hideProgress()

        binding.orderRecycler.adapter = orderListAdapter.apply {
            actionListener = this@OrderListFragment
            items = mock()
        }

        showFilters()
    }

    private fun mock(): List<WorkOrder> {
        return listOf(
            WorkOrder(
                "1",
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Rob", null, "Martin"),
                "2020/09/28",
                arrayListOf(),
                1,
                arrayListOf(),
                "pendiente"
            ),
            WorkOrder(
                "1",
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Rob", null, "Martin"),
                "2020/09/28",
                arrayListOf(),
                1,
                arrayListOf(),
                "pendiente"
            ),
            WorkOrder(
                "1",
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Rob", null, "Martin"),
                "2020/09/28",
                arrayListOf(),
                1,
                arrayListOf(),
                "pendiente"
            ),
            WorkOrder(
                "1",
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Rob", null, "Martin"),
                "2020/09/28",
                arrayListOf(),
                1,
                arrayListOf(),
                "Activo"
            )
        )
    }

    override fun hideProgress() {
        super.hideProgress()
        binding.orderListSwipe.isRefreshing = false
    }

    private fun showFilters() {
        binding.recyclerViewFilter.adapter = orderListFilterAdapter.apply {
            items = listOf(
                OrderFilter(Filter.Active(), getString(R.string.text_filter_active)),
                OrderFilter(Filter.Retry(), getString(R.string.text_filter_retry)),
                OrderFilter(Filter.Cancel(), getString(R.string.text_filter_cancel))
            )
            filter = ::order
        }
    }

    override fun onEmptyFilter(isEmpty: Boolean) {
        if (isEmpty) {
            binding.orderRecycler.invisible()
        } else {
            binding.orderRecycler.visible()
        }
    }

    override fun onSelectItem(item: WorkOrder) {
        findNavController().navigate(R.id.action_orderListFragment_to_taskDetailFragment)
    }


    private fun order(option: Filter) {
        when (option) {
            is Filter.Active -> filterOrdered("activo")

            is Filter.Retry -> filterOrdered("pendiente")

            is Filter.Cancel -> filterOrdered("cancelado")
        }
    }

    private fun filterOrdered(value: String?) = orderListAdapter.filter.filter(value)

}