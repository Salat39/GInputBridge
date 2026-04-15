package com.salat.gbinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfiguratorPresetsViewModel @Inject constructor(
    private val car: CarRepository,
    private val stateKeeper: StateKeeperRepository
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
}
