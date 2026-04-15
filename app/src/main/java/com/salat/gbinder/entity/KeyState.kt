package com.salat.gbinder.entity

import kotlinx.coroutines.Job

data class KeyState(
    var pressState: PressState = PressState.RELEASED,
    var longPosted: Boolean = false,
    var singleTimer: Job? = null,
    var doubleTimer: Job? = null,
    // Remove keyState from current state pool by time task
    var selfDestroyer: Job? = null
)
