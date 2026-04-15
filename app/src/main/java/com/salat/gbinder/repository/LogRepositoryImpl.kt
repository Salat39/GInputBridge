package com.salat.gbinder.repository

import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository

class LogRepositoryImpl(private val stateKeeper: StateKeeperRepository) : LogRepository {
    override fun log(msg: String) {
        stateKeeper.sendLog(msg, false)
    }

    override fun deepLog(msg: String) {
        stateKeeper.sendLog(msg, true)
    }
}
