package com.salat.gbinder.remoteconfig.domain.usecases

import com.salat.gbinder.remoteconfig.domain.repository.RemoteConfigRepository

class GetAppUpdateFlowUseCase(repository: RemoteConfigRepository) {
    val flow = repository.appUpdateFlow
}
