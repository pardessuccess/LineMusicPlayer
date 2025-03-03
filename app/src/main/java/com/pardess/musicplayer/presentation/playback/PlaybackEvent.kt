package com.pardess.musicplayer.presentation.playback

import com.pardess.musicplayer.domain.model.Song

sealed class PlaybackEvent {
    data class PlaySong(
        val index: Int,
        val playlist: List<Song>
    ) : PlaybackEvent()

    object PlayRandom : PlaybackEvent()
    object PauseSong : PlaybackEvent()
    object ResumeSong : PlaybackEvent()
    object SkipToNextSong : PlaybackEvent()
    object SkipToPreviousSong : PlaybackEvent()
    data class RepeatMode(val repeatMode: Int) : PlaybackEvent()
    data class ShuffleMode(val shuffle: Boolean) : PlaybackEvent()
    data class SeekSongToPosition(val position: Long) : PlaybackEvent()
    object ExpandPanel : PlaybackEvent()
    object Favorite : PlaybackEvent()
}