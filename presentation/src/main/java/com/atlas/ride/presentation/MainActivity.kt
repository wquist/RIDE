package com.atlas.ride.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val controller: NavController by lazy {
        findNavController(R.id.main_content_fragment)
    }
    private val configuration: AppBarConfiguration by lazy {
        AppBarConfiguration(main_drawer_view.menu, main_drawer_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(controller, configuration)
        main_drawer_view.setupWithNavController(controller)

        refreshHomeState()
    }

    override fun onSupportNavigateUp(): Boolean {
        val result = controller.navigateUp(configuration) || super.onSupportNavigateUp()

        // Like in [onCreate], the initial fragment must be handled specially (no navigation
        // button). Note that this must occur after the controller has navigated, such that
        // [currentDesination] will point to the correct view ID.
        refreshHomeState()

        return result
    }

    private fun refreshHomeState() {
        // The initial fragment destination is not part of the drawer menu, so neither the hamburger
        // or the back arrow should be displayed.
        if (with(controller) { currentDestination?.id == graph.startDestination })
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
