package com.clydelizardo.f2pgames

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.clydelizardo.f2pgames.databinding.ActivityMainBinding
import com.clydelizardo.f2pgames.list.view.OnGameSelected
import com.clydelizardo.f2pgames.model.GameInfo

class MainActivity : AppCompatActivity(), OnGameSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
    }

    override fun onGameSelected(game: GameInfo) {
        findNavController(R.id.nav_host_fragment).navigate(NavGraphGameDirections.actionShowGameDetails(game))
    }
}