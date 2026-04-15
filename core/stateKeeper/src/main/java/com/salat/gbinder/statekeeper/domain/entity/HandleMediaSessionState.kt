package com.salat.gbinder.statekeeper.domain.entity

data class HandleMediaSessionState(
    val isMediaControlEnabled: Boolean = false,
    val isDataTranslatorEnabled: Boolean = false
)
