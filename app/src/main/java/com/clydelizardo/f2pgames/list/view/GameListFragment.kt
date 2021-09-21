package com.clydelizardo.f2pgames.list.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clydelizardo.f2pgames.databinding.FragmentGameListBinding
import com.clydelizardo.f2pgames.list.viewmodel.GameListState
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModel
import com.clydelizardo.f2pgames.model.GameInfo
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GameListFragment : Fragment(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GameListViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGameListBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentGameListBinding.bind(view)

        val gameListAdapter = GameListAdapter()
        gameListAdapter.selectionListener = object : OnGameSelected {
            override fun onGameSelected(game: GameInfo) {
                val context = context
                if (context is OnGameSelected) {
                    context.onGameSelected(game)
                }
            }
        }
        gameListAdapter.longPressListener = object : LongPressListener {
            override fun onLongPress(gameInfo: GameInfo) {
                viewModel.toggleFavoriteState(gameInfo)
            }
        }
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = gameListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        binding.refreshLayout.setOnRefreshListener {
            if (!viewModel.refresh()) {
                binding.refreshLayout.isRefreshing = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                when (it) {
                    is GameListState.FailedRefresh -> {
                        gameListAdapter.submitList(it.gameList)
                        binding.refreshLayout.isEnabled = false
                        binding.refreshLayout.isRefreshing = false
                        Toast.makeText(context, "Failed to refresh", Toast.LENGTH_LONG).show()
                        viewModel.removeFailedState()
                    }
                    is GameListState.FailedUpdate -> {
                        gameListAdapter.submitList(it.gameList)
                        binding.refreshLayout.isEnabled = false
                        binding.refreshLayout.isRefreshing = false
                        Toast.makeText(
                            context,
                            "Failed to update favorite states",
                            Toast.LENGTH_LONG
                        ).show()
                        viewModel.removeFailedState()
                    }
                    GameListState.Failure -> {
                        gameListAdapter.submitList(emptyList())
                        binding.refreshLayout.isEnabled = true
                        binding.refreshLayout.isRefreshing = false
                        Toast.makeText(context, "Failed to retrieve games", Toast.LENGTH_SHORT)
                            .show()
                    }
                    GameListState.Loading -> {
                        gameListAdapter.submitList(emptyList())
                        binding.refreshLayout.isEnabled = true
                        binding.refreshLayout.isRefreshing = true
                    }
                    is GameListState.Refreshing -> {
                        gameListAdapter.submitList(it.gameList)
                        binding.refreshLayout.isEnabled = true
                        binding.refreshLayout.isRefreshing = true
                    }
                    is GameListState.Success -> {
                        gameListAdapter.submitList(it.gameList)
                        binding.refreshLayout.isEnabled = true
                        binding.refreshLayout.isRefreshing = false
                    }
                    is GameListState.Updating -> {
                        gameListAdapter.submitList(it.gameList)
                        binding.refreshLayout.isEnabled = true
                        binding.refreshLayout.isRefreshing = true
                    }
                }
            }
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}