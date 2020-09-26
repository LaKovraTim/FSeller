package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.AdapterOrderOptionsBinding
import falabella.lakovratim.android.fastseller.domain.model.OrderOptions
import javax.inject.Inject

class OrderOptionsAdapter @Inject constructor() :
RecyclerView.Adapter<OrderOptionsAdapter.ViewHolder>() {

    var items= listOf<OrderOptions>()
    private var previousPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterOrderOptionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val options = items.

        holder.optionItemTitle.text = items[position].title
        holder.imageOptionItem.setImageDrawable( items[position].drawable)

        //holder.optionItemTitle.text = options[1].
        holder.optionItem.setOnClickListener {
            previousPosition = position;
            notifyDataSetChanged();
        }

        if(position == previousPosition){
            holder.optionItem.backgroundTintList = ColorStateList.valueOf(holder.itemView.context.resources.getColor(
                R.color.greenBank,null));
        }
        else {
            holder.optionItem.backgroundTintList = ColorStateList.valueOf(holder.itemView.context.resources.getColor(
                R.color.screenBackground,null));
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(binding: AdapterOrderOptionsBinding) : RecyclerView.ViewHolder(binding.root) {
        val optionItem = binding.optionItem
        val optionItemTitle = binding.optionItemTitle
        val imageOptionItem = binding.imageOptionItem
    }
}