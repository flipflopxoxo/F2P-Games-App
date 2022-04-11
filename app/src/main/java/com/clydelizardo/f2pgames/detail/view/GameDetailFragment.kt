package com.clydelizardo.f2pgames.detail.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.clydelizardo.f2pgames.R
import com.clydelizardo.f2pgames.databinding.FragmentGameDetailBinding
import com.clydelizardo.f2pgames.databinding.FragmentGameDetailV2Binding
import com.clydelizardo.f2pgames.detail.viewmodel.DetailState
import com.clydelizardo.f2pgames.detail.viewmodel.GameDetailViewModel
import com.clydelizardo.f2pgames.detail.viewmodel.GameDetailViewModelV2
import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.util.toDisplayFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class GameDetailFragment : Fragment() {

    val viewModel: GameDetailViewModelV2 by viewModels()
    private var binding: FragmentGameDetailV2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentGameDetailV2Binding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@GameDetailFragment.viewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}