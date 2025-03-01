package com.pardess.musicplayer.data.repository


import com.pardess.musicplayer.data.datasource.datastore.UserPreferenceKeys.REPEAT_MODE_KEY
import com.pardess.musicplayer.data.datasource.datastore.UserPreferenceKeys.SHUFFLE_MODE_KEY
import com.pardess.musicplayer.data.datasource.datastore.UserPreferences
import com.pardess.musicplayer.domain.repository.PrefRepository
import kotlinx.coroutines.flow.Flow

class PrefRepositoryImpl(
    private val userPreferences: UserPreferences
) : PrefRepository {
    override suspend fun setRepeatMode(value: Int) {
        userPreferences.setData(REPEAT_MODE_KEY, value)
    }

    override fun getRepeatMode(): Flow<Int> {
        return userPreferences.getData(REPEAT_MODE_KEY, 0)
    }

    override suspend fun setShuffleMode(value: Boolean) {
        userPreferences.setData(SHUFFLE_MODE_KEY, value)
    }

    override fun getShuffleMode(): Flow<Boolean> {
        return userPreferences.getData(SHUFFLE_MODE_KEY, false)
    }
}