package com.pardess.musicplayer.domain.usecase.main

import com.pardess.musicplayer.data.entity.join.FavoriteSong
import com.pardess.musicplayer.data.entity.join.HistorySong
import com.pardess.musicplayer.data.entity.join.PlayCountSong
import com.pardess.musicplayer.domain.repository.ManageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MainDetailUseCase {
    fun getFavoriteSongs(): Flow<List<FavoriteSong>>
    suspend fun deleteFavoriteSong(songId: Long)

    fun getHistorySongs(): Flow<List<HistorySong>>
    suspend fun resetHistory()

    fun getPlayCountSongs(): Flow<List<PlayCountSong>>
}

class MainDetailUseCaseImpl @Inject constructor(
    private val repository: ManageRepository
) : MainDetailUseCase {
    override fun getFavoriteSongs(): Flow<List<FavoriteSong>> {
        return repository.getFavoriteSongs()
    }

    override suspend fun deleteFavoriteSong(songId: Long) {
        withContext(Dispatchers.IO) {
            repository.removeFavorite(songId)
        }
    }

    override fun getHistorySongs(): Flow<List<HistorySong>> {
        return repository.getHistorySongs()
    }

    override suspend fun resetHistory() {
        withContext(Dispatchers.IO) {
            repository.resetHistory()
        }
    }

    override fun getPlayCountSongs(): Flow<List<PlayCountSong>> {
        return repository.getPlayCountSongs()
    }
}