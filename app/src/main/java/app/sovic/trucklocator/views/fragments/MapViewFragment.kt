package app.sovic.trucklocator.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import app.sovic.trucklocator.R
import app.sovic.trucklocator.databinding.FragmentMapViewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapViewFragment : BaseFragment<FragmentMapViewBinding>(R.layout.fragment_map_view),
    GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    override val binding by lazy {   FragmentMapViewBinding.inflate(layoutInflater)}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

 binding.mapView.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        val sydney = LatLng(-33.852, 151.211)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_local_shipping_blue_24dp))
                .title("Marker in Sydney")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


    override fun onBackPressed() {
        TODO("Not yet implemented")
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

}