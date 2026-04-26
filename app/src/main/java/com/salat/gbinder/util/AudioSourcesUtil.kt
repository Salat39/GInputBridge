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

internal fun indexInCarouselSources(
    sources: List<MediaCenterConstant.AudioSource>,
    current: MediaCenterConstant.AudioSource
): Int {
    val i = sources.indexOf(current)
    if (i >= 0) return i
    if (current == MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB &&
        sources.contains(MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT) &&
        !sources.contains(MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB)
    ) return sources.indexOf(MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT)
    if (current == MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT &&
        sources.contains(MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB) &&
        !sources.contains(MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT)
    ) return sources.indexOf(MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB)
    return -1
}

internal fun nextCarouselAudioSource(
    sources: List<MediaCenterConstant.AudioSource>,
    rawCurrent: MediaCenterConstant.AudioSource,
    lastKnown: MediaCenterConstant.AudioSource?
): MediaCenterConstant.AudioSource {
    if (sources.isEmpty()) return rawCurrent
    if (sources.size == 1) return sources[0]
    val effective = when (rawCurrent) {
        MediaCenterConstant.AudioSource.AUDIO_SOURCE_UNKNOWN,
        MediaCenterConstant.AudioSource.AUDIO_SOURCE_OTHER -> lastKnown ?: rawCurrent

        else -> rawCurrent
    }
    val idx = indexInCarouselSources(sources, effective)
    if (idx >= 0) {
        return if (idx < sources.lastIndex) {
            sources[idx + 1]
        } else sources[0]
    }
    val fromLast = lastKnown?.let { ln ->
        val li = indexInCarouselSources(sources, ln)
        if (li >= 0) (li + 1) % sources.size else null
    }
    if (fromLast != null) return sources[fromLast]
    if (sources.size > 1) return sources[1]
    return sources[0]
}

internal fun isCarouselNoOpReSelect(
    sources: List<MediaCenterConstant.AudioSource>,
    current: MediaCenterConstant.AudioSource,
    target: MediaCenterConstant.AudioSource,
    lastKnown: MediaCenterConstant.AudioSource?
): Boolean {
    if (target == current) return true
    if (current == MediaCenterConstant.AudioSource.AUDIO_SOURCE_UNKNOWN ||
        current == MediaCenterConstant.AudioSource.AUDIO_SOURCE_OTHER
    ) {
        if (lastKnown != null && target == lastKnown) return true
    }
    val iCur = indexInCarouselSources(sources, current)
    val iTgt = indexInCarouselSources(sources, target)
    if (iCur < 0 || iCur != iTgt) return false
    val hasUsb = current == MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB ||
            target == MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB
    val hasBt = current == MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT ||
            target == MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT
    return hasUsb && hasBt
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
        if (manager.currentAudioSource == target) {
            return true
        }
        delay(AUDIO_SOURCE_SETTLE_DELAY_MS)
    }
    runCatching { requestCarouselAudioSourceForTarget(manager, target) }
        .onFailure { Timber.e(it) }
    repeat(3) { _ ->
        if (manager.currentAudioSource == target) {
            return true
        }
        delay(AUDIO_SOURCE_SETTLE_DELAY_MS)
    }
    return false
}
