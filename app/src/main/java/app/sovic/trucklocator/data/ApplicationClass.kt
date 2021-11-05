package app.sovic.trucklocator.data

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {


    override fun onCreate() {
        super.onCreate()
    }



    init {
        instance = this
    }

    companion object {

        private var instance: ApplicationClass? = null

        fun getInstance() : ApplicationClass {
            return instance as ApplicationClass
        }
    }








}