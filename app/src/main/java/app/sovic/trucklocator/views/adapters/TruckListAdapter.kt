package app.sovic.trucklocator.views.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sovic.trucklocator.ConvertSectoDay
import app.sovic.trucklocator.R
import app.sovic.trucklocator.data.model.Data
import app.sovic.trucklocator.databinding.ItemListViewBinding
import app.sovic.trucklocator.getString

class TruckListAdapter(val context: Context) :
    ListAdapter<Data, TruckListAdapter.ViewHolder>(DiffUtil()) {
    inner class ViewHolder(val itemBinding: ItemListViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Data) {
            itemBinding.run {
                truckNumber.text = item.truckNumber
                truckSpeed.text = item.lastWaypoint.speed.toString()
                daysCount.text = ConvertSectoDay(item.lastWaypoint.createTime)
                if (item.lastWaypoint.ignitionOn.equals(true))
                {
                    cardView.setBackgroundColor(Color.RED)
                }
                else{
                    cardView.setBackgroundColor(Color.WHITE)
                }
                truckStatus.apply {
                    text =
                        "${if (item.lastRunningState.truckRunningState == 0) "Stopped" else "Running"}       since last  ${
                            ConvertSectoDay(item.lastRunningState.stopStartTime)
                        }"
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        itemBinding = ItemListViewBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            TODO("Not yet implemented")
        }

    }

}