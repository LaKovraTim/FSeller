package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.AdapterFilterItemBinding
import falabella.lakovratim.android.fastseller.presentation.ui.taskdetail.MapBottomSheet
import javax.inject.Inject

class OrderFilterAdapter @Inject constructor() :
    RecyclerView.Adapter<OrderFilterAdapter.ViewHolder>() {

    var items: List<String> = listOf()
    private var previousPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterFilterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = items[position]
        holder.filterName.text = filter

        holder.filterItem.setOnClickListener {
            previousPosition = position;
            notifyDataSetChanged();
        }

        if(position == previousPosition){
            holder.filterItem.backgroundTintList = ColorStateList.valueOf(holder.itemView.context.resources.getColor(R.color.greenBackground,null));
        }
        else {
            holder.filterItem.backgroundTintList = ColorStateList.valueOf(holder.itemView.context.resources.getColor(R.color.screenBackground,null));
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(binding: AdapterFilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val filterName = binding.textFilter
        val filterItem = binding.filterItem
    }
}