package com.clydelizardo.f2pgames.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.clydelizardo.f2pgames.databinding.FragmentGameListBinding
import com.clydelizardo.f2pgames.di.core.DaggerAppComponent
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GameListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder()
            .build()
            .inject(this)
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
        val viewModel: GameListViewModel =
            ViewModelProvider(this, viewModelFactory).get(GameListViewModel::class.java)

        val binding = FragmentGameListBinding.bind(view)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val gameListAdapter = GameListAdapter()
        binding.recyclerView.adapter = gameListAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.start()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listOfGames.collect {
                gameListAdapter.submitList(it)
            }
        }
    }
}