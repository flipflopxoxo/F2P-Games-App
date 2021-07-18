package com.clydelizardo.f2pgames.list.viewmodel.view

import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import junit.framework.TestCase
import org.junit.Test
import java.util.*

class GameListViewModelTest : TestCase() {
    @Test
    fun default_instantiate_retrieveListOfGames() {
        val gameList = listOf(
            GameInfo(
                "name1",
                "desc1",
                Date(1L),
                "FPS Shooter",
                "PC (Windows)",
                "https://google.com",
                false
            )
        )
        val viewModel = GameListViewModel({
            GetFreeGamesResult.Success(gameList)
        })

        assertEquals(
            gameList,
            viewModel.listOfGames
        )
    }
}