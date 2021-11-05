package app.sovic.trucklocator.views.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import app.sovic.trucklocator.databinding.ActivityMainBinding
import app.sovic.trucklocator.views.fragments.ListViewFragment
import dagger.hilt.android.AndroidEntryPoint
import android.R
import android.view.MenuItem
import app.sovic.trucklocator.MapsFragment
import app.sovic.trucklocator.views.fragments.MapViewFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeAsUpIndicator(app.sovic.trucklocator.R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fm = supportFragmentManager.beginTransaction()
        fm.add(app.sovic.trucklocator.R.id.flContainer, MapsFragment())
            .addToBackStack(null).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(app.sovic.trucklocator.R.menu.main, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.actionLocation -> {
//                newGame()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        return super.onPrepareOptionsMenu(menu)
//        menu.
//    }
}