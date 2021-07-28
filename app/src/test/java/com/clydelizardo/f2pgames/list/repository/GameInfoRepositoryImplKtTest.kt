package com.clydelizardo.f2pgames.list.repository

import com.clydelizardo.f2pgames.list.repository.api.model.GameDetail
import org.junit.Test

class GameInfoRepositoryImplKtTest {
    @Test
    fun validGameDetail_convertToGameInfo_returnsGameInfo() {
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
            toGameInfo.releaseDate
    }
}