package app.sovic.trucklocator.utils

import android.widget.Filter
import app.sovic.trucklocator.data.model.Data
import app.sovic.trucklocator.views.adapters.TruckListAdapter
import kotlin.collections.ArrayList

class CustomFilter(filterList: MutableList<Data>, adapter: TruckListAdapter) : Filter() {
    var adapter: TruckListAdapter
    var filterList:  MutableList<Data>

    //FILTERING OCURS
    override fun performFiltering(constraint: CharSequence): FilterResults {
        var constraint: CharSequence? = constraint
        val results = FilterResults()

        //CHECK CONSTRAINT VALIDITY
        if (constraint != null && constraint.length > 0) {
            //CHANGE TO UPPER
            constraint = constraint.toString().toUpperCase()
            //STORE OUR FILTERED PLAYERS
            val filteredFeatures: ArrayList<Data> = ArrayList<Data>()
            for (i in filterList.indices) {
                //CHECK
                if (filterList[i].truckNumber.toUpperCase().contains(constraint)) {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredFeatures.add(filterList[i])
                }
            }
            results.count = filteredFeatures.size
            results.values = filteredFeatures
        } else {
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults) {
        adapter.items = results.values as ArrayList<Data>

        //REFRESH
        adapter.notifyDataSetChanged()
    }

    init {
        this.adapter = adapter
        this.filterList = filterList
    }
}