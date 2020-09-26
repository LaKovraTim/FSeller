@file:Suppress("MemberVisibilityCanBePrivate", "UNCHECKED_CAST")

package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.AdapterOrderListItemBinding
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import javax.inject.Inject

class OrderListAdapter @Inject constructor() : RecyclerView.Adapter<OrderListAdapter.ViewHolder>(),
    Filterable {

    var items: List<WorkOrder> = listOf()
        set(value) {
            auxItems.addAll(value)
            field = value
        }
    private var auxItems: MutableList<WorkOrder> = mutableListOf()

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

    //TODO delete
    private val logos = arrayOf(R.drawable.ic_fala, R.drawable.ic_sodimac, R.drawable.ic_linio)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = auxItems[position]

        with(holder.itemView.context) {

            holder.orderItemImage.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    logos.random()
                )
            )
            holder.orderItemTitle.text = this.getString(R.string.order_number, order.id)
            holder.orderItemClient.text = this.getString(
                R.string.made_by,
                "${order.customer?.firstName} ${order.customer?.secondName}"
            )
            holder.orderItemDate.text = this.getString(R.string.delivety_date, order.deliveryDate)
            holder.card.setOnClickListener {
                actionListener?.onSelectItem(order)
            }
        }
    }

    override fun getItemCount(): Int = auxItems.size

    class ViewHolder(binding: AdapterOrderListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val orderItemImage = binding.orderItemImage
        val orderItemTitle = binding.orderItemTitle
        val orderItemDate = binding.orderItemDate
        val orderItemDescription = binding.orderItemDescription
        val orderItemClient = binding.orderItemClient
        val card = binding.orderItemCard
    }

    override fun getFilter(): Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return FilterResults().apply {
                values = if (constraint.isNullOrEmpty()) {
                    items
                } else {
                    items.filter { it.id.contains(constraint.trim(), true) }
                } as MutableList
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            auxItems.clear()
            auxItems.addAll(results?.values as MutableList<WorkOrder>)
            actionListener?.onEmptyFilter(auxItems.isEmpty())
            notifyDataSetChanged()
        }
    }

    interface ActionListener {
        fun onEmptyFilter(isEmpty: Boolean)
        fun onSelectItem(item: WorkOrder)
    }
}