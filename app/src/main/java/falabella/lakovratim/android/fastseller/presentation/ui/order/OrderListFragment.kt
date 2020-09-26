package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentOrderListBinding
import falabella.lakovratim.android.fastseller.domain.model.Customer
import falabella.lakovratim.android.fastseller.domain.model.WorkOrderResponse
import falabella.lakovratim.android.fastseller.presentation.appComponent
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment
import falabella.lakovratim.android.fastseller.presentation.util.extension.hideKeyboard
import falabella.lakovratim.android.fastseller.presentation.util.extension.invisible
import falabella.lakovratim.android.fastseller.presentation.util.extension.visible
import javax.inject.Inject

class OrderListFragment : BaseFragment<FragmentOrderListBinding>(),
    OrderListAdapter.ActionListener {

    @Inject
    lateinit var orderListAdapter: OrderListAdapter

    @Inject
    lateinit var orderFilterAdapter: OrderFilterAdapter


    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderListBinding = FragmentOrderListBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                requireActivity().hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                orderListAdapter.filter.filter(newText)
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

    private fun mock(): List<WorkOrderResponse> {
        return listOf(
            WorkOrderResponse(
                1,
                arrayListOf(),
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Elba", null, null, "Lazo"),
                "2020/09/26",
                123456,
                arrayListOf(),
                "1",
                ""
            ),
            WorkOrderResponse(
                1,
                arrayListOf(),
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Elba", null, null, "Lazo"),
                "2020/09/26",
                3443434,
                arrayListOf(),
                "1",
                ""
            ),
            WorkOrderResponse(
                1,
                arrayListOf(),
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Elba", null, null, "Lazo"),
                "2020/09/26",
                123456,
                arrayListOf(),
                "1",
                ""
            ),
            WorkOrderResponse(
                1,
                arrayListOf(),
                "Ninguno",
                "2020/09/26",
                Customer(null, null, "Elba", null, null, "Lazo"),
                "2020/09/26",
                3443434,
                arrayListOf(),
                "1",
                ""
            )
        )
    }

    override fun hideProgress() {
        super.hideProgress()
        binding.orderListSwipe.isRefreshing = false
    }

    private fun showFilters() {
        binding.recyclerViewFilter.adapter = orderFilterAdapter.apply {
            items = listOf(
                getString(R.string.text_filter_active),
                getString(R.string.text_filter_retry),
                getString(R.string.text_filter_cancel)
            )
        }
    }

    override fun onEmptyFilter(isEmpty: Boolean) {
        if (isEmpty) {
            binding.orderRecycler.invisible()
        } else {
            binding.orderRecycler.visible()
        }
    }
}