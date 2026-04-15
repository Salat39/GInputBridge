package com.salat.gbinder

import com.salat.gbinder.entity.KeyBindPattern
import com.salat.gbinder.entity.PackagesChangedEvent
import com.salat.gbinder.entity.ToggleMediaControl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Crutch shit
 */
object GlobalState {
    val logState = MutableStateFlow<List<Pair<Long, String>>>(emptyList())

    val setAudioSourceFlow = MutableSharedFlow<Pair<String, String>>()
    val toggleMediaControlFlow = MutableSharedFlow<ToggleMediaControl>()
    val devicePackagesChangedFlow = MutableSharedFlow<PackagesChangedEvent>()
    val requestPlaybackInfoFlow = MutableSharedFlow<Boolean>()
    val requestPhoneCallFlow = MutableSharedFlow<String>()
    val requestPhoneAnswerFlow = MutableSharedFlow<Boolean>()
    val requestPhoneRejectFlow = MutableSharedFlow<Boolean>()
    val requestPhoneDisconnectFlow = MutableSharedFlow<Boolean>()
    val backupVisiblePackageFlow = MutableSharedFlow<String>()
    val tempDisableMediaControlFlow = MutableSharedFlow<Int>()

    val keyBindingMode = MutableStateFlow(false)
    val keyBindingFlow = MutableSharedFlow<KeyBindPattern>()
}
