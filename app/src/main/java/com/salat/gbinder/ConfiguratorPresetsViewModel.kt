package com.salat.gbinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salat.gbinder.adb.data.entity.AdbConnectionState
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.entity.CarModel
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfiguratorPresetsViewModel @Inject constructor(
    private val car: CarRepository,
    private val stateKeeper: StateKeeperRepository,
    private val adbRepository: AdbRepository,
) : ViewModel() {

    private val _warningVolume = MutableStateFlow(if (BuildConfig.DEBUG) 538771713 else null)
    val warningVolume = _warningVolume.asStateFlow()

    private val _isAtlas = MutableStateFlow(ModelHelper.detectCarModel() == CarModel.ATLAS)
    val isAtlas = _isAtlas.asStateFlow()

    private val _rearWiperAuto = MutableStateFlow<Boolean?>(null)
    val rearWiperAuto = _rearWiperAuto.asStateFlow()

    // Null - status is unknown because ADB is not connected
    private val _aotCompileState = MutableStateFlow<AotCompileState?>(null)
    val aotCompileState = _aotCompileState.asStateFlow()

    private val _toastMessage = MutableSharedFlow<Int>()
    val toastMessage = _toastMessage.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            adbRepository.connectionState.collect { state ->
                if (_aotCompileState.value == AotCompileState.COMPILING) return@collect
                _aotCompileState.update {
                    if (state is AdbConnectionState.Connected) readAotCompileState() else null
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            if (!BuildConfig.DEBUG) {
                _warningVolume.update {
                    car.getIntProperty(CarPropertyKey.SETTING_FUNC_SOUND_WARNING_VOLUME)
                }
            }
            if (_isAtlas.value) {
                refreshRearWiperAutoState()
            }
        }
    }

    fun atlasWheelSettings() = viewModelScope.launch(Dispatchers.IO) {
        if (!_isAtlas.value) return@launch
        adbRepository.setAtlasWheelSettings()
    }

    fun setFuncCustomKey(key: Int) = viewModelScope.launch(Dispatchers.IO) {
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

    fun setrearWiperAuto(enabled: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        if (!_isAtlas.value) return@launch
        val value = if (enabled) 1 else 0
        val ok = car.setPropertyIntValue(
            CarPropertyKey.SETTING_FUNC_AUTO_REAR_WIPING,
            Integer.MIN_VALUE,
            value
        )
        if (ok) {
            _rearWiperAuto.update { enabled }
        }
    }

    fun compileAppToNative() = viewModelScope.launch(Dispatchers.IO) {
        when (_aotCompileState.value) {
            AotCompileState.COMPILED -> {
                _toastMessage.emit(R.string.app_aot_compile_already_done)
                return@launch
            }

            AotCompileState.COMPILING -> {
                _toastMessage.emit(R.string.app_aot_compile_in_progress)
                return@launch
            }

            null -> {
                _toastMessage.emit(R.string.app_aot_compile_no_adb)
                return@launch
            }

            AotCompileState.NOT_COMPILED -> Unit
        }

        _aotCompileState.update { AotCompileState.COMPILING }
        // Detached - dex2oat runs for minutes and must not hold the shared ADB command lock
        adbRepository.execute(
            "(setsid nohup cmd package compile -m speed -f ${BuildConfig.APPLICATION_ID} >/dev/null 2>&1 &)"
        )
        _toastMessage.emit(R.string.app_aot_compile_started)

        // ponytail: fixed poll budget, the badge falls back to the real status when dex2oat is slower
        repeat(AOT_COMPILE_POLL_COUNT) {
            delay(AOT_COMPILE_POLL_INTERVAL_MS)
            if (readAotCompileState() == AotCompileState.COMPILED) {
                _aotCompileState.update { AotCompileState.COMPILED }
                return@launch
            }
        }
        _aotCompileState.update { readAotCompileState() }
    }

    private suspend fun readAotCompileState(): AotCompileState? {
        val status = adbRepository.execute(
            "dumpsys package ${BuildConfig.APPLICATION_ID} | grep -m1 status="
        )
        return when {
            status.contains("[status=speed]") -> AotCompileState.COMPILED
            status.contains("status=") -> AotCompileState.NOT_COMPILED
            else -> null
        }
    }

    private suspend fun refreshRearWiperAutoState() {
        val value = car.getIntProperty(CarPropertyKey.SETTING_FUNC_AUTO_REAR_WIPING)
        _rearWiperAuto.update {
            when (value) {
                0 -> false
                1 -> true
                else -> null
            }
        }
    }

    companion object {
        private const val AOT_COMPILE_POLL_INTERVAL_MS = 10_000L
        private const val AOT_COMPILE_POLL_COUNT = 30
    }
}

enum class AotCompileState { NOT_COMPILED, COMPILING, COMPILED }
