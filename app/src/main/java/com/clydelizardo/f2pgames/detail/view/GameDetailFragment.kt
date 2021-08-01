package com.clydelizardo.f2pgames.detail.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.clydelizardo.f2pgames.R
import com.clydelizardo.f2pgames.databinding.FragmentGameDetailBinding
import com.clydelizardo.f2pgames.detail.viewmodel.DetailState
import com.clydelizardo.f2pgames.detail.viewmodel.GameDetailViewModel
import com.clydelizardo.f2pgames.di.core.DaggerAppComponent
import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.util.toDisplayFormat
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

const val GAME_INFO = "game_info"

class GameDetailFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: GameDetailViewModel by viewModels(factoryProducer = { viewModelFactory })

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
        return FragmentGameDetailBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentGameDetailBinding.bind(view)
        val gameInfo: GameInfo? = arguments?.get(GAME_INFO) as GameInfo
        if (gameInfo != null) {
            viewModel.setGame(gameInfo)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.detail.collect { detailState ->
                when (detailState) {
                    DetailState.Failure -> {
                        // TODO()
                    }
                    DetailState.Loading -> {
                        // TODO()
                    }
                    is DetailState.Success -> {
                        bindDetailToView(binding, detailState.detail)
                    }
                }
            }
        }
        if (gameInfo != null) {

        }
    }

    private fun bindDetailToView(
        bind: FragmentGameDetailBinding,
        gameDetail: GameDetail
    ) {
        bind.apply {
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
        }
    }

    private fun getLocale() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0]
    } else {
        resources.configuration.locale
    }

}