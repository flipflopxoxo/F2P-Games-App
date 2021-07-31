package com.clydelizardo.f2pgames

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.clydelizardo.f2pgames.databinding.ActivityMainBinding
import com.clydelizardo.f2pgames.detail.GAME_INFO
import com.clydelizardo.f2pgames.detail.GameDetailFragment
import com.clydelizardo.f2pgames.list.view.GameListFragment
import com.clydelizardo.f2pgames.list.view.OnGameSelected
import com.clydelizardo.f2pgames.model.GameInfo

class MainActivity : AppCompatActivity(), OnGameSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, GameListFragment())
                .commit()
        }
    }

    override fun onGameSelected(game: GameInfo) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_INFO, game)
                }
            })
            .addToBackStack("detail")
            .commit()
    }
}