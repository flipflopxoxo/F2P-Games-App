package com.clydelizardo.f2pgames

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.clydelizardo.f2pgames.databinding.ActivityMainBinding
import com.clydelizardo.f2pgames.list.view.GameListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, GameListFragment())
            .commit()
    }
}