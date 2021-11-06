package app.sovic.trucklocator.views.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import app.sovic.trucklocator.R
import app.sovic.trucklocator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    lateinit var binding: ActivityMainBinding

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeAsUpIndicator(app.sovic.trucklocator.R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        val fm = supportFragmentManager.beginTransaction()
//        fm.add(app.sovic.trucklocator.R.id.flContainer, MapsFragment())
//            .addToBackStack(null).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(app.sovic.trucklocator.R.menu.main, menu)
        return true
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        TODO("Not yet implemented")
    }

//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        return super.onPrepareOptionsMenu(menu)
//        menu.
//    }
}