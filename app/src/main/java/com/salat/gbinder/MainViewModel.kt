package com.salat.gbinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salat.gbinder.car.domain.entity.IdType
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.entity.DisplayAppUpdate
import com.salat.gbinder.entity.DisplayPropertyItem
import com.salat.gbinder.entity.UiDownloadState
import com.salat.gbinder.features.launcher.BACKUP_DIVIDER
import com.salat.gbinder.features.launcher.LauncherDataRepository
import com.salat.gbinder.filedownloader.domain.usecases.ClearDownloadedFilesUseCase
import com.salat.gbinder.filedownloader.domain.usecases.DownloadFileUseCase
import com.salat.gbinder.mappers.toDisplay
import com.salat.gbinder.mappers.toUi
import com.salat.gbinder.remoteconfig.domain.usecases.GetAppUpdateFlowUseCase
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.util.decodeBase64Jvm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stateKeeper: StateKeeperRepository,
    private val car: CarRepository,
    private val launcher: LauncherDataRepository, // todo make via uc
    // private val guard: SignRepository,
    private val getAppUpdateFlowUseCase: GetAppUpdateFlowUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val clearDownloadedFilesUseCase: ClearDownloadedFilesUseCase
) : ViewModel() {
    private val _canAccessibility = MutableStateFlow(false)
    val canAccessibility = _canAccessibility.asStateFlow()

    private val _bindsImport = MutableStateFlow("")
    val bindsImport = _bindsImport.asStateFlow()

    private val _settingsImport = MutableStateFlow("")
    val settingsImport = _settingsImport.asStateFlow()

    private val _iconsImport = MutableStateFlow("")
    val iconsImport = _iconsImport.asStateFlow()

    private val _appUpdateInfo = MutableStateFlow<DisplayAppUpdate?>(null)
    val appUpdateInfo = _appUpdateInfo.asStateFlow()

    private val _updateDownloadState = MutableStateFlow<UiDownloadState?>(null)
    val updateDownloadState = _updateDownloadState.asStateFlow()

    val configuratorFunctionChangeIntValueFlow = stateKeeper.configuratorIntFunctionValueFlow
    val configuratorFunctionChangeFloatValueFlow = stateKeeper.configuratorFloatFunctionValueFlow
    val configuratorSensorChangeIntValueFlow = stateKeeper.configuratorIntSensorValueFlow
    val configuratorSensorChangeFloatValueFlow = stateKeeper.configuratorFloatSensorValueFlow

    init {
        viewModelScope.launch {
            stateKeeper.canAccessibility.collect { _canAccessibility.emit(it) }
        }
        /* viewModelScope.launch(Dispatchers.Default) {
            if (!BuildConfig.DEBUG) guard.verify()
        } */
        viewModelScope.launch {
            checkAppUpdate()
            launch {
                Timber.d("Temp files deleted: ${clearDownloadedFilesUseCase.execute()}")
            }
        }
    }

    private fun CoroutineScope.checkAppUpdate() = launch {
        getAppUpdateFlowUseCase.flow.collect { (isSuccess, info) ->
            if (!isSuccess || info == null) return@collect

            val versionCode: Int = BuildConfig.VERSION_CODE
            if (info.code > versionCode || BuildConfig.DEBUG) {
                _appUpdateInfo.update { info.toDisplay() }
            }
        }
    }

    fun setBindsImport(value: String) = viewModelScope.launch {
        _bindsImport.emit(value)
    }

    fun setSettingsImport(value: String) = viewModelScope.launch(Dispatchers.Default) {
        runCatching {
            if (value.isEmpty()) {
                _settingsImport.emit("")
                _iconsImport.emit("")
            } else {
                val backupPath = value.split(BACKUP_DIVIDER)
                val baseBackup = backupPath.getOrNull(0).orEmpty()
                val iconsBackup = backupPath.getOrNull(1).orEmpty()

                _iconsImport.emit(iconsBackup)

                val decode = decodeBase64Jvm(baseBackup)
                _settingsImport.emit(decode)
            }
        }.onFailure { Timber.e(it) }
    }

    fun rebuildLauncher() = viewModelScope.launch(Dispatchers.IO) {
        runCatching { launcher.rebuildMyApps() }.onFailure { Timber.e(it) }
    }

    fun playNotifTest(sampleId: Int, volume: Float) = viewModelScope.launch {
        stateKeeper.sendNotifPlayTest(sampleId, volume)
    }

    fun clearUpdateDownloadState() {
        _updateDownloadState.update { null }
    }

    fun downloadUpdate(url: String) = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("Start download: $url")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")
        val timestamp = LocalDateTime.now().format(formatter)
        downloadFileUseCase.execute(url, "${timestamp}_update.apk", "").collect { result ->
            _updateDownloadState.update { result.toUi() }
        }
    }

    fun onResume(packageName: String) {
        stateKeeper.setVisibleApp(packageName, false)
    }

    suspend fun getSupportStatus(propertyId: Int, type: Int) =
        car.getSupportStatus(propertyId, type)

    suspend fun setPropertyIntValue(propertyId: Int, zone: Int, value: Int) =
        car.setPropertyIntValue(propertyId, zone, value)

    suspend fun setPropertyFloatValue(propertyId: Int, zone: Int, value: Float) =
        car.setPropertyFloatValue(propertyId, zone, value)

    suspend fun getPropertySupportedValuesWithType(propertyId: Int, type: Int) =
        car.getPropertySupportedValuesWithType(propertyId, type)

    suspend fun getPropertyValuesWithType(propertyId: Int, type: Int) =
        car.getPropertyValuesWithType(propertyId, type)

    fun setOpenedProperty(property: DisplayPropertyItem?) {
        if (property == null) {
            stateKeeper.setConfiguratorOpenedFunction(null)
            stateKeeper.setConfiguratorOpenedSensor(null)
        } else {
            if (property.type == IdType.ID_TYPE_SENSOR) {
                stateKeeper.setConfiguratorOpenedSensor(property.value)
            } else stateKeeper.setConfiguratorOpenedFunction(property.value)
        }
    }
}
