package com.atlas.ride.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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
        AppBarConfiguration(setOf(), main_drawer_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_content_toolbar)
        setupActionBarWithNavController(controller, configuration)

        main_drawer_view.setupWithNavController(controller)
        main_drawer_view.setNavigationItemSelectedListener {
            main_drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
//        if (controller.currentDestination?.id == controller.graph.startDestination)
//            supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return (controller.navigateUp(configuration) || super.onSupportNavigateUp())
    }
}
