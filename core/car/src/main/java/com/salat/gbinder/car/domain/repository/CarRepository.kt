package com.salat.gbinder.car.domain.repository

import com.salat.gbinder.car.domain.entity.PropertyStatus
import kotlinx.coroutines.flow.StateFlow

interface CarRepository {
    val driveModeStateFlow: StateFlow<Pair<Int, Int>>

    val ignitionStateFlow: StateFlow<Int>

    fun create()

    suspend fun getSupportStatus(propertyId: Int, type: Int): PropertyStatus

    suspend fun setPropertyIntValue(propertyId: Int, zone: Int, value: Int): Boolean

    suspend fun setPropertyFloatValue(propertyId: Int, zone: Int, value: Float): Boolean

    suspend fun getPropertyValuesWithType(propertyId: Int, type: Int): Map<Int, Pair<Int, Float>>

    suspend fun getPropertySupportedValuesWithType(propertyId: Int, type: Int): Map<Int, Set<Int>>

    suspend fun getIntProperty(propertyId: Int): Int

    suspend fun getSensorValue(sensorId: Int): Float

    fun getIntPropertyWithType(propertyId: Int, type: Int): Int
}
