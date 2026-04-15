package com.salat.gbinder.car.data

import android.content.Context
import androidx.annotation.Keep
import com.ecarx.xui.adaptapi.car.AbsCarFunction
import com.ecarx.xui.adaptapi.car.IVehicleFunction
import com.ecarx.xui.adaptapi.car.Pairs
import com.ecarx.xui.adaptapi.car.VehicleFunction
import ecarx.car.hardware.annotation.ApiResult
import ecarx.car.hardware.vehicle.ECarXCarSetManager
import ecarx.car.hardware.vehicle.ECarXCarVfmiscManager

class CustomCarFunction(
    context: Context,
    moduleId: Int,
    private val log: (String) -> Unit
) : AbsCarFunction(context, moduleId) {
    var mVfmiscMgr: ECarXCarVfmiscManager? = null
        private set

    override fun onCarSignalConnected(eCarXCarSetManager: ECarXCarSetManager) {
        mVfmiscMgr = mECarXCarSetManager.eCarXCarVfmiscManager
    }

    @Keep
    fun registerIntFunction(vf: VehicleFunction<Int>) {
        // addIntFunction(vf)

        try {
            val m = com.ecarx.xui.adaptapi.car.AbsCarFunction::class.java
                .getDeclaredMethod("addIntFunction", VehicleFunction::class.java)
            m.isAccessible = true // Force access
            m.invoke(this, vf)
        } catch (t: Throwable) {
            log("[CustomKeySupport] reflect add fail: ${t::class.java.name}: ${t.message}")
        }
    }

    override fun buildFunctions() {
        runCatching {
            // Map client values -> raw codes used by PA (write path)
            val valuesCorrector: Pairs<Int, Int> = Pairs.of(3, 0)
                .add(0, 1)
                .add(1, 2)
                .add(2, 3)
                .add(4, 4)
                .add(5, 5)
                .add(99, 6)
                .add(6, 7)
                .add(100, 8)
                .add(101, 9)
                .add(102, 10)

            // Supported values exposed to clients (order doesn't matter)
            val supportedValue = intArrayOf(2, 3, 4, 5, 102, 6, 100, 101, 0, 1, 99)

            val pipeline: IVehicleFunction.IValueTaskBuild<Int> =
                VehicleFunction.intFunction(CarPropertyKey.BCM_FUNC_CUSTOM_KEY)
                    // Int.MIN_VALUE here denotes "common/no-zone" in this API
                    .createZone(Int.MIN_VALUE)
                    // Provide supported values + any extra raw codes if needed
                    .supportedFunctionValue(
                        { supportedValue },
                        557871947, 557871854, 557872059, 557871847, 557871825
                    )
                    // Bind support-status to a PA_IntBase property
                    .useStatusPAByIntBase(561024178)

            pipeline
                // Setter: client Int -> ApiResult (write to car manager)
                .onSetFunctionValue(
                    { value: Int ->
                        try {
                            log("[CustomKeySupport] execute hasInstance: ${mVfmiscMgr != null}")
                            mVfmiscMgr?.CB_SelfDefineFuncReq(value) ?: ApiResult.FAILED
                        } catch (e: Exception) {
                            log("[CustomKeySupport] execute fail ${e.message}")
                            ApiResult.FAILED
                        }
                    },
                    valuesCorrector
                )
                // Value read path: raw code -> client value (reverse mapping)
                .useValuePAByIntBase(561024178, valuesCorrector.reverse())
                // Register built function in this module
                .addTo(::registerIntFunction)
            log("[CustomKeySupport] Success")
        }.onFailure { t ->
            log("[CustomKeySupport] Failure: ${t.message}")
        }
    }
}
