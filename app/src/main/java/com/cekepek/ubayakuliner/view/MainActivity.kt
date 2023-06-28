package com.cekepek.ubayakuliner.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cekepek.ubayakuliner.R
import com.cekepek.ubayakuliner.util.Global

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!Global.login){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
//        navController =
//            (supportFragmentManager.findFragmentById(R.id.tutorialFragment) as
//                    NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }


}