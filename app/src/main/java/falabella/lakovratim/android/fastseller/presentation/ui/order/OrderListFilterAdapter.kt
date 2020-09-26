package falabella.lakovratim.android.fastseller.presentation.ui.order

import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.AdapterFilterItemBinding
import falabella.lakovratim.android.fastseller.domain.model.OrderFilter
import falabella.lakovratim.android.fastseller.presentation.util.Filter
import falabella.lakovratim.android.fastseller.presentation.util.OrderMenu
import javax.inject.Inject
import kotlin.reflect.KFunction1

class OrderListFilterAdapter @Inject constructor() :
    RecyclerView.Adapter<OrderListFilterAdapter.ViewHolder>() {

    var items: List<OrderFilter> = listOf()
    private var previousPosition = -1
    lateinit var filter: KFunction1<Filter, Unit>


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
        holder.filterName.text = items[position].name

        holder.filterItem.setOnClickListener {
            previousPosition = position;
            filter.invoke(items[position].filter)
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