package com.salat.gbinder.entity

import kotlinx.serialization.Serializable

@Serializable
enum class KeyBindAction {
    LAUNCH_APP,
    LAUNCH_LINK,
    APP_LAUNCHER,
    TOGGLE_DM,
    CAROUSEL_DM,
    PHONE_CALL,
    CAMERAS_360,
    CAROUSEL_LAMP,
    TASK_MANAGER,
    ANDROID_BACK,
    ANDROID_HOME,
    NAVIGATE_TO_PAST_APP,
}
