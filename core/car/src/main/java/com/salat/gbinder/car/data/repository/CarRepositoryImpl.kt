@file:Suppress("SameParameterValue")

package com.salat.gbinder.car.data.repository

import android.content.Context
import android.content.Intent
import com.ecarx.xui.adaptapi.ECarXCarProxy
import com.ecarx.xui.adaptapi.FunctionStatus
import com.ecarx.xui.adaptapi.binder.IConnectable
import com.ecarx.xui.adaptapi.car.Car
import com.ecarx.xui.adaptapi.car.ICar
import com.ecarx.xui.adaptapi.car.base.ICarFunction
import com.ecarx.xui.adaptapi.car.sensor.ISensor
import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.entity.IdType
import com.salat.gbinder.car.domain.entity.PropertyStatus
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.statekeeper.domain.entity.ActionPropertyTask
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import ecarx.car.ECarXCar
import ecarx.car.hardware.ECarXCarPropertyValue
import ecarx.car.hardware.signal.CarSignalManager
import ecarx.car.hardware.signal.SignalFilter
import ecarx.car.hardware.vehicle.ECarXCarSetManager
import ecarx.car.hardware.vehicle.ECarXCarVfmiscManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class CarRepositoryImpl(
    private val context: Context,
    private val scope: CoroutineScope,
    private val stateKeeper: StateKeeperRepository
) : CarRepository, ECarXCarProxy.ECarXCarProxyMethod {

    private val _driveModeStateFlow = MutableStateFlow(-1 to -1)
    override val driveModeStateFlow = _driveModeStateFlow.asStateFlow()

    private val _ignitionStateFlow = MutableStateFlow(-1)
    override val ignitionStateFlow = _ignitionStateFlow.asStateFlow()

    // Car watcher
    private var mICar: ICar? = null

    private var mVfmiscMgr: ECarXCarVfmiscManager? = null
    // private var customCarFunction: CustomCarFunction? = null
    // private var mVH: VehicleHelper? = null // alt fun setter, listeners not working

    private var carIsConnected = false
    private var isCreated = false

    private val basePath
        get() = context.packageName

    // "%ID%_%AREA%" listener key
    private val functionListenList = mutableSetOf<String>()

    // sensor id -> (intValue, floatValue)
    private val sensorListenList = mutableMapOf(
        CarPropertyKey.SENSOR_TYPE_IGNITION_STATE to (-1 to -1f)
    )

    private val sensorListener = object : ISensor.ISensorListener {

        /**
         * Sensor INT value changed
         */
        override fun onSensorEventChanged(id: Int, value: Int) {
            // Ignition state flow
            if (id == CarPropertyKey.SENSOR_TYPE_IGNITION_STATE) {
                _ignitionStateFlow.update { value }
            }

            // General sensor listener
            if (id in sensorListenList && sensorListenList[id]?.first != value) {
                sensorListenList[id] = value to (sensorListenList[id]?.second ?: -1f)
                sendSensorEventChanged(id, value)

                stateKeeper.sendLog(
                    "[CAR] Sensor Event Changed id=$id value=$value",
                    true
                )
            }

            if (stateKeeper.configuratorOpenedSensor.value == id) {
                stateKeeper.sendConfiguratorIntSensorValue(id, value)
            }
        }

        /**
         * Sensor FLOAT value changed
         */
        override fun onSensorValueChanged(id: Int, value: Float) {

            if (id in sensorListenList && sensorListenList[id]?.second != value) {
                sensorListenList[id] = (sensorListenList[id]?.first ?: -1) to value
                sendSensorValueChanged(id, value)

                stateKeeper.sendLog(
                    "[CAR] Sensor Value Changed id=$id value=$value",
                    true
                )
            }

            if (stateKeeper.configuratorOpenedSensor.value == id) {
                stateKeeper.sendConfiguratorFloatSensorValue(id, value)
            }
        }

        override fun onSensorSupportChanged(i: Int, functionStatus: FunctionStatus?) {
            // No implementation needed here
        }
    }

    private val functionListener = object : ICarFunction.IFunctionValueWatcher {
        override fun onCustomizeFunctionValueChanged(id: Int, area: Int, value: Float) {

            val key = "${id}_$area"
            if (functionListenList.contains(key)) {
                sendPropertyValueChanged(id, area, value)
                sendPropertyFloatChanged(id, area, value)
            }

            if (stateKeeper.configuratorOpenedFunction.value == id) {
                stateKeeper.sendConfiguratorFloatFunctionValue(id, area, value)
            }

            val zonePart = if (area == Integer.MIN_VALUE) "" else " zone=$area"
            stateKeeper.sendLog(
                "[CAR] Float Fun Changed id=$id$zonePart value=$value",
                true
            )
        }

        override fun onFunctionChanged(id: Int) {
            stateKeeper.sendLog("[CAR] Fun Changed id=$id", true)
        }

        override fun onFunctionValueChanged(id: Int, area: Int, value: Int) {

            val key = "${id}_$area"
            if (functionListenList.contains(key)) {
                sendPropertyValueChanged(id, area, value)
                sendPropertyIntChanged(id, area, value)
            }

            if (CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT == id && _driveModeStateFlow.value.second != value) {
                _driveModeStateFlow.update { _driveModeStateFlow.value.second to value }
            }

            // Update lamp overlay value
            stateKeeper.lampModeInOverlay.value?.let { lampConfig ->
                if (CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL == id) {
                    stateKeeper.setLampModeInOverlay(lampConfig.copy(second = value))
                }
            }

            if (stateKeeper.configuratorOpenedFunction.value == id) {
                stateKeeper.sendConfiguratorIntFunctionValue(id, area, value)
            }

            val zonePart = if (area == Integer.MIN_VALUE) "" else " zone=$area"
            stateKeeper.sendLog(
                "[CAR] Int Fun Changed id=$id$zonePart value=$value",
                true
            )
        }

        override fun onSupportedFunctionStatusChanged(
            id: Int,
            area: Int,
            functionStatus: FunctionStatus?
        ) {
            val zonePart = if (area == Integer.MIN_VALUE) "" else " zone=$area"
            stateKeeper.sendLog(
                "[CAR] Supported Fun Status Changed id=$id$zonePart status=${functionStatus?.name}",
                true
            )
        }

        override fun onSupportedFunctionValueChanged(id: Int, params: IntArray?) {
            stateKeeper.sendLog(
                "[CAR] Supported Fun Value Changed id=$id list=[${params?.joinToString(", ")}]",
                true
            )
        }
    }

    override fun create() {
        // initECarXCar()
        initCarWatcher()

        scope.launch {
            launch { initCarFuncCollector() }

            // Get startup ignition sensor value
            launch {
                _ignitionStateFlow.update {
                    getIntPropertyWithType(
                        propertyId = CarPropertyKey.SENSOR_TYPE_IGNITION_STATE,
                        type = IdType.ID_TYPE_SENSOR
                    )
                }
            }

            // Get startup drive mode
            launch {
                val startupDm = getIntPropertyWithType(
                    propertyId = CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT,
                    type = IdType.ID_TYPE_FUNCTION
                )
                if (_driveModeStateFlow.value.second != startupDm) {
                    _driveModeStateFlow.update { _driveModeStateFlow.value.second to startupDm }
                }
            }
        }

        // TODO TEST ZONE
        /* scope.launch {
            repeat(1000) {
                sendPropertyIntChanged(100, 25, it)
                Timber.d("!!! TRY SEND $it $functionListenList")
                delay(2500)
            }
        } */

        // TODO TEST ZONE
        /* scope.launch {
            delay(2000L)

            getAllProperty().forEach { item ->
                if (item.type == IdType.ID_TYPE_SENSOR) {
                    val key = item.value
                    if (!sensorListenList.contains(key)) {
                        sensorListenList[key] = 0f
                        key.setSensorListener()
                    }
                }
            }
        }

        // TODO TEST ZONE
        listOf(
            ISensor.SENSOR_TYPE_RPM,
            ISensor.SENSOR_TYPE_CAR_SPEED,
            ISensor.SENSOR_TYPE_CAR_SPEED_FROM_IPK,
            ISensor.SENSOR_TYPE_CAR_SPEED_ACCELERATION,
        ).forEach { id ->
            if (!sensorListenList.contains(id)) {
                sensorListenList[id] = 0f
                id.setRatedSensorListener()
            }
        }*/
    }

    private fun initECarXCar(): Boolean {
        runCatching {
            // Build custom fun
            /* customCarFunction = CustomCarFunction(
                context = context,
                moduleId = ICarFunction.CAR_MODULE_VENDOR,
                log = { stateKeeper.sendLog(it, false) }
            ) */

            // Init proxy
            val xCarProxy = ECarXCarProxy(context, this)
            xCarProxy.initECarXCar()

            val mECarXCarSetManager: ECarXCarSetManager =
                xCarProxy.eCarXCar.getCarManager("car_publicattribute") as ECarXCarSetManager
            @Suppress("UsePropertyAccessSyntax")
            mVfmiscMgr = mECarXCarSetManager.getECarXCarVfmiscManager()

            stateKeeper.sendLog("[ECarXCarService] Init", false)
            return true
        }.onFailure {
            stateKeeper.sendLog("[ECarXCarService] Failure ${it.message}", false)
        }
        return false
    }

    /**
     * ECarXCarProxy connected
     */
    override fun onECarXCarServiceConnected(
        eCarXCar: ECarXCar?,
        carSignalManager: CarSignalManager?
    ) {
        runCatching {
            carSignalManager?.registerCallback(object : CarSignalManager.CarSignalEventCallback {
                override fun onChangeEvent(value: ECarXCarPropertyValue?) {
                    stateKeeper.sendLog(
                        "[CarSignalManager] id: ${value?.propertyId} value: ${value?.value}",
                        false
                    )
                }

                override fun onErrorEvent(i: Int, i2: Int) {
                    stateKeeper.sendLog(
                        "[CarSignalManager] error $i $i2",
                        false
                    )
                }

            }, SignalFilter())

            // Init custom fun
            // customCarFunction?.initCarSignalManager(eCarXCar, carSignalManager)

            stateKeeper.sendLog(
                "[CarSignalManager] Callback ready {eCarXCar.isConnected=${eCarXCar?.isConnected ?: false}; carSignalManager.exist=${carSignalManager != null}}",
                false
            )
        }.onFailure {
            stateKeeper.sendLog("[CarSignalManager] Callback fail", false)
        }

        stateKeeper.sendLog("[ECarXCarService] Connected", false)
    }

    /**
     * ECarXCarProxy death
     */
    override fun onECarXCarServiceDeath() {
        // runCatching { customCarFunction?.onECarXCarServiceDeath() }
        stateKeeper.sendLog("[ECarXCarService] Death", false)
    }

    private fun initCarWatcher(): Boolean {
        runCatching {
            mICar = Car.create(context)
            (if (mICar is IConnectable) mICar as IConnectable else null)
                ?.registerConnectWatcher(object : IConnectable.IConnectWatcher {
                    @Throws(IllegalAccessException::class, IllegalArgumentException::class)
                    override fun onConnected() {
                        carIsConnected = true
                    }

                    override fun onDisConnected() {
                        carIsConnected = false
                    }
                })

            // early sensor subscribing
            sensorListenList.forEach { (sensorId, _) -> sensorId.setSensorListener() }
            isCreated = true

            mICar?.iCarFunction?.registerFunctionValueWatcher(functionListener)
            return true
        }
        return false
    }

    private fun Int.setSensorListener() = runCatching {
        mICar?.sensorManager?.registerListener(sensorListener, this)
    }

    /*private fun Int.setRatedSensorListener(rate: Int = 5) = runCatching {
        mICar?.sensorManager?.registerListener(sensorListener, this, rate)
    }*/

    private fun CoroutineScope.initCarFuncCollector() = launch {
        launch {
            stateKeeper.toggleCameraFlow.collect {
                runCatching { toggleCamera() }
                stateKeeper.sendLog("[CAMERA] toggle", true)
            }
        }
        launch {
            stateKeeper.funCustomKeyFlow.collect { value ->
                // General hardware set
                /* runCatching {
                    val result = mVfmiscMgr?.CB_SelfDefineFuncReq(value.toHardwareApiCustomKey())
                    val name = result?.name.orEmpty()
                    stateKeeper.sendLog(
                        "[ECarXCarService] success set custom key via main = $name",
                        false
                    )
                }.onFailure {
                    stateKeeper.sendLog(
                        "[ECarXCarService] error set custom key ${it.message}",
                        false
                    )
                } */

                // Custom fun set
                /* runCatching {
                    val res = customCarFunction?.mVfmiscMgr?.CB_SelfDefineFuncReq(value.toHardwareApiCustomKey())
                    val name = res?.name.orEmpty()
                    stateKeeper.sendLog("[ECarXCarService] success set custom key via custom $name", false)
                }.onFailure {
                    stateKeeper.sendLog(
                        "[ECarXCarService] error set custom key ${it.message}",
                        false
                    )
                }
                runCatching {
                    val result = customCarFunction?.setFunctionValue(
                        CarPropertyKey.BCM_FUNC_CUSTOM_KEY,
                        value
                    )
                    stateKeeper.sendLog(
                        "[ECarXCarService] ${if (result == true) "success" else "fail"} custom fun set",
                        false
                    )
                }.onFailure {
                    stateKeeper.sendLog("[ECarXCarService] error custom fun set", false)
                } */

                // General api set
                runCatching {
                    val result = setIntProperty(CarPropertyKey.BCM_FUNC_CUSTOM_KEY, value)
                    stateKeeper.sendLog(
                        "[FUNC CUSTOM KEY] set $value ${if (result) "success" else "fail"}",
                        true
                    )
                }
            }
        }
        launch {
            stateKeeper.funCustomKeySupportFlow.collect {
                runCatching {
                    // getSupportedValueStatus
                    val supported = mICar?.iCarFunction?.getSupportedFunctionValue(
                        CarPropertyKey.BCM_FUNC_CUSTOM_KEY,
                        Integer.MIN_VALUE
                    )?.toSet()
                    stateKeeper.sendLog(
                        "[FUNC CUSTOM KEY] supported values: ${supported?.joinToString(", ")}",
                        true
                    )
                }
            }
        }
        launch {
            stateKeeper.propertyTaskFlow.collect {
                when (it) {
                    is ActionPropertyTask.IntValue -> runCatching {
                        val areaId = if (it.areaId == -228) Integer.MIN_VALUE else it.areaId
                        setIntProperty(it.propertyId, areaId, it.value)
                    }

                    is ActionPropertyTask.FloatValue -> runCatching {
                        val areaId = if (it.areaId == -228) Integer.MIN_VALUE else it.areaId
                        setFloatProperty(it.propertyId, areaId, it.value)
                    }

                    is ActionPropertyTask.GetFunIntValue -> runCatching {
                        val areaId = if (it.areaId == -228) Integer.MIN_VALUE else it.areaId
                        val value = getIntProperty(it.propertyId, areaId)
                        sendPropertyValueResult(it.propertyId, areaId, value)
                        sendPropertyIntResult(it.propertyId, areaId, value)
                    }

                    is ActionPropertyTask.GetFunFloatValue -> runCatching {
                        val areaId = if (it.areaId == -228) Integer.MIN_VALUE else it.areaId
                        val value = mICar
                            ?.iCarFunction
                            ?.getCustomizeFunctionValue(it.propertyId, areaId) ?: -1f
                        sendPropertyValueResult(it.propertyId, areaId, value)
                        sendPropertyFloatResult(it.propertyId, areaId, value)
                    }

                    is ActionPropertyTask.FunListenValue -> runCatching {
                        val areaId = if (it.areaId == -228) Integer.MIN_VALUE else it.areaId

                        val key = "${it.propertyId}_$areaId"
                        if (!functionListenList.contains(key)) {
                            functionListenList.add(key)
                        }
                    }

                    is ActionPropertyTask.GetSensorIntValue -> {
                        val value = getIntPropertyWithType(it.sensorId, IdType.ID_TYPE_SENSOR)
                        sendSensorEventResult(it.sensorId, value)
                    }

                    is ActionPropertyTask.GetSensorFloatValue -> {
                        val value = getFloatPropertyWithType(it.sensorId, IdType.ID_TYPE_SENSOR)
                        sendSensorValueResult(it.sensorId, value)
                    }

                    is ActionPropertyTask.SensorListenValue -> runCatching {
                        val key = it.sensorId
                        if (key !in sensorListenList) {
                            sensorListenList[key] = 0 to 0f
                            key.setSensorListener()
                        }
                    }

                    is ActionPropertyTask.GetInfoIntValue -> {
                        val value = getIntPropertyWithType(it.infoId, IdType.ID_TYPE_INFO)
                        sendInfoValueResult(it.infoId, value)
                    }

                    is ActionPropertyTask.GetInfoFloatValue -> {
                        val value = getFloatPropertyWithType(it.infoId, IdType.ID_TYPE_INFO)
                        sendInfoValueResult(it.infoId, value)
                    }

                    is ActionPropertyTask.GetInfoStringValue -> {
                        val value = mICar?.carInfoManager?.getCarInfoString(it.infoId) ?: ""
                        sendInfoValueResult(it.infoId, value)
                    }
                }
            }
        }
        launch {
            stateKeeper.setLampModeFlow.collect {
                setIntProperty(CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL, it)
            }
        }
    }

    private fun sendPropertyValueChanged(id: Int, area: Int, value: Any) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.PROPERTY_VALUE_CHANGED"
            putExtra("id", id.toString())
            putExtra("area", area.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendPropertyIntChanged(id: Int, area: Int, value: Int) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.PROPERTY_INT_CHANGED"
            putExtra("id", id.toString())
            putExtra("area", area.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendPropertyFloatChanged(id: Int, area: Int, value: Float) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.PROPERTY_FLOAT_CHANGED"
            putExtra("id", id.toString())
            putExtra("area", area.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendPropertyValueResult(id: Int, area: Int, value: Any) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.PROPERTY_VALUE_RESULT"
            putExtra("id", id.toString())
            putExtra("area", area.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendPropertyIntResult(id: Int, area: Int, value: Int) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.PROPERTY_INT_RESULT"
            putExtra("id", id.toString())
            putExtra("area", area.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendPropertyFloatResult(id: Int, area: Int, value: Float) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.PROPERTY_FLOAT_RESULT"
            putExtra("id", id.toString())
            putExtra("area", area.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendSensorValueChanged(id: Int, value: Float) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.SENSOR_FLOAT_CHANGED"
            putExtra("id", id.toString())

            // TODO MAKE VALUE FORMATS?
            val textValue = value.toString()
            putExtra("value", textValue)
            // putExtra("normalizedValue", textValue.extractMantissa())
            // putExtra("roundedValue", String.format(Locale.ENGLISH, "%.2f", value))
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendSensorEventChanged(id: Int, value: Int) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.SENSOR_INT_CHANGED"
            putExtra("id", id.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendSensorValueResult(id: Int, value: Float) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.SENSOR_FLOAT_RESULT"
            putExtra("id", id.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendSensorEventResult(id: Int, value: Int) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.SENSOR_INT_RESULT"
            putExtra("id", id.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun sendInfoValueResult(id: Int, value: Any) = scope.launch {
        val intent = Intent().apply {
            action = "$basePath.INFO_VALUE_RESULT"
            putExtra("id", id.toString())
            putExtra("value", value.toString())
        }
        scope.launch(Dispatchers.IO) { context.sendBroadcast(intent) }
    }

    private fun toggleCamera() {
        if (!carIsConnected) return
        if (getFunctionValue(CarPropertyKey.PAS_FUNC_PAC_ACTIVATION) == 1) {
            setFunctionValue(CarPropertyKey.PAS_FUNC_PAC_ACTIVATION, 0)
        } else {
            // delay(80L)
            setFunctionValue(CarPropertyKey.PAS_FUNC_PAC_ACTIVATION, 1)
        }
    }

    private fun setFunctionValue(functionId: Int, value: Int): Boolean {
        if (!carIsConnected) return false
        val car = mICar ?: return false
        return try {
            car.iCarFunction.setFunctionValue(functionId, value)
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

    private fun getFunctionValue(functionId: Int): Int {
        if (!carIsConnected) return -1
        val car = mICar ?: return -1
        return try {
            car.iCarFunction.getFunctionValue(functionId)
        } catch (e: Exception) {
            Timber.e(e)
            -1
        }
    }

    private fun setFloatProperty(propertyId: Int, value: Float): Boolean {
        return setFloatProperty(propertyId, Integer.MIN_VALUE, value)
    }

    private fun setFloatProperty(propertyId: Int, areaId: Int, value: Float): Boolean {
        if (!carIsConnected) return false
        val car = mICar ?: return false
        return try {
            stateKeeper.sendLog("[CAR] setFloatProperty $propertyId $areaId $value", true)
            car.iCarFunction.setCustomizeFunctionValue(propertyId, areaId, value)
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

    private fun setIntProperty(propertyId: Int, value: Int): Boolean {
        return setIntProperty(propertyId, Integer.MIN_VALUE, value)
    }

    private fun setIntProperty(propertyId: Int, areaId: Int, value: Int): Boolean {
        if (!carIsConnected) return false
        val car = mICar ?: return false
        return try {
            stateKeeper.sendLog("[CAR] setIntProperty $propertyId $areaId $value", true)
            car.iCarFunction.setFunctionValue(propertyId, areaId, value)
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

    override suspend fun getIntProperty(propertyId: Int): Int {
        return getIntProperty(propertyId, Integer.MIN_VALUE)
    }

    private fun getIntProperty(propertyId: Int, areaId: Int): Int {
        if (!carIsConnected) return -1
        val car = mICar ?: return -1
        return try {
            car.iCarFunction.getFunctionValue(propertyId, areaId)
        } catch (e: Exception) {
            Timber.e(e)
            -1
        }
    }

    override suspend fun getSupportStatus(propertyId: Int, type: Int) = when (type) {
        IdType.ID_TYPE_FUNCTION -> mICar?.iCarFunction
            ?.isFunctionSupported(propertyId, Integer.MIN_VALUE)
            ?.toStatus()
            ?: PropertyStatus.ERROR

        IdType.ID_TYPE_SENSOR -> mICar?.sensorManager?.isSensorSupported(propertyId)
            ?.toStatus()
            ?: PropertyStatus.ERROR

        IdType.ID_TYPE_INFO -> mICar?.carInfoManager?.isCarInfoSupported(propertyId)
            ?.toStatus()
            ?: PropertyStatus.ERROR

        else -> PropertyStatus.ERROR
    }

    override suspend fun setPropertyIntValue(propertyId: Int, zone: Int, value: Int) =
        setIntProperty(propertyId, zone, value)

    override suspend fun setPropertyFloatValue(propertyId: Int, zone: Int, value: Float) =
        setFloatProperty(propertyId, zone, value)

    private fun getFloatPropertyWithType(propertyId: Int, type: Int) = when (type) {
        IdType.ID_TYPE_FUNCTION ->
            mICar?.iCarFunction?.getCustomizeFunctionValue(propertyId, Integer.MIN_VALUE) ?: 0f

        IdType.ID_TYPE_SENSOR -> mICar?.sensorManager?.getSensorLatestValue(propertyId) ?: 0f

        IdType.ID_TYPE_INFO -> mICar?.carInfoManager?.getCarInfoFloat(propertyId) ?: 0f

        else -> 0f
    }

    override fun getIntPropertyWithType(propertyId: Int, type: Int) = when (type) {
        IdType.ID_TYPE_FUNCTION ->
            mICar?.iCarFunction?.getFunctionValue(propertyId, Integer.MIN_VALUE) ?: 0

        IdType.ID_TYPE_SENSOR -> mICar?.sensorManager?.getSensorEvent(propertyId) ?: 0

        IdType.ID_TYPE_INFO -> mICar?.carInfoManager?.getCarInfoInt(propertyId) ?: 0

        else -> 0
    }

    override suspend fun getPropertyValuesWithType(
        propertyId: Int,
        type: Int
    ): Map<Int, Pair<Int, Float>> {
        val output = mutableMapOf<Int, Pair<Int, Float>>()
        when (type) {
            IdType.ID_TYPE_FUNCTION -> {
                val zones =
                    mICar?.iCarFunction?.getSupportedFunctionZones(propertyId)?.toSet()
                        ?: emptySet()

                zones.forEach {
                    output[it] = getIntPropertyWithType(propertyId, type) to
                            getFloatPropertyWithType(propertyId, type)
                }

                if (output.isEmpty()) {
                    val defaultInt = getIntProperty(propertyId, Integer.MIN_VALUE)
                    val defaultFloat = mICar?.iCarFunction
                        ?.getCustomizeFunctionValue(propertyId, Integer.MIN_VALUE) ?: 0f
                    output[Integer.MIN_VALUE] = defaultInt to defaultFloat
                }
            }

            IdType.ID_TYPE_SENSOR, IdType.ID_TYPE_INFO -> {
                output[Integer.MIN_VALUE] = getIntPropertyWithType(propertyId, type) to
                        getFloatPropertyWithType(propertyId, type)
            }
        }

        return output.toMap()
    }

    override suspend fun getPropertySupportedValuesWithType(
        propertyId: Int,
        type: Int
    ): Map<Int, Set<Int>> {
        val output = mutableMapOf<Int, Set<Int>>()
        when (type) {
            IdType.ID_TYPE_FUNCTION -> {
                val zones =
                    mICar?.iCarFunction?.getSupportedFunctionZones(propertyId)?.toSet()
                        ?: emptySet()
                zones.forEach {
                    output[it] =
                        mICar?.iCarFunction?.getSupportedFunctionValue(propertyId, it)?.toSet()
                            ?: emptySet()
                }

                val byMinInt =
                    mICar?.iCarFunction?.getSupportedFunctionValue(propertyId, Integer.MIN_VALUE)
                        ?.toSet()
                        ?: emptySet()

                if (byMinInt.isNotEmpty()) {
                    output[Integer.MIN_VALUE] = byMinInt
                }
            }
        }
        return output.toMap()
    }

    override suspend fun getSensorValue(sensorId: Int) =
        mICar?.sensorManager?.getSensorLatestValue(sensorId) ?: 0f

    private fun FunctionStatus.toStatus() = when (this) {
        FunctionStatus.active -> PropertyStatus.ACTIVE
        FunctionStatus.notactive -> PropertyStatus.NOT_ACTIVE
        FunctionStatus.notavailable -> PropertyStatus.NOT_AVAILABLE
        FunctionStatus.error -> PropertyStatus.ERROR
    }

    private fun String.extractMantissa(): String {
        val index = this.indexOf('E')
        return if (index != -1) {
            this.substring(0, index)
        } else this
    }
}
