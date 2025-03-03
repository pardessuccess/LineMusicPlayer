package com.pardess.musicplayer.di

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import com.pardess.musicplayer.data.datasource.database.MIGRATION_1_2
import com.pardess.musicplayer.data.datasource.database.MusicDatabase
import com.pardess.musicplayer.data.datasource.database.dao.FavoriteDao
import com.pardess.musicplayer.data.datasource.database.dao.HistoryDao
import com.pardess.musicplayer.data.datasource.database.dao.PlayCountDao
import com.pardess.musicplayer.data.datasource.database.dao.PlaylistDao
import com.pardess.musicplayer.data.datasource.database.dao.SearchDao
import com.pardess.musicplayer.data.datasource.database.dao.SongDao
import com.pardess.musicplayer.data.datasource.datastore.UserPreferences
import com.pardess.musicplayer.data.datasource.datastore.UserPreferencesImpl
import com.pardess.musicplayer.data.repository.ManageRepositoryImpl
import com.pardess.musicplayer.data.repository.MusicRepositoryImpl
import com.pardess.musicplayer.data.repository.PlaylistRepositoryImpl
import com.pardess.musicplayer.data.repository.PrefRepositoryImpl
import com.pardess.musicplayer.data.repository.SearchRepositoryImpl
//import com.pardess.musicplayer.data.service.notification.PlaybackNotificationOver24
import com.pardess.musicplayer.domain.repository.ManageRepository
import com.pardess.musicplayer.domain.repository.MusicRepository
import com.pardess.musicplayer.domain.repository.PlaylistRepository
import com.pardess.musicplayer.domain.repository.PrefRepository
import com.pardess.musicplayer.domain.repository.SearchRepository
import com.pardess.musicplayer.domain.usecase.media_player.MediaPlayerListenerUseCase
import com.pardess.musicplayer.domain.usecase.media_player.MediaPlayerListenerUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }

    @Provides
    @Singleton
    fun providesDatabase(app: Application): MusicDatabase {
        return Room.databaseBuilder(app, MusicDatabase::class.java, MusicDatabase.NAME)
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun providesSongDao(database: MusicDatabase) = database.songDao()

    @Provides
    fun providesPlaylistDao(database: MusicDatabase) = database.playlistDao()

    @Provides
    fun providesHistoryDao(database: MusicDatabase) = database.historyDao()

    @Provides
    fun providesFavoriteDao(database: MusicDatabase) = database.favoriteDao()

    @Provides
    fun providesPlayCountDao(database: MusicDatabase) = database.playCountDao()

    @Provides
    fun providesSearchDao(database: MusicDatabase) = database.searchDao()

    @Provides
    @Singleton
    fun providesSearchRepository(
        searchDao: SearchDao
    ): SearchRepository {
        return SearchRepositoryImpl(searchDao)
    }

    @Provides
    @Singleton
    fun providesManageRepository(
        favoriteDao: FavoriteDao,
        historyDao: HistoryDao,
        playCountDao: PlayCountDao,
        songDao: SongDao
    ): ManageRepository {
        return ManageRepositoryImpl(
            favoriteDao,
            historyDao,
            playCountDao,
            songDao
        )
    }

    @Provides
    @Singleton
    fun providesPlaylistRepository(
        playlistDao: PlaylistDao
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(playlistDao)
    }

    @Provides
    @Singleton
    fun providesMusicRepository(
        @ApplicationContext context: Context,
    ): MusicRepository {
        return MusicRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun providesPrefRepository(
        userPreferences: UserPreferences
    ): PrefRepository {
        return PrefRepositoryImpl(userPreferences)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferencesImpl(context)
    }
}