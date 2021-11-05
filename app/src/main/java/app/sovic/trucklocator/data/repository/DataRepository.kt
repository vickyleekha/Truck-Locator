package app.sovic.trucklocator.data.repository

import app.sovic.trucklocator.data.Mock
import app.sovic.trucklocator.data.model.Response
import javax.inject.Inject

class DataRepository @Inject constructor(private val mock: Mock) {
    fun getTruckData(): Response {
        return mock.loadMockData()
    }
}