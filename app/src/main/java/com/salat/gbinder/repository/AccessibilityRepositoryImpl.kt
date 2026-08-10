package com.salat.gbinder.repository

import com.salat.gbinder.statekeeper.domain.entity.AccessibilityServiceSignal
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import kotlinx.coroutines.flow.SharedFlow

class AccessibilityRepositoryImpl(private val stateKeeper: StateKeeperRepository) :
    AccessibilityRepository {

    override fun setCanAccessibility(value: Boolean) = stateKeeper.setCanAccessibility(value)

    override fun setVisibleApp(pkg: String, skipHistory: Boolean) =
        stateKeeper.setVisibleApp(pkg, skipHistory)

    override val actionSignalsFlow: SharedFlow<AccessibilityServiceSignal> =
        stateKeeper.accessibilityServiceSignalFlow
}
