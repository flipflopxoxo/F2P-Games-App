package com.clydelizardo.f2pgames.detail.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.clydelizardo.f2pgames.R
import com.clydelizardo.f2pgames.databinding.FragmentGameDetailBinding
import com.clydelizardo.f2pgames.detail.viewmodel.DetailState
import com.clydelizardo.f2pgames.detail.viewmodel.GameDetailViewModel
import com.clydelizardo.f2pgames.di.core.BaseFragment
import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.util.toDisplayFormat
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

class GameDetailFragment : BaseFragment() {

    val viewModel: GameDetailViewModel by viewModels()

    val args: GameDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGameDetailBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentGameDetailBinding.bind(view)

        val gameInfo: GameInfo = args.gameInfo
        viewModel.setGame(gameInfo)

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.detail.collect { detailState ->
                when (detailState) {
                    DetailState.Failure -> {
                        // TODO()
                    }
                    DetailState.Loading -> {
                        binding.circularProgress.show()
                        binding.circularProgress.progress = 40
                        binding.detailGroup.visibility = View.GONE
                    }
                    is DetailState.Success -> {
                        bindDetailToView(binding, detailState.detail)
                    }
                }
            }
        }
    }

    private fun bindDetailToView(
        bind: FragmentGameDetailBinding,
        gameDetail: GameDetail
    ) {
        bind.apply {
            detailGroup.visibility = View.VISIBLE
            gameDetail.screenshotUrls.firstOrNull()?.let {
                detailThumbnail.visibility = View.VISIBLE
                Glide.with(this@GameDetailFragment)
                    .load(it)
                    .into(detailThumbnail)
            } ?: run {
                detailThumbnail.visibility = View.GONE
            }

            detailName.text = gameDetail.title
            detailDescription.text = gameDetail.description
            detailExtraInfo.text = resources.getString(
                R.string.game_details_spec,
                gameDetail.genre,
                gameDetail.platform,
                gameDetail.publisher,
                gameDetail.developer,
                gameDetail.releaseDate.toDisplayFormat(getLocale())
            )
            circularProgress.hide()
        }
    }

    private fun getLocale() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0]
    } else {
        resources.configuration.locale
    }
}