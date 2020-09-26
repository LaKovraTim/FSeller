@file:Suppress("MemberVisibilityCanBePrivate", "UNCHECKED_CAST")

package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.databinding.AdapterOrderListItemBinding
import falabella.lakovratim.android.fastseller.domain.model.WorkOrderResponse
import javax.inject.Inject
import kotlin.random.Random

class OrderListAdapter @Inject constructor() : RecyclerView.Adapter<OrderListAdapter.ViewHolder>(),
    Filterable {

    var items: List<WorkOrderResponse> = listOf()
        set(value) {
            auxItems.addAll(value)
            field = value
        }
    private var auxItems: ArrayList<WorkOrderResponse> = arrayListOf()

    var actionListener: ActionListener? = null

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
        val order = auxItems[position]

        //TODO set values from real object
        holder.orderItemTitle.text = "Pedido #${Random.nextInt()}"
        holder.orderItemClient.text = "Realizada por: Elba Lazo"
        holder.orderItemDate.text = "Fecha entrega: Lunes, 28 de Septiembre"
        holder.orderItemDescription.setOnClickListener {
            Toast.makeText(holder.itemView.context, "In Development", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = auxItems.size

    class ViewHolder(binding: AdapterOrderListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val orderItemTitle = binding.orderItemTitle
        val orderItemDate = binding.orderItemDate
        val orderItemDescription = binding.orderItemDescription
        val orderItemClient = binding.orderItemClient
    }

    override fun getFilter(): Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return FilterResults().apply {
                values = if (constraint.isNullOrEmpty()) {
                    items as ArrayList
                } else {
                    items.filter { it.number.toString().contains(constraint.trim(), true) }
                }
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            auxItems.clear()
            auxItems.addAll(results?.values as ArrayList<WorkOrderResponse>)
            actionListener?.onEmptyFilter(auxItems.isEmpty())
            notifyDataSetChanged()
        }
    }

    interface ActionListener {
        fun onEmptyFilter(isEmpty: Boolean)
    }
}