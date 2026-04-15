package com.salat.gbinder.statekeeper.domain.entity

sealed class AccessibilityServiceSignal() {
    data object GoHome : AccessibilityServiceSignal()

    data object GoBack : AccessibilityServiceSignal()
}
