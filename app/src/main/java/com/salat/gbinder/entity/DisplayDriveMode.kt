package com.salat.gbinder.entity

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.salat.gbinder.R

@Immutable
data class DisplayDriveMode(
    val id: Int,
    val originalName: String,
    val displayName: String,
    val notifSample: Int = -1,
    val notifVolume: Float = 1f,
    @StringRes val description: Int = 0
)

val DISPLAY_DRIVE_MODES: List<DisplayDriveMode>
    get() = listOf(
        // Base
        DisplayDriveMode(
            id = 570491158,
            originalName = "DRIVE_MODE_SELECTION_ADAPTIVE",
            displayName = "Smart",
            description = R.string.drive_mode_adaptive_desc
        ),
        DisplayDriveMode(
            id = 570491139,
            originalName = "DRIVE_MODE_SELECTION_DYNAMIC",
            displayName = "Sport",
            description = R.string.drive_mode_dynamic_desc
        ),
        DisplayDriveMode(
            id = 570491138,
            originalName = "DRIVE_MODE_SELECTION_COMFORT",
            displayName = "Comfort",
            description = R.string.drive_mode_comfort_desc
        ),
        DisplayDriveMode(
            id = 570491137,
            originalName = "DRIVE_MODE_SELECTION_ECO",
            displayName = "Eco",
            description = R.string.drive_mode_eco_desc
        ),

        // Power
        DisplayDriveMode(
            id = 570491144,
            originalName = "DRIVE_MODE_SELECTION_POWER",
            displayName = "Power",
            description = R.string.drive_mode_power_desc
        ),
        DisplayDriveMode(
            id = 570491157,
            originalName = "DRIVE_MODE_SPORT_PLUS",
            displayName = "Sport Plus",
            description = R.string.drive_mode_sport_plus_desc
        ),

        // Off-road & low-μ
        DisplayDriveMode(
            id = 570491146,
            originalName = "DRIVE_MODE_SELECTION_MUD",
            displayName = "Mud",
            description = R.string.drive_mode_mud_desc
        ),
        DisplayDriveMode(
            id = 570491155,
            originalName = "DRIVE_MODE_SELECTION_OFFROAD",
            displayName = "Offroad",
            description = R.string.drive_mode_offroad_desc
        ),
        DisplayDriveMode(
            id = 570491147,
            originalName = "DRIVE_MODE_SELECTION_ROCK",
            displayName = "Rock",
            description = R.string.drive_mode_rock_desc
        ),
        DisplayDriveMode(
            id = 570491149,
            originalName = "DRIVE_MODE_SELECTION_SAND",
            displayName = "Sand",
            description = R.string.drive_mode_sand_desc
        ),
        DisplayDriveMode(
            id = 570491145,
            originalName = "DRIVE_MODE_SELECTION_SNOW",
            displayName = "Snow",
            description = R.string.drive_mode_snow_desc
        ),
        // Assist function
        DisplayDriveMode(
            id = 570491141,
            originalName = "DRIVE_MODE_SELECTION_HDC",
            displayName = "HDC",
            description = R.string.drive_mode_hdc_desc
        ),

        // Baseline "Custom"
        DisplayDriveMode(
            id = 570491200,
            originalName = "DRIVE_MODE_SELECTION_CUSTOM",
            displayName = "Custom",
            description = R.string.drive_mode_custom_desc
        ),
        // Baseline "Normal"
        DisplayDriveMode(
            id = 570491153,
            originalName = "DRIVE_MODE_SELECTION_NORMAL",
            displayName = "Normal",
            description = R.string.drive_mode_normal_desc
        ),

        // Driveline / traction
        DisplayDriveMode(
            id = 570491150,
            originalName = "DRIVE_MODE_SELECTION_AWD",
            displayName = "AWD",
            description = R.string.drive_mode_awd_desc
        ),

        // Electrified (HEV/PHEV)
        DisplayDriveMode(
            id = 570491143,
            originalName = "DRIVE_MODE_SELECTION_HYBRID",
            displayName = "Hybrid",
            description = R.string.drive_mode_hybrid_desc
        ),
        DisplayDriveMode(
            id = 570491142,
            originalName = "DRIVE_MODE_SELECTION_PURE",
            displayName = "Pure",
            description = R.string.drive_mode_pure_desc
        ),
        DisplayDriveMode(
            id = 570491151,
            originalName = "DRIVE_MODE_SELECTION_SAVE",
            displayName = "Save",
            description = R.string.drive_mode_save_desc
        ),
        DisplayDriveMode(
            id = 570491152,
            originalName = "DRIVE_MODE_SELECTION_ECO_HEV_PHEV",
            displayName = "Eco (HEV/PHEV)",
            description = R.string.drive_mode_eco_hev_phev_desc
        ),
        DisplayDriveMode(
            id = 570491148,
            originalName = "DRIVE_MODE_SELECTION_PHEV",
            displayName = "PHEV",
            description = R.string.drive_mode_phev_desc
        ),
        DisplayDriveMode(
            id = 570491154,
            originalName = "DRIVE_MODE_SELECTION_EAWD",
            displayName = "EAWD",
            description = R.string.drive_mode_eawd_desc
        ),

        // Special / unclear
        // Note: All START_TYPE* map to the same generic description string.
        // If you later split them, add separate string resources and rewire here.
        /*
        DisplayDriveMode(
            id = 570491159,
            originalName = "DRIVE_MODE_SELECTION_START_TYPE18",
            displayName = "Start Type 18",
            description = R.string.drive_mode_start_type_desc
        ),
        DisplayDriveMode(
            id = 570491160,
            originalName = "DRIVE_MODE_SELECTION_START_TYPE72",
            displayName = "Start Type 72",
            description = R.string.drive_mode_start_type_desc
        ),
        DisplayDriveMode(
            id = 570491161,
            originalName = "DRIVE_MODE_SELECTION_START_TYPE79",
            displayName = "Start Type 79",
            description = R.string.drive_mode_start_type_desc
        ),
        DisplayDriveMode(
            id = 570491162,
            originalName = "DRIVE_MODE_SELECTION_START_TYPE97",
            displayName = "Start Type 97",
            description = R.string.drive_mode_start_type_desc
        ),

        DisplayDriveMode(
            id = 570491140,
            originalName = "DRIVE_MODE_SELECTION_XC",
            displayName = "XC",
            description = R.string.drive_mode_xc_desc
        ) */
    )
