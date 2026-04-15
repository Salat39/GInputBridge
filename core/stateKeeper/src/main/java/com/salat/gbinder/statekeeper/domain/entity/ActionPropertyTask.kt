package com.salat.gbinder.statekeeper.domain.entity

sealed class ActionPropertyTask {
    data class IntValue(val propertyId: Int, val areaId: Int, val value: Int) :
        ActionPropertyTask()

    data class FloatValue(val propertyId: Int, val areaId: Int, val value: Float) :
        ActionPropertyTask()

    data class GetFunIntValue(val propertyId: Int, val areaId: Int) : ActionPropertyTask()

    data class GetFunFloatValue(val propertyId: Int, val areaId: Int) : ActionPropertyTask()

    data class FunListenValue(val propertyId: Int, val areaId: Int) : ActionPropertyTask()

    data class GetSensorIntValue(val sensorId: Int) : ActionPropertyTask()

    data class GetSensorFloatValue(val sensorId: Int) : ActionPropertyTask()

    data class SensorListenValue(val sensorId: Int) : ActionPropertyTask()

    data class GetInfoIntValue(val infoId: Int) : ActionPropertyTask()

    data class GetInfoFloatValue(val infoId: Int) : ActionPropertyTask()

    data class GetInfoStringValue(val infoId: Int) : ActionPropertyTask()
}
