package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.databinding.AdapterFilterItemBinding
import javax.inject.Inject

class OrderFilterAdapter @Inject constructor() : RecyclerView.Adapter<OrderFilterAdapter.ViewHolder>() {

    var items: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterFilterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = items[position]
        holder.filterName.text = filter.toString()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(binding: AdapterFilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val filterName = binding.textFilter
    }
}