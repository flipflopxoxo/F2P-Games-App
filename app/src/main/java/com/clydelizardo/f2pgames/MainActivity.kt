package com.clydelizardo.f2pgames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clydelizardo.f2pgames.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
    }
}