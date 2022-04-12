/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.testing.TestLifecycleOwner
import com.clydelizardo.f2pgames.MainCoroutineRule
import com.clydelizardo.f2pgames.list.usecase.ChangeGameFavoriteStatus
import com.clydelizardo.f2pgames.list.usecase.FavoriteStatusResult
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.util.Status
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import org.junit.Rule
import org.junit.Test


class GameListViewModelV2Test {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get: Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `GIVEN game list is available - WHEN observing properties - THEN properties are updated`() {
        val lifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.CREATED)

        val getFreeGames: GetFreeGames = mockk()
        val freeGamesList: List<GameInfo> = mockk()
        val freeGamesResult: GetFreeGamesResult = GetFreeGamesResult.Success(freeGamesList)

        coEvery { getFreeGames.invoke() } returns freeGamesResult

        val viewModel = gameListViewModelV2(getFreeGames = getFreeGames)

        val (isLoadingObserver, favoriteUpdateResultObserver, listObserver) = getObservers()

        viewModel.favoriteUpdateResult.observe(lifecycleOwner, favoriteUpdateResultObserver)
        viewModel.isLoading.observe(lifecycleOwner, isLoadingObserver)
        viewModel.list.observe(lifecycleOwner, listObserver)

        lifecycleOwner.currentState = Lifecycle.State.RESUMED

