package com.salat.gbinder.repository

import com.salat.gbinder.statekeeper.domain.entity.AccessibilityServiceSignal
import kotlinx.coroutines.flow.SharedFlow

interface AccessibilityRepository {
    fun setCanAccessibility(value: Boolean)

    fun setVisibleApp(pkg: String, skipHistory: Boolean)

    val actionSignalsFlow: SharedFlow<AccessibilityServiceSignal>
}
