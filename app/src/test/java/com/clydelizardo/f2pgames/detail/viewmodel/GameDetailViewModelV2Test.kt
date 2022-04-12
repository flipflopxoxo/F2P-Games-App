/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.lifecycle.testing.TestLifecycleOwner
import com.clydelizardo.f2pgames.MainCoroutineRule
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetail
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetailResult
import com.clydelizardo.f2pgames.detail.view.GameDetailFragmentArgs
import com.clydelizardo.f2pgames.model.GameDetail
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.awaitFrame
import org.junit.Rule
import org.junit.Test

class GameDetailViewModelV2Test {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get: Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `GIVEN game details is available - WHEN observe details - THEN retrieve game details`() {
        val getGameDetail: GetGameDetail = mockk()
        val gameDetail: GameDetail = mockk()

        coEvery { getGameDetail.invoke(any()) } returns GetGameDetailResult.Success(gameDetail)

        val viewModel = gameDetailViewModelV2(getGameDetail = getGameDetail)

        val lifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.CREATED)

        val isLoadingObserver: Observer<Boolean> = mockk(relaxed = true)
        val detailObserver: Observer<GameDetail> = mockk(relaxed = true)

        viewModel.isLoading.observe(lifecycleOwner, isLoadingObserver)
        viewModel.detail.observe(lifecycleOwner, detailObserver)

        lifecycleOwner.currentState = Lifecycle.State.RESUMED

        verify { detailObserver.onChanged(gameDetail) }
        verify {
            isLoadingObserver.onChanged(false)
            isLoadingObserver.onChanged(true)
        }
    }

    @Test
    fun `GIVEN game details is loading - WHEN observe properties - THEN isLoading`() {
        val lifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.CREATED)

        val getGameDetail: GetGameDetail = mockk()

        coEvery { getGameDetail.invoke(any()) } coAnswers  {
            awaitCancellation()
        }

        val viewModel = gameDetailViewModelV2(getGameDetail = getGameDetail)

        val detailObserver: Observer<GameDetail> = mockk(relaxed = true)
        val isLoadingObserver: Observer<Boolean> = mockk(relaxed = true)

        viewModel.detail.observe(lifecycleOwner, detailObserver)
        viewModel.isLoading.observe(lifecycleOwner, isLoadingObserver)

        lifecycleOwner.currentState = Lifecycle.State.RESUMED

        verify { isLoadingObserver.onChanged(true) }
        verify(inverse = true) { isLoadingObserver.onChanged(false) }
    }

    private fun gameDetailViewModelV2(
        savedStateHandle: SavedStateHandle = GameDetailFragmentArgs(
            gameInfo = mockk()
        ).toSavedStateHandle(),
        getGameDetail: GetGameDetail
    ): GameDetailViewModelV2 {
        return GameDetailViewModelV2(
            savedStateHandle = savedStateHandle,
            getGameDetail = getGameDetail
        )
    }
}