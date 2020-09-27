@file:Suppress("MemberVisibilityCanBePrivate", "UNCHECKED_CAST")

package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.AdapterOrderListItemBinding
import falabella.lakovratim.android.fastseller.databinding.AdapterOrderListItemToSelectBinding
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import javax.inject.Inject

class OrderListAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var items: List<WorkOrder> = listOf()
        set(value) {
            auxItems.clear()
            auxItems.addAll(value)
            field = value
        }
    private var auxItems: MutableList<WorkOrder> = mutableListOf()

    var actionListener: ActionListener? = null

    override fun getItemViewType(position: Int): Int {
        return auxItems[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> NormalHolder(
                AdapterOrderListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> {
                ItemToSelectHolder(
                    AdapterOrderListItemToSelectBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val order = auxItems[position]

        with(holder.itemView.context) {

            val identifierDrawable = this.resources.getIdentifier(
                "ic_${order.commerceCode}",
                "drawable",
                this.packageName
            )

            when (holder) {
                is NormalHolder -> {

                    if (identifierDrawable > 0) {
                        holder.orderItemImage.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this,
                                identifierDrawable
                            )
                        )
                    }

                    holder.orderItemTitle.text = this.getString(R.string.order_number, order.id)
                    holder.orderItemClient.text = this.getString(
                        R.string.made_by,
                        "${order.customer?.firstName} ${order.customer?.secondName}"
                    )
                    holder.orderItemDate.text =
                        this.getString(R.string.delivety_date, order.deliveryDate)
                    holder.orderItemCommune.text = order.customer?.address?.comuna
                    holder.orderItemAttempt.text = "Intentos de entrega: ${order.retries?.size.toString()}"
                    holder.card.setOnClickListener {
                        actionListener?.onSelectItem(order)
                    }
                    when (order.status) {
                        "PENDIENTE" -> holder.orderItemCardState.backgroundTintList =
                            ColorStateList.valueOf(
                                holder.itemView.context.resources.getColor(
                                    R.color.yellow,
                                    null
                                )
                            )
                        "CANCELADA" -> holder.orderItemCardState.backgroundTintList =
                            ColorStateList.valueOf(
                                holder.itemView.context.resources.getColor(
                                    R.color.red,
                                    null
                                )
                            )
                        else -> holder.orderItemCardState.backgroundTintList =
                            ColorStateList.valueOf(
                                holder.itemView.context.resources.getColor(
                                    R.color.greenBank,
                                    null
                                )
                            )
                    }

                }
                is ItemToSelectHolder -> {

                    if (identifierDrawable > 0) {
                        holder.orderItemImage.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this,
                                identifierDrawable
                            )
                        )
                    }
                    holder.orderItemTitle.text = this.getString(R.string.order_number, order.id)
                    holder.orderItemClient.text = this.getString(
                        R.string.made_by,
                        "${order.customer?.firstName} ${order.customer?.secondName}"
                    )
                    holder.orderItemDate.text =
                        this.getString(R.string.delivety_date, order.deliveryDate)
                    holder.card.setOnClickListener {
                        actionListener?.onSelectItem(order)
                    }
                    holder.orderItemCommune.text = order.customer?.address?.comuna
                    holder.orderItemAttempt.text = "Intentos de entrega: ${order.retries?.size.toString()}"


                    fun changeSelected(order: WorkOrder) {
                        when (order.isSelected) {
                            true -> {
                                holder.orderCheckSelect.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this,
                                        R.drawable.circle_check
                                    )
                                )
                            }
                            else -> {
                                holder.orderCheckSelect.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this,
                                        R.drawable.circle_empty
                                    )
                                )
                            }
                        }
                    }

                    when (order.status) {
                        "PENDIENTE" -> holder.orderItemCardState.backgroundTintList =
                            ColorStateList.valueOf(
                                holder.itemView.context.resources.getColor(
                                    R.color.yellow,
                                    null
                                )
                            )
                        "CANCELADA" -> holder.orderItemCardState.backgroundTintList =
                            ColorStateList.valueOf(
                                holder.itemView.context.resources.getColor(
                                    R.color.red,
                                    null
                                )
                            )
                        else -> holder.orderItemCardState.backgroundTintList =
                            ColorStateList.valueOf(
                                holder.itemView.context.resources.getColor(
                                    R.color.greenBank,
                                    null
                                )
                            )
                    }

                    changeSelected(order)
                    holder.orderCheckSelect.setOnClickListener {
                        order.isSelected = !order.isSelected
                        changeSelected(order)
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int = auxItems.size

    class NormalHolder(binding: AdapterOrderListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val orderItemImage = binding.orderItemImage
        val orderItemTitle = binding.orderItemTitle
        val orderItemDate = binding.orderItemDate
        val orderItemCommune = binding.orderItemState
        val orderItemAttempt = binding.orderItemAttempt
        val orderItemDescription = binding.orderItemGoToDetail
        val orderItemClient = binding.orderItemClient
        val card = binding.orderItemCard
        val orderItemCardState = binding.orderItemCardState
    }

    class ItemToSelectHolder(binding: AdapterOrderListItemToSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val orderItemImage = binding.orderItemImage
        val orderItemTitle = binding.orderItemTitle
        val orderItemDate = binding.orderItemDate
        val orderItemCommune = binding.orderItemState
        val orderItemAttempt = binding.orderItemAttempt
        val orderItemClient = binding.orderItemClient
        val card = binding.orderItemCard
        val orderCheckSelect = binding.orderCheckSelect
        val orderItemCardState = binding.orderItemCardState
    }

    override fun getFilter(): Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return FilterResults().apply {
                values = if (constraint.isNullOrEmpty()) {
                    items
                } else {
                    items.filter {
                        it.id.contains(constraint.trim(), true)
                                || it.status?.contains(constraint.trim(), true) == true
                                || it.customer?.address?.comuna?.contains(
                            constraint.trim(),
                            true
                        ) == true
                    }
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

    fun changeItems(toSelect: Boolean) {
        when {
            toSelect -> {
                items.filter { it.isSelectable() }
                    .forEach {
                        it.type = WorkOrder.SELECT_TYPE_ITEM
                        it.isSelected = true
                    }
            }
            else -> {
                items.forEach {
                    it.type = WorkOrder.NORMAL_TYPE_ITEM
                    it.isSelected = false
                }
            }
        }
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<WorkOrder> {
        return items.filter { it.isSelected }.toList()
    }

    interface ActionListener {
        fun onEmptyFilter(isEmpty: Boolean)
        fun onSelectItem(item: WorkOrder)
    }
}