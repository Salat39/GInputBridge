package com.salat.gbinder.util

import android.content.Context
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant
import com.salat.gbinder.entity.DISPLAY_AUDIO_SOURCES
import com.salat.gbinder.mappers.asString
import kotlinx.coroutines.delay
import timber.log.Timber

private const val AUDIO_SOURCE_SETTLE_DELAY_MS = 120L
private const val AUDIO_SOURCE_SETTLE_MAX_ATTEMPTS = 6

internal fun nextCarouselAudioSource(
    sources: List<MediaCenterConstant.AudioSource>,
    current: MediaCenterConstant.AudioSource
): MediaCenterConstant.AudioSource {
    if (sources.isEmpty()) return current

    val currentIndex = sources.indexOf(current)
    val nextIndex = if (currentIndex == -1 || currentIndex == sources.lastIndex) 0 else {
        currentIndex + 1
    }
    return sources[nextIndex]
}

internal fun Context.getAudioSourceDisplayLabel(source: MediaCenterConstant.AudioSource): String {
    val key = source.asString()
    return DISPLAY_AUDIO_SOURCES.find { it.key == key }?.let { getString(it.displayTitle) } ?: key
}

internal fun requestCarouselAudioSourceForTarget(
    manager: MediaCenterManager,
    target: MediaCenterConstant.AudioSource
) {
    if (target == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE) {
        manager.requestAudioSource(
            target,
            MediaCenterConstant.AppSource.WECARFLOW
        )
    } else manager.requestAudioSource(target)
}

internal suspend fun waitForCarouselAudioSourceToSettle(
    manager: MediaCenterManager,
    target: MediaCenterConstant.AudioSource
): Boolean {
    repeat(AUDIO_SOURCE_SETTLE_MAX_ATTEMPTS) { _ ->
        if (manager.currentAudioSource == target) return true
        delay(AUDIO_SOURCE_SETTLE_DELAY_MS)
    }
    runCatching { requestCarouselAudioSourceForTarget(manager, target) }
        .onFailure { Timber.e(it) }
    repeat(3) { _ ->
        if (manager.currentAudioSource == target) return true
        delay(AUDIO_SOURCE_SETTLE_DELAY_MS)
    }
    return false
}
