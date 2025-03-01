package com.pardess.musicplayer.data.datasource.database.dao

import androidx.room.*
import com.pardess.musicplayer.data.entity.HistoryEntity
import com.pardess.musicplayer.data.entity.PlayCountEntity
import com.pardess.musicplayer.data.entity.join.HistorySong
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryEntity)

    @Delete
    suspend fun deleteHistory(history: HistoryEntity)

    @Query("DELETE FROM HistoryEntity WHERE timestamp = :timestamp")
    suspend fun deleteHistoryByTimestamp(timestamp: Long)

    @Query("SELECT * FROM HistoryEntity WHERE songId = :songId")
    suspend fun getHistoryBySongId(songId: Long): HistoryEntity?

    @Query("UPDATE HistoryEntity SET timestamp = :timestamp WHERE songId = :songId")
    suspend fun updateHistoryTimestamp(songId: Long, timestamp: Long)
//
//    @Transaction
//    suspend fun insertOrUpdateHistory(songId: Long, timestamp: Long) {
//        insertHistory(HistoryEntity(id = 0, songId = songId, timestamp = timestamp)) // ✅ 없으면 추가
//    }

    @Query(
        """
        SELECT SongEntity.*, HistoryEntity.timestamp 
        FROM HistoryEntity
        INNER JOIN SongEntity ON HistoryEntity.songId = SongEntity.id
        ORDER BY HistoryEntity.timestamp DESC
    """
    )
    fun getHistorySongs(): Flow<List<HistorySong>>
}