package com.clydelizardo.f2pgames.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clydelizardo.f2pgames.databinding.FragmentGameListBinding
import com.clydelizardo.f2pgames.list.viewmodel.GameListState
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModel
import com.clydelizardo.f2pgames.model.GameInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class GameListFragment : Fragment() {

    private val viewModel: GameListViewModel by viewModels()
    private var binding: FragmentGameListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameListBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@GameListFragment.viewModel

            recyclerView.apply {
                layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = createAdapter()
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
            refreshLayout.setOnRefreshListener {
                if (!this@GameListFragment.viewModel.refresh()) {
                    refreshLayout.isRefreshing = false
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                when (it) {
                    is GameListState.FailedRefresh -> {
                        Toast.makeText(context, "Failed to refresh", Toast.LENGTH_LONG).show()
                    }
                    is GameListState.FailedUpdate -> {
                        Toast.makeText(
                            context,
                            "Failed to update favorite states",
                            Toast.LENGTH_LONG
                        ).show()
                        viewModel.removeFailedState()
                    }
                    GameListState.Failure -> {
                        Toast.makeText(context, "Failed to retrieve games", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createAdapter(): GameListAdapter {
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
        return gameListAdapter
    }
}