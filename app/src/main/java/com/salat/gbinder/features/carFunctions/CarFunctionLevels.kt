package com.salat.gbinder.features.carFunctions

import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.entity.CarFunctionIds

internal const val DEFAULT_TEMP_LO = 16f

internal fun levelSpec(function: CarFunction): Triple<Int, Int, List<Int>>? = when (function) {
    CarFunction.WHEEL_HEAT -> Triple(
        CarPropertyKey.HVAC_FUNC_STEERING_WHEEL_HEAT,
        Integer.MIN_VALUE,
        CarFunctionIds.STEERING_HEAT_LEVELS
    )
    CarFunction.DRIVER_HEAT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_HEATING,
        CarFunctionIds.ZONE_DRIVER,
        CarFunctionIds.SEAT_HEAT_LEVELS
    )
    CarFunction.PASSENGER_HEAT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_HEATING,
        CarFunctionIds.ZONE_PASSENGER,
        CarFunctionIds.SEAT_HEAT_LEVELS
    )
    CarFunction.DRIVER_VENT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION,
        CarFunctionIds.ZONE_DRIVER,
        CarFunctionIds.SEAT_VENT_LEVELS
    )
    CarFunction.PASSENGER_VENT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION,
        CarFunctionIds.ZONE_PASSENGER,
        CarFunctionIds.SEAT_VENT_LEVELS
    )
    CarFunction.LIGHT -> Triple(
        CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL,
        Integer.MIN_VALUE,
        CarFunctionIds.LIGHT_LEVELS
    )
    else -> null
}

internal suspend fun CarRepository.readLevelValue(propertyId: Int, areaId: Int): Int {
    val primary = getIntProperty(propertyId, areaId)
    if (primary != -1) return primary
    if (areaId != Integer.MIN_VALUE) {
        val fallback = getIntProperty(propertyId, Integer.MIN_VALUE)
        if (fallback != -1) return fallback
    }
    return getIntProperty(propertyId)
}

internal suspend fun CarRepository.readTempLimit(propertyId: Int): Float {
    val global = getFloatProperty(propertyId, Integer.MIN_VALUE)
    if (global >= 0f) return global
    return getFloatProperty(propertyId, CarFunctionIds.ZONE_DRIVER)
}

internal fun normalizeLevelValue(function: CarFunction, value: Int): Int = when (function) {
    CarFunction.WHEEL_HEAT -> when (value) {
        1 -> CarFunctionIds.STEERING_HEAT_L1
        2 -> CarFunctionIds.STEERING_HEAT_L2
        3 -> CarFunctionIds.STEERING_HEAT_L3
        CarFunctionIds.STEERING_HEAT_L1,
        CarFunctionIds.STEERING_HEAT_L2,
        CarFunctionIds.STEERING_HEAT_L3 -> value
        else -> 0
    }
    CarFunction.DRIVER_HEAT, CarFunction.PASSENGER_HEAT -> when (value) {
        1 -> CarFunctionIds.SEAT_HEAT_L1
        2 -> CarFunctionIds.SEAT_HEAT_L2
        3 -> CarFunctionIds.SEAT_HEAT_L3
        CarFunctionIds.SEAT_HEAT_L1,
        CarFunctionIds.SEAT_HEAT_L2,
        CarFunctionIds.SEAT_HEAT_L3 -> value
        0x1005020F -> CarFunctionIds.SEAT_HEAT_L3
        else -> 0
    }
    CarFunction.DRIVER_VENT, CarFunction.PASSENGER_VENT -> when (value) {
        1 -> CarFunctionIds.SEAT_VENT_L1
        2 -> CarFunctionIds.SEAT_VENT_L2
        3 -> CarFunctionIds.SEAT_VENT_L3
        CarFunctionIds.SEAT_VENT_L1,
        CarFunctionIds.SEAT_VENT_L2,
        CarFunctionIds.SEAT_VENT_L3 -> value
        0x1005010F -> CarFunctionIds.SEAT_VENT_L3
        else -> 0
    }
    else -> value
}

