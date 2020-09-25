@file:Suppress("MemberVisibilityCanBePrivate")

package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.databinding.AdapterOrderListItemBinding
import javax.inject.Inject

class OrderListAdapter @Inject constructor() : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    var items: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterOrderListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = items[position]

        holder.orderItemTitle.text = "OC $order"
        holder.orderItemDate.text = "26/09/2020"
        holder.orderItemDescription.text = "La terrible compra"
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(binding: AdapterOrderListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val orderItemTitle = binding.orderItemTitle
        val orderItemDate = binding.orderItemDate
        val orderItemDescription = binding.orderItemDescription
    }
}