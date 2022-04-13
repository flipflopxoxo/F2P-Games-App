package com.clydelizardo.f2pgames

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clydelizardo.f2pgames.databinding.ActivityMainBinding
import com.clydelizardo.f2pgames.detail.view.GameDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        viewBinding.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            toolbar.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, navDestination, bundle ->
                toolbar.title = if (navDestination.id == R.id.gameDetailFragment) {
                    bundle?.run {
                        val gameInfo = GameDetailFragmentArgs.fromBundle(this).gameInfo
                        gameInfo.name
                    }
                } else {
                    navDestination.label
                }
            }
        }
    }
}