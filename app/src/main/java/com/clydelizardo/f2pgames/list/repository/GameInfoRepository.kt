package com.clydelizardo.f2pgames.list.repository

import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface GameInfoRepository {
    suspend fun getListOfGames(): Result<List<GameInfo>>
}