package app.sovic.trucklocator.views.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import app.sovic.trucklocator.R
import app.sovic.trucklocator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navController.addOnDestinationChangedListener(this)
//
//        val fm = supportFragmentManager.beginTransaction()
//        fm.add(app.sovic.trucklocator.R.id.flContainer, MapsFragment())
//            .addToBackStack(null).commit()
    }

   lateinit var menu1:Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu1= menu!!
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main,menu)
        return true
    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.action_listFragment_to_mapsFragment -> {
                menu1.getItem(2).isVisible=false
                onCreateOptionsMenu(menu1)
            }
            R.id.mapsFragment ->{

            }
        }
        }


//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        return super.onPrepareOptionsMenu(menu)
//        menu.
//    }
}