package app.sovic.trucklocator.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import app.sovic.trucklocator.R
import app.sovic.trucklocator.databinding.FragmentListViewBinding
import app.sovic.trucklocator.viewModels.TruckViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListViewFragment : BaseFragment<FragmentListViewBinding>(R.layout.fragment_list_view){

    override val binding by lazy {   FragmentListViewBinding.inflate(layoutInflater)}

    private val truckViewModel:TruckViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            val storiesData =truckViewModel.getDataList()

            storiesData.observe(viewLifecycleOwner, { value ->
//                        if (!value.data.i) {
                            val dataList = value.data
                        Log.d("DATA",dataList.toString())

//                            storiesPagerAdapter = StoriesPagerAdapter(this, dataList)
//                            view_pager_stories.adapter = storiesPagerAdapter
//
//                            startPreCaching(dataList)
//                        }
            })
        }
    }

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
}