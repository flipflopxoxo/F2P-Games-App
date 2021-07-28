package com.clydelizardo.f2pgames.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.clydelizardo.f2pgames.databinding.FragmentGameDetailBinding
import com.clydelizardo.f2pgames.list.repository.api.model.GameDetail
import com.clydelizardo.f2pgames.list.repository.toGameInfo
import java.text.SimpleDateFormat

class GameDetailFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentGameDetailBinding.bind(view)
        val toGameInfo = GameDetail(
            "A free-to-play, co-op action RPG with gameplay similar to Monster Hunter.",
            id = 1,
            title = "Dauntless",
            thumbnail = "https://www.freetogame.com/g/1/thumbnail.jpg",
            gameUrl = "https://www.freetogame.com/g/1/thumbnail.jpg",
            genre = "MMORPG",
            platform = "PC (Windows)",
            publisher = "Phoenix Labs",
            developer = "Phoenix Labs, Iron Galaxy",
            releaseDate = "2019-05-21",
            freetogameProfileUrl = "https://www.freetogame.com/dauntless"
        ).toGameInfo()
        bind.apply {
            Glide.with(this@GameDetailFragment)
                .load(toGameInfo.thumbnail)
                .into(detailThumbnail)
            detailName.text = toGameInfo.name
            detailDescription.text = toGameInfo.description
            detailExtraInfo.text = "Genre: ${toGameInfo.genre}\\nPlatform: ${toGameInfo.platform}\\nPublisher: ${toGameInfo.publisher}\\nDeveloper: ${toGameInfo.developer})}"
        }
    }
}