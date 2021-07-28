package com.clydelizardo.f2pgames

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.clydelizardo.f2pgames.databinding.ActivityMainBinding
import com.clydelizardo.f2pgames.di.core.DaggerAppComponent
import com.clydelizardo.f2pgames.list.view.GameListAdapter
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder()
            .build()
            .inject(this)
        val viewModel: GameListViewModel by viewModels {
            viewModelFactory
        }

        val view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        view.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val gameListAdapter = GameListAdapter()
        view.recyclerView.adapter = gameListAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.start()
        }

        lifecycleScope.launchWhenResumed {
            viewModel.listOfGames.collect {
                gameListAdapter.submitList(it)
            }
        }
    }
}