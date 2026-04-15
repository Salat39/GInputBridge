package com.salat.gbinder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.salat.gbinder.coroutines.IoCoroutineScope
import com.salat.gbinder.entity.ToggleMediaControl
import com.salat.gbinder.statekeeper.domain.entity.ActionPropertyTask
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.util.getSafeFloat
import com.salat.gbinder.util.getSafeInt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BackgroundTaskReceiver() : BroadcastReceiver() {

    @Inject
    @IoCoroutineScope
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var stataKeeper: StateKeeperRepository

    companion object {
        private const val BASE_PATH = "com.salat.gbinder"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("[BackgroundTaskReceiver] ${intent.action} received")

        when (intent.action) {
            "$BASE_PATH.SET_AUDIO_SOURCE" -> {
                val target = intent.getStringExtra("target")?.uppercase().orEmpty()
                val source = intent.getStringExtra("source")?.uppercase().orEmpty()
                scope.launch {
                    GlobalState.setAudioSourceFlow.emit(target to source)
                }
            }

            "$BASE_PATH.ENABLE_MEDIA_CONTROL" -> {
                scope.launch {
                    GlobalState.toggleMediaControlFlow.emit(ToggleMediaControl.ENABLE)
                }
            }

            "$BASE_PATH.SMART_ENABLE_MEDIA_CONTROL" -> {
                scope.launch {
                    GlobalState.toggleMediaControlFlow.emit(ToggleMediaControl.SMART)
                }
            }

            "$BASE_PATH.DISABLE_MEDIA_CONTROL" -> {
                scope.launch {
                    GlobalState.toggleMediaControlFlow.emit(ToggleMediaControl.DISABLE)
                }
            }

            "$BASE_PATH.TEMPORARY_DISABLE_MEDIA_CONTROL" -> {
                scope.launch {
                    val duration = intent.getSafeInt("duration")
                    if (duration != 0) {
                        GlobalState.tempDisableMediaControlFlow.emit(duration)
                    }
                }
            }

            "$BASE_PATH.REQUEST_PLAYBACK_INFO" -> {
                scope.launch {
                    GlobalState.requestPlaybackInfoFlow.emit(true)
                }
            }

            "$BASE_PATH.PHONE_CALL" -> {
                val appScope = scope
                val likeNumber = try {
                    intent.getDoubleExtra("number", 0.0)
                } catch (_: Exception) {
                    0.0
                }

                val number = if (likeNumber == 0.0) {
                    intent.getStringExtra("number").orEmpty()
                } else likeNumber.toString()

                appScope.launch {
                    GlobalState.requestPhoneCallFlow.emit(number)
                }
            }

            "$BASE_PATH.ANSWER_CALL" -> {
                scope.launch {
                    GlobalState.requestPhoneAnswerFlow.emit(true)
                }
            }

            "$BASE_PATH.REJECT_CALL" -> {
                scope.launch {
                    GlobalState.requestPhoneRejectFlow.emit(true)
                }
            }

            "$BASE_PATH.DISCONNECT_CALL" -> {
                scope.launch {
                    GlobalState.requestPhoneDisconnectFlow.emit(true)
                }
            }

            "$BASE_PATH.SET_VISIBLE_PACKAGE" -> {
                scope.launch {
                    val pkg = intent.getStringExtra("pkg").orEmpty()
                    if (pkg.isNotEmpty()) {
                        GlobalState.backupVisiblePackageFlow.emit(pkg)
                    }
                }
            }

            "$BASE_PATH.TOGGLE_CAMERA" -> stataKeeper.setToggleCamera(true)

            "$BASE_PATH.SET_INT_PROPERTY" -> scope.launch {
                val propertyId = intent.getSafeInt("id")
                val areaId = if (intent.hasExtra("area")) intent.getSafeInt("area") else -228
                val value = intent.getSafeInt("value")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.IntValue(
                        propertyId = propertyId,
                        areaId = areaId,
                        value = value
                    )
                )
            }

            "$BASE_PATH.SET_FLOAT_PROPERTY" -> scope.launch {
                val propertyId = intent.getSafeInt("id")
                val areaId = if (intent.hasExtra("area")) intent.getSafeInt("area") else -228
                val value = intent.getSafeFloat("value")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.FloatValue(
                        propertyId = propertyId,
                        areaId = areaId,
                        value = value
                    )
                )
            }

            "$BASE_PATH.GET_INT_PROPERTY" -> scope.launch {
                val propertyId = intent.getSafeInt("id")
                val areaId = if (intent.hasExtra("area")) intent.getSafeInt("area") else -228

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.GetFunIntValue(
                        propertyId = propertyId,
                        areaId = areaId
                    )
                )
            }

            "$BASE_PATH.GET_FLOAT_PROPERTY" -> scope.launch {
                val propertyId = intent.getSafeInt("id")
                val areaId = if (intent.hasExtra("area")) intent.getSafeInt("area") else -228

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.GetFunFloatValue(
                        propertyId = propertyId,
                        areaId = areaId
                    )
                )
            }

            "$BASE_PATH.LISTEN_PROPERTY_CHANGES" -> scope.launch {
                val propertyId = intent.getSafeInt("id")
                val areaId = if (intent.hasExtra("area")) intent.getSafeInt("area") else -228

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.FunListenValue(
                        propertyId = propertyId,
                        areaId = areaId
                    )
                )
            }

            "$BASE_PATH.GET_INT_SENSOR" -> scope.launch {
                val sensorId = intent.getSafeInt("id")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.GetSensorIntValue(sensorId)
                )
            }

            "$BASE_PATH.GET_FLOAT_SENSOR" -> scope.launch {
                val sensorId = intent.getSafeInt("id")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.GetSensorFloatValue(sensorId)
                )
            }

            "$BASE_PATH.LISTEN_SENSOR_CHANGES" -> scope.launch {
                val sensorId = intent.getSafeInt("id")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.SensorListenValue(sensorId)
                )
            }

            "$BASE_PATH.GET_INT_INFO" -> scope.launch {
                val infoId = intent.getSafeInt("id")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.GetInfoIntValue(infoId)
                )
            }

            "$BASE_PATH.GET_FLOAT_INFO" -> scope.launch {
                val infoId = intent.getSafeInt("id")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.GetInfoFloatValue(infoId)
                )
            }

            "$BASE_PATH.GET_STRING_INFO" -> scope.launch {
                val infoId = intent.getSafeInt("id")

                stataKeeper.setPropertyTask(
                    ActionPropertyTask.GetInfoStringValue(infoId)
                )
            }

            "$BASE_PATH.TOGGLE_LAUNCHER" -> stataKeeper.toggleLauncher()

            else -> Unit
        }
    }
}
