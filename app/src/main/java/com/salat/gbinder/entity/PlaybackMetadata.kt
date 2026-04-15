package com.salat.gbinder.entity

class PlaybackMetadata(
    val id: String,
    val packageName: String,
    val appName: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val uri: String,
    val coverUri: String
)
