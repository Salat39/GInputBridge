package com.salat.gbinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfiguratorPresetsViewModel @Inject constructor(
    private val car: CarRepository,
    private val stateKeeper: StateKeeperRepository,
    private val adbRepository: AdbRepository,
    private val dataStore: DataStoreRepository
) : ViewModel() {

    private val _warningVolume = MutableStateFlow(if (BuildConfig.DEBUG) 538771713 else null)
    val warningVolume = _warningVolume.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (!BuildConfig.DEBUG) {
                _warningVolume.update {
                    car.getIntProperty(CarPropertyKey.SETTING_FUNC_SOUND_WARNING_VOLUME)
                }
            }
        }
    }

    private suspend fun shouldRunAtlasAdbStep(): Boolean {
        val adbEnabled = dataStore.getValueFlow(GeneralPrefs.ENABLE_ADB_HELPER, false).first()
        val adbPort = dataStore.getValueFlow(GeneralPrefs.ADB_HELPER_PORT, 5555).first()
        return adbEnabled && adbPort == 5555
    }

    fun warmUpAdbSessionIfNeeded() = viewModelScope.launch(Dispatchers.IO) {
        if (shouldRunAtlasAdbStep()) {
            // Warm up session early when entering system params.
            adbRepository.execute(":")
        }
    }

    fun setFuncCustomKey(key: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (shouldRunAtlasAdbStep()) {
            adbRepository.execute("""settings put system wheel_settings "1"""")
        }
        stateKeeper.setFunCustomKey(key)
    }

    fun setWarningVolume(value: Int) = viewModelScope.launch(Dispatchers.IO) {
        car.setPropertyIntValue(
            CarPropertyKey.SETTING_FUNC_SOUND_WARNING_VOLUME,
            Integer.MIN_VALUE,
            value
        )
        _warningVolume.update { value }
    }
}
