package com.salat.gbinder.entity

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.salat.gbinder.R

@Immutable
data class DisplayLampMode(
    val id: Int,
    val originalName: String,
    val displayName: String,
    @StringRes val displayTitle: Int = 0,
    @StringRes val description: Int = 0
)

val DISPLAY_LAMP_MODES: List<DisplayLampMode>
    get() = listOf(
        DisplayLampMode(
            id = 0,
            originalName = "LAMP_EXTERIOR_LIGHT_CONTROL_OFF",
            displayName = "Off",
            displayTitle = R.string.headlight_off,
            description = R.string.headlight_off_desc
        ),
        DisplayLampMode(
            id = 537136641,
            originalName = "LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT",
            displayName = "Position",
            displayTitle = R.string.headlight_parking,
            description = R.string.headlight_parking_desc
        ),
        DisplayLampMode(
            id = 537136642,
            originalName = "LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM",
            displayName = "Low beam",
            displayTitle = R.string.headlight_low_beam,
            description = R.string.headlight_low_beam_desc
        ),
        DisplayLampMode(
            id = 537136643,
            originalName = "LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC",
            displayName = "Auto",
            displayTitle = R.string.headlight_auto,
            description = R.string.headlight_auto_desc
        )
    )
