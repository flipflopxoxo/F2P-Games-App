package com.clydelizardo.f2pgames.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clydelizardo.f2pgames.NavGraphGameDirections
import com.clydelizardo.f2pgames.databinding.FragmentGameListV2Binding
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModelV2
import com.clydelizardo.f2pgames.model.GameInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameListFragment : Fragment() {

    private val viewModel: GameListViewModelV2 by viewModels()
    private var binding: FragmentGameListV2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameListV2Binding.inflate(layoutInflater, container, false)
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
        }

        viewModel.failures.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Failed to retrieve games", Toast.LENGTH_SHORT)
                .show()
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
                requireView().findNavController().navigate(NavGraphGameDirections.actionShowGameDetails(game))
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