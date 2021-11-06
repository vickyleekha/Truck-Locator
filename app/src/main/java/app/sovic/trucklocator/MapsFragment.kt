package app.sovic.trucklocator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import app.sovic.trucklocator.data.model.Data
import app.sovic.trucklocator.databinding.FragmentMapsBinding
import app.sovic.trucklocator.viewModels.TruckViewModel
import app.sovic.trucklocator.views.fragments.BaseFragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapsFragment :Fragment(){
//    BaseFragment<FragmentMapsBinding>(R.layout.fragment_maps) {


//    override val binding by lazy {  FragmentMapsBinding.inflate(layoutInflater) }

    private val truckViewModel: TruckViewModel by viewModels()

    private var locationArrayList: MutableList<Data> = ArrayList()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(g).icon(bitmapDescriptorFromVector(requireContext(),R.drawable.ic_local_shipping_black_24dp)).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        // inside on map ready method
        // we will be displaying all our markers.
        // for adding markers we are running for loop and
        // inside that we are drawing marker on our map.
        // inside on map ready method
        // we will be displaying all our markers.
        // for adding markers we are running for loop and
        // inside that we are drawing marker on our map.
        for (i in 0 until locationArrayList.size) {
            val latLng =
                LatLng(locationArrayList[i].lastWaypoint.lat, locationArrayList[i].lastWaypoint.lng)

            if (locationArrayList[i].lastRunningState.truckRunningState == 1) {
                val latLng1 = latLng
                googleMap.addMarker(
                    MarkerOptions().position(latLng1).icon(
                        bitmapDescriptorFromVector(
                            requireContext(),
                            R.drawable.ic_local_shipping_green_24dp
                        )
                    ).title("Running Truck")
                )
            } else if (locationArrayList[i].lastRunningState.truckRunningState == 0) {
                if (locationArrayList[i].lastWaypoint.ignitionOn) {
                    val latLngYellow = latLng
                    googleMap.addMarker(
                        MarkerOptions().position(latLngYellow).icon(
                            bitmapDescriptorFromVector(
                                requireContext(),
                                R.drawable.ic_local_shipping_yellow_24dp
                            )
                        ).title(" Idle Truck")
                    )
                } else if (!locationArrayList[i].lastWaypoint.ignitionOn) {
                    val latLngBlue = latLng
                    googleMap.addMarker(
                        MarkerOptions().position(latLngBlue).icon(
                            bitmapDescriptorFromVector(
                                requireContext(),
                                R.drawable.ic_local_shipping_blue_24dp
                            )
                        ).title("Stopped Truck")
                    )
                }
            }

            //iska correct logic find krna hai
            else if (locationArrayList[i].lastRunningState.stopStartTime > 240) {
                val latLngRed = latLng
                // below line is use to add marker to each location of our array list.
                googleMap.addMarker(
                    MarkerOptions().position(latLngRed).icon(
                        bitmapDescriptorFromVector(
                            requireContext(),
                            R.drawable.ic_local_shipping_red_24dp
                        )
                    ).title("Marker")
                )

            }



            // below lin is use to zoom our camera on map.
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))

            // below line is use to move our camera to the specific location.
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }


    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        val storiesData = truckViewModel.getDataList()

        storiesData.observe(viewLifecycleOwner, { value ->
//                        if (!value.data.i) {
            locationArrayList = value.data

            Log.d("DATA", locationArrayList.toString())

        })

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_location -> {
                moveTo()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    private fun moveTo() {
        view?.findNavController()?.navigate(R.id.action_mapsFragment_to_listFragment)
    }



//    override fun onBackPressed() {
//      requireActivity().finish()
//    }


}