package com.clydelizardo.f2pgames.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.clydelizardo.f2pgames.databinding.FragmentGameDetailBinding
import com.clydelizardo.f2pgames.model.GameInfo

const val GAME_INFO = "game_info"

class GameDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGameDetailBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentGameDetailBinding.bind(view)
        val toGameInfo: GameInfo? = arguments?.get(GAME_INFO) as GameInfo?
        if (toGameInfo != null) {
            bind.apply {
                Glide.with(this@GameDetailFragment)
                    .load(toGameInfo.thumbnail)
                    .into(detailThumbnail)
                detailName.text = toGameInfo.name
                detailDescription.text = toGameInfo.description
                detailExtraInfo.text =
                    "Genre: ${toGameInfo.genre}\nPlatform: ${toGameInfo.platform}\nPublisher: ${toGameInfo.publisher}\nDeveloper: ${toGameInfo.developer})}"
            }
        }
    }
}