        verify(ordering = Ordering.ORDERED) {
            isLoadingObserver.onChanged(true)
            isLoadingObserver.onChanged(false)
        }
        verify {
            listObserver.onChanged(freeGamesList)
        }
        verify(inverse = true) {
            favoriteUpdateResultObserver.onChanged(any())
        }
    }

    @Test
    fun `GIVEN game list is loading - WHEN observing properties - THEN only isLoading is updated`() {
        val lifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.CREATED)

        val getFreeGames: GetFreeGames = mockk()

        coEvery { getFreeGames.invoke() } coAnswers {
            awaitCancellation()
        }

        val viewModel = gameListViewModelV2(getFreeGames = getFreeGames)

        val (isLoadingObserver, favoriteUpdateResultObserver, listObserver) = getObservers()

        viewModel.favoriteUpdateResult.observe(lifecycleOwner, favoriteUpdateResultObserver)
        viewModel.isLoading.observe(lifecycleOwner, isLoadingObserver)
        viewModel.list.observe(lifecycleOwner, listObserver)

        lifecycleOwner.currentState = Lifecycle.State.RESUMED

        verify {
            isLoadingObserver.onChanged(true)
        }
        verify(inverse = true) {
            isLoadingObserver.onChanged(false)
            listObserver.onChanged(any())
            favoriteUpdateResultObserver.onChanged(any())
        }
    }

    @Test
    fun `GIVEN game list is available AND observing properties - WHEN updating favorite status AND update is successful - THEN properties and favoriteUpdateResult are updated`() {
        val lifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.CREATED)

        val getFreeGames: GetFreeGames = mockk()
        val gameInfo = gameInfo()
        val freeGamesList: List<GameInfo> = listOf(gameInfo)
        val freeGamesResult: GetFreeGamesResult = GetFreeGamesResult.Success(freeGamesList)

        coEvery { getFreeGames.invoke() } returns freeGamesResult

        val changeGameFavoriteStatus: ChangeGameFavoriteStatus = mockk()
        coEvery { changeGameFavoriteStatus.invoke(any(), any()) } coAnswers {
            val arg = arg<GameInfo>(0)
            FavoriteStatusResult.Success(arg.copy(isFavorite = arg(1)))
        }
        val viewModel = gameListViewModelV2(
            getFreeGames = getFreeGames,
            changeGameFavoriteStatus = changeGameFavoriteStatus
        )

        val (isLoadingObserver, favoriteUpdateResultObserver, listObserver) = getObservers()

        viewModel.favoriteUpdateResult.observe(lifecycleOwner, favoriteUpdateResultObserver)
        viewModel.isLoading.observe(lifecycleOwner, isLoadingObserver)
        viewModel.list.observe(lifecycleOwner, listObserver)

        lifecycleOwner.currentState = Lifecycle.State.RESUMED

        val updatedGameInfo = gameInfo.copy(isFavorite = true)
        val updatedFreeGamesList: List<GameInfo> = listOf(updatedGameInfo)
        val updatedFreeGamesResult: GetFreeGamesResult =
            GetFreeGamesResult.Success(updatedFreeGamesList)

        coEvery { getFreeGames.invoke() } returns updatedFreeGamesResult

        viewModel.toggleFavoriteState(gameInfo = gameInfo())

        verify(ordering = Ordering.ORDERED) {
            isLoadingObserver.onChanged(true)
            isLoadingObserver.onChanged(false)
            isLoadingObserver.onChanged(true)
            isLoadingObserver.onChanged(false)
        }
        verify(ordering = Ordering.ORDERED) {
            listObserver.onChanged(freeGamesList)
            listObserver.onChanged(updatedFreeGamesList)
        }
        verify {
            favoriteUpdateResultObserver.onChanged(FavoriteStatusResult.Success(updatedGameInfo))
        }
    }


    @Test
    fun `GIVEN game list is available AND observing properties - WHEN updating favorite status AND update fails - THEN properties and favoriteUpdateResult are updated`() {
        val lifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.CREATED)

        val getFreeGames: GetFreeGames = mockk()
        val gameInfo = gameInfo()
        val freeGamesList: List<GameInfo> = listOf(gameInfo)
        val freeGamesResult: GetFreeGamesResult = GetFreeGamesResult.Success(freeGamesList)

        coEvery { getFreeGames.invoke() } returns freeGamesResult

        val changeGameFavoriteStatus: ChangeGameFavoriteStatus = mockk()
        coEvery { changeGameFavoriteStatus.invoke(any(), any()) } returns FavoriteStatusResult.Failure
        val viewModel = gameListViewModelV2(
            getFreeGames = getFreeGames,
            changeGameFavoriteStatus = changeGameFavoriteStatus
        )

        val (isLoadingObserver, favoriteUpdateResultObserver, listObserver) = getObservers()

        viewModel.favoriteUpdateResult.observe(lifecycleOwner, favoriteUpdateResultObserver)
        viewModel.isLoading.observe(lifecycleOwner, isLoadingObserver)
        viewModel.list.observe(lifecycleOwner, listObserver)

        lifecycleOwner.currentState = Lifecycle.State.RESUMED

        viewModel.toggleFavoriteState(gameInfo = gameInfo())

        verify(ordering = Ordering.ORDERED) {
            isLoadingObserver.onChanged(true)
            isLoadingObserver.onChanged(false)
        }
        verify(ordering = Ordering.ORDERED) {
            listObserver.onChanged(freeGamesList)
        }
        verify {
            favoriteUpdateResultObserver.onChanged(FavoriteStatusResult.Failure)
        }
    }

    private fun gameInfo() = GameInfo(
        id = 1,
        name = "Dauntless",
        description = "description",
        genre = "genre",
        releaseDate = null,
        platform = "platform",
        link = "link",
        isFavorite = false,
        thumbnail = "thumbnail",
        publisher = "publisher",
        developer = "developer"
    )

    private fun getObservers(): GameListObservers {
        return GameListObservers()
    }

    private fun gameListViewModelV2(
        getFreeGames: GetFreeGames = mockk(),
        changeGameFavoriteStatus: ChangeGameFavoriteStatus = mockk()
    ): GameListViewModelV2 {
        return GameListViewModelV2(
            getFreeGames = getFreeGames,
            changeGameFavoriteStatus = changeGameFavoriteStatus
        )
    }

    private data class GameListObservers(
        val isLoadingObserver: Observer<Boolean> = mockk(relaxed = true),
        val favoriteUpdateResultObserver: Observer<FavoriteStatusResult> = mockk(relaxed = true),
        val listObserver: Observer<List<GameInfo>> = mockk(relaxed = true)
    )
}