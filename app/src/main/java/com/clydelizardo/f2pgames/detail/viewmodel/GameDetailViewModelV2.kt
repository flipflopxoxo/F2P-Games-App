package com.clydelizardo.f2pgames.detail.viewmodel

import androidx.lifecycle.*
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetail
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetailResult
import com.clydelizardo.f2pgames.detail.view.GameDetailFragment
import com.clydelizardo.f2pgames.detail.view.GameDetailFragmentArgs
import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModelV2 @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getGameDetail: GetGameDetail
) :
    ViewModel() {
    val detailResult = liveData {
        val gameInfo = GameDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).gameInfo

        emit(DetailState.Loading)
        emit(
            when (val detailResult = getGameDetail(gameInfo)) {
                is GetGameDetailResult.Success -> {
                    DetailState.Success(detailResult.value)
                }
                GetGameDetailResult.Failure -> {
                    DetailState.Failure
                }
            }
        )
    }
    val detail = MediatorLiveData<GameDetail>().apply {
        addSource(detailResult) {
            if (it is DetailState.Success) {
                value = it.detail
            }
        }
    }
    val isLoading = detailResult.map {
        it is DetailState.Loading
    }

    sealed class DetailState {
        class Success(val detail: GameDetail) : DetailState()
        object Loading : DetailState()
        object Failure : DetailState()
    }
}