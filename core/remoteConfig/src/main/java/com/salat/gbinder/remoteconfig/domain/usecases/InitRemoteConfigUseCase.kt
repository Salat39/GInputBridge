package com.salat.gbinder.remoteconfig.domain.usecases

import com.salat.gbinder.remoteconfig.domain.repository.RemoteConfigRepository

class InitRemoteConfigUseCase(private val repository: RemoteConfigRepository) {
    fun execute() = repository.init()
}
