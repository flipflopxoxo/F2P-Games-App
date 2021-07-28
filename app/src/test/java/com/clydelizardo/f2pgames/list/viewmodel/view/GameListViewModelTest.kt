package com.clydelizardo.f2pgames.list.viewmodel.view

import com.clydelizardo.f2pgames.MainCoroutineRule
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

class GameListViewModelTest {
    @ExperimentalCoroutinesApi
    @get: Rule
    val mainRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    @Test
    fun default_instantiate_retrieveListOfGames() = runBlockingTest {
        val gameList = listOf(
            GameInfo(
                "1",
                "name1",
                "desc1",
                Date(1L),
                "FPS Shooter",
                "PC (Windows)",
                "https://google.com",
                false
            )
        )
        val viewModel = GameListViewModel {
            GetFreeGamesResult.Success(gameList)
        }
        viewModel.start()

        val first = viewModel.listOfGames.value
        assertEquals(
            gameList,
            first
        )
    }
}