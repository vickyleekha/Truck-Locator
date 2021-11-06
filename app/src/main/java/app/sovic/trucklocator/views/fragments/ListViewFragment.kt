package app.sovic.trucklocator.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import app.sovic.trucklocator.R
import app.sovic.trucklocator.data.model.Data
import app.sovic.trucklocator.databinding.FragmentListViewBinding
import app.sovic.trucklocator.viewModels.TruckViewModel
import app.sovic.trucklocator.views.adapters.TruckListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListViewFragment : BaseFragment<FragmentListViewBinding>(R.layout.fragment_list_view) {

    override val binding by lazy { FragmentListViewBinding.inflate(layoutInflater) }

    private val truckViewModel: TruckViewModel by viewModels()
    private var dataList:MutableList<Data> = ArrayList ()
    private val truckListAdapter by lazy { TruckListAdapter(dataList,requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setHasOptionsMenu(true)
           search()
        binding.run {
            included.ivLocation.setOnClickListener {
                moveTo()

            }
            recyclerViewList.adapter = truckListAdapter
            val storiesData = truckViewModel.getDataList()

            storiesData.observe(viewLifecycleOwner, { value ->
//                        if (!value.data.i) {
                dataList.addAll(value.data)
                Log.d("DATA", dataList.toString())

//                            storiesPagerAdapter = StoriesPagerAdapter(this, dataList)
//                            view_pager_stories.adapter = storiesPagerAdapter
//
//                            startPreCaching(dataList)
//                        }
            })
        }

    }


    //for searching local data
    private fun search() {
        binding.included.mSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                //FILTER AS YOU TYPE
              truckListAdapter.getFilter().filter(query)
                return false
            }
        })

    }



//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.maps-> {
//
//                moveTo()
//                true
//            }
//           else ->
//        super.onOptionsItemSelected(item)
//    }
//}

    private fun moveTo() {

        view?.findNavController()?.navigate(R.id.action_listFragment_to_mapsFragment)
    }


    override fun onBackPressed() {
       requireActivity().finish()
    }
}