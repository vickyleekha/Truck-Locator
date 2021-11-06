package app.sovic.trucklocator.views.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sovic.trucklocator.data.model.Data
import app.sovic.trucklocator.databinding.ItemListViewBinding
import app.sovic.trucklocator.utils.ConvertSectoDay
import app.sovic.trucklocator.utils.CustomFilter
import app.sovic.trucklocator.utils.viewGone
import app.sovic.trucklocator.utils.viewVisible

class TruckListAdapter(var items:MutableList<Data>,val context: Context) :
   RecyclerView.Adapter<TruckListAdapter.ViewHolder>(), Filterable {


    var filter: CustomFilter?=null
    inner class ViewHolder(val itemBinding: ItemListViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Data) {
            itemBinding.run {
                truckNumber.text = item.truckNumber
                truckSpeed.text = item.lastWaypoint.speed.toString()
                daysCount.text = ConvertSectoDay(item.lastWaypoint.createTime)
                if (item.lastWaypoint.updateTime > 14400000000000 )
                {
                    cardView.setBackgroundColor(Color.RED)
                }
                else{
                    cardView.setBackgroundColor(Color.WHITE)
                }
                truckStatus.apply {
                    text =
                        "${if (item.lastRunningState.truckRunningState == 0) "Stopped" else "Running"} since last  ${
                            ConvertSectoDay(item.lastRunningState.stopStartTime)
                        }"
                }
                truckSpeed.apply { if (item.lastRunningState.truckRunningState == 0) viewGone() else viewVisible() }
                speedMeasure.apply { if (item.lastRunningState.truckRunningState == 0) viewGone() else viewVisible() }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        itemBinding = ItemListViewBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items[position])
    }


//    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Data>() {
//        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
//            TODO("Not yet implemented")
//        }
//
//        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
//            TODO("Not yet implemented")
//        }
//
//    }

    override fun getFilter(): Filter {
        if (filter == null) {
            var filterLis=items

            filter = CustomFilter(filterLis,this)
        }
        return filter as CustomFilter
    }

    override fun getItemCount(): Int {
       return items.size
    }

}