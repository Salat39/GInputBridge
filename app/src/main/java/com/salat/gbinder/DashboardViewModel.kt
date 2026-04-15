package com.salat.gbinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val car: CarRepository
) : ViewModel() {

    private val _speedValue = MutableStateFlow(0f)
    val speedValue = _speedValue.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                _speedValue.value = car.getSensorValue(CarPropertyKey.SENSOR_TYPE_CAR_SPEED) * 3.6f
                delay(100L)
            }
        }
    }
}
