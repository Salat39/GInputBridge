package com.salat.gbinder.entity

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.salat.gbinder.R

@Immutable
data class DisplayAudioSource(
    val key: String,
    @StringRes val displayTitle: Int,
    @StringRes val description: Int
)

val DISPLAY_AUDIO_SOURCES: List<DisplayAudioSource>
    get() = listOf(
        DisplayAudioSource(
            key = "ONLINE",
            displayTitle = R.string.audio_source_online,
            description = R.string.audio_source_online_desc
        ),
        DisplayAudioSource(
            key = "BT",
            displayTitle = R.string.audio_source_bt,
            description = R.string.audio_source_bt_desc
        ),
        DisplayAudioSource(
            key = "RADIO",
            displayTitle = R.string.audio_source_radio,
            description = R.string.audio_source_radio_desc
        ),
        DisplayAudioSource(
            key = "USB",
            displayTitle = R.string.audio_source_usb,
            description = R.string.audio_source_usb_desc
        ),
        /* DisplayAudioSource(
            key = "CPAA",
            displayTitle = R.string.audio_source_cpaa,
            description = R.string.audio_source_cpaa_desc
        ) */
    )
