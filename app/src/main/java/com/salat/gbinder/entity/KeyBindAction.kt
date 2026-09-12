package com.salat.gbinder.entity

import kotlinx.serialization.Serializable

@Serializable
enum class KeyBindAction {
    LAUNCH_APP,
    APP_CAROUSEL,
    NAVI_MEDIA_SWITCH,
    FULLSCREEN_TO_SPLIT,
    LAUNCH_LINK,
    APP_LAUNCHER,
    TOGGLE_DM,
    CAROUSEL_DM,
    PHONE_CALL,
    CAMERAS_360,
    CARPLAY_LAUNCH,
    CAROUSEL_LAMP,
    CAROUSEL_AUDIO_SOURCE,
    TASK_MANAGER,
    RECENTS,
    ANDROID_BACK,
    ANDROID_HOME,
    NIGHT_MODE,
    NAVIGATE_TO_PAST_APP,
    CAR_FUNCTION,
    CAR_FUNCTION_PANEL,
    APP_PANEL,
}
