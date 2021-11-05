package app.sovic.trucklocator.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sovic.trucklocator.data.model.Response
import app.sovic.trucklocator.data.model.ResultData
import app.sovic.trucklocator.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class TruckViewModel @Inject constructor (private val dataRepository: DataRepository) : ViewModel() {

 fun getDataList(): MutableLiveData<Response> {
        return MutableLiveData(dataRepository.getTruckData())
        }
}