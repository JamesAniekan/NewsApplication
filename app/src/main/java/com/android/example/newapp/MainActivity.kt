package com.android.example.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.example.newapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Bind the layouts
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //Get the drawer layout
        drawerLayout = binding.drawerLayout
        //Get the navigation controller
       val navController = this.findNavController(R.id.nav_host_fragment)
        //Set drawerLayout to navController
       NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        //Set side bar navigation view to navigation controller
        NavigationUI.setupWithNavController(binding.sideBar, navController)

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}