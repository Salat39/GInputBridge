@file:Suppress("UNNECESSARY_SAFE_CALL")

package com.salat.gbinder

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.content.pm.LauncherApps
import android.media.MediaMetadata
import android.media.session.MediaController
import android.media.session.PlaybackState
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.annotation.RawRes
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.ecarx.xui.adaptapi.input.IKeyCallback
import com.ecarx.xui.adaptapi.input.Input
import com.geely.lib.oneosapi.OneOSApiManager
import com.geely.lib.oneosapi.input.KeyCode
import com.geely.lib.oneosapi.input.KeyCodeEvent
import com.geely.lib.oneosapi.input.KeyInputManager
import com.geely.lib.oneosapi.launcher.LauncherManager
import com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener
import com.geely.lib.oneosapi.launcher.listener.IWidgetListDisplayChangeListener
import com.geely.lib.oneosapi.listener.ServiceConnectionListener
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant
import com.geely.lib.oneosapi.mediacenter.listener.SourceStateListener
import com.geely.lib.oneosapi.phone.PhoneManager
import com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener
import com.geely.lib.oneosapi.phone.telecom.GlyCallItem
import com.google.firebase.FirebaseApp
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.car.domain.entity.IdType
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.coil.IconRefFetcher
import com.salat.gbinder.coil.IconRefKeyer
import com.salat.gbinder.components.generateFileId
import com.salat.gbinder.components.inMainToast
import com.salat.gbinder.components.launchDynamicRetry
import com.salat.gbinder.coroutines.AppCoroutineScope
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.datastore.KeyBindStorageRepository
import com.salat.gbinder.datastore.LauncherPrefs
import com.salat.gbinder.datastore.NoBackupPrefs
import com.salat.gbinder.entity.AppMediaAction
import com.salat.gbinder.entity.DISPLAY_LAMP_MODES
import com.salat.gbinder.entity.FULL_KEYS
import com.salat.gbinder.entity.IGNORED_MEDIA_APPS
import com.salat.gbinder.entity.KeyBindAction
import com.salat.gbinder.entity.KeyBindConfig
import com.salat.gbinder.entity.KeyBindPattern
import com.salat.gbinder.entity.KeyState
import com.salat.gbinder.entity.PackagesChangedEvent
import com.salat.gbinder.entity.PlaybackMetadata
import com.salat.gbinder.entity.PressState
import com.salat.gbinder.entity.ToggleMediaControl
import com.salat.gbinder.features.launcher.LauncherDataRepository
import com.salat.gbinder.features.launcher.LauncherEntryActivity
import com.salat.gbinder.features.launcher.OVERLAY_RESTRICTED_PKGS
import com.salat.gbinder.logs.ExecTraceTree
import com.salat.gbinder.mappers.asAppSource
import com.salat.gbinder.mappers.asAudioSource
import com.salat.gbinder.mappers.asString
import com.salat.gbinder.statekeeper.domain.entity.AccessibilityServiceSignal
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.util.HeadrestNotifier
import com.salat.gbinder.util.SimpleTimer
import com.salat.gbinder.util.SystemAppsLightRepository
import com.salat.gbinder.util.activeMediaControllerFlow
import com.salat.gbinder.util.activeMediaSessionControllerFlow
import com.salat.gbinder.util.driveModeNotifStore
import com.salat.gbinder.util.getAudioSourceDisplayLabel
import com.salat.gbinder.util.getDriveModeName
import com.salat.gbinder.util.hasElapsedSinceBoot
import com.salat.gbinder.util.isMediaPlayingFlow
import com.salat.gbinder.util.nextCarouselAudioSource
import com.salat.gbinder.util.openApp
import com.salat.gbinder.util.requestCarouselAudioSourceForTarget
import com.salat.gbinder.util.sendMediaActionToApp
import com.salat.gbinder.util.sendMurglarAutoPlayCompat
import com.salat.gbinder.util.sendVkxAutoPlayCompat
import com.salat.gbinder.util.sendYmAutoPlayCompat
import com.salat.gbinder.util.softOpenApp
import com.salat.gbinder.util.waitForCarouselAudioSourceToSettle
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import java.io.File
import java.util.Collections
import java.util.WeakHashMap
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

const val ADDITIONAL_KEYS_MIN_LONG_PRESS_TIME = 820

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {

    companion object {
        private const val LOGS_LIMIT = 200

        private const val BASE_PATH = "com.salat.gbinder"
        private const val MACRO_DROID_PACKAGE = "com.arlosoft.macrodroid"
        private const val YAM_PACKAGE = "ru.yandex.music"
        private const val MURGLAR_PACKAGE = "com.badmanners.murglar2"
        private const val VKX_PACKAGE = "ua.itaysonlab.vkx"
        private const val GEELY_AC_PACKAGE = "com.geely.hvac"
        private const val HAV_YM_PACKAGE = "yandex.auto.music"
        private const val HAV_YM_UMA_PACKAGE = "yandex.auto.uma"
        private const val DUSI_ASSISTANT_PACKAGE = "com.dusiassistant"
        private const val KARAOKE_FOCUS_ACTION = "com.audiocn.karaoke.action.KEY_BROADCAST_FOCUS"
        private const val KARAOKE_FOCUS_EXTRA = "KaraokeKeyFocus"
        private val NATIVE_SOURCE_SESSION_PACKAGES = setOf(
            "com.android.bluetooth",
            "com.geely.usbservice",
            "com.geely.radio.service"
        )
        private val BT_RADIO_SOURCES = setOf(
            MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT,
            MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO,
            MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB
        )

        private const val RESTORE_DRIVE_MODE_TIMEOUT = 9_000L

        private const val OPEN_APP_TO_SEND_PLAY_PAUSE = 3000L
        private const val PLAYER_COMPAT_ACTION_DELAY = 600L

        private const val NOTIFICATION_WITH_DM_REMEMBER_DELAY = 1_000L
        private const val NOTIFICATION_WITHOUT_DM_REMEMBER_SHORT_DELAY = 5_000L
        private const val NOTIFICATION_WITHOUT_DM_REMEMBER_LONG_DELAY = 35_000L

        private const val MINIMIZE_SYSTEM_DELAY = 360L
        private const val SILENT_START = 4 // in sec
        private const val ONLINE_SWITCH_RETRY_INTERVAL_MS = 1200L
        private const val KARAOKE_RETRY_COUNT = 4
        private const val KARAOKE_RETRY_DELAY_MS = 1500L
        private const val MEDIA_CODE_PLAY = 0x1000
        private const val MEDIA_CODE_PAUSE = 0x1001

        private val AUDIO_SOURCE = MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE
    }

    @Inject
    @AppCoroutineScope
    lateinit var appScope: CoroutineScope

    @Inject
    lateinit var stateKeeper: StateKeeperRepository

    @Inject
    lateinit var carManager: CarRepository

    @Inject
    lateinit var systemApps: SystemAppsLightRepository

    @Inject
    lateinit var dataStore: DataStoreRepository

    @Inject
    lateinit var keyBindStorage: KeyBindStorageRepository

    @Inject
    lateinit var adb: AdbRepository

    // Ping for init
    @Inject
    lateinit var launcherData: LauncherDataRepository

    private val runtimeTimer = SimpleTimer()

    private var mKeyInputManager: KeyInputManager? = null

    private var additionalInputManager: Input? = null

    private var mPhoneManager: PhoneManager? = null

    // private var mThemeManager: ThemeManager? = null

    // private var mCameraManager: CameraManager? = null

    private var mLauncherManager: LauncherManager? = null

    // private var mStatusBarPublicManager: StatusBarPublicManager? = null
    private var mMediaCenterManager: MediaCenterManager? = null
    private var mSourceStateListener: SourceStateListener? = null

    private var mCustomIBluetoothServicesListener: CustomIBluetoothServicesListener? = null
    private var mCustomILauncherPageSwitchListener: CustomILauncherPageSwitchListener? = null
    private var mCustomIWidgetListDisplayChangeListener: CustomIWidgetListDisplayChangeListener? =
        null

    // Prefs
    private var enableTracking = false
    private var debugMode = false
    private var fullBroadcast = false
    private var trackKeyEvents = false
    private var customLongPressEnabled = false
    private var customLongPressTime = 1000L
    private var doubleClickEnabled = false
    private var doubleClickTimeout = 300L
    private var customShortPressEnabled = false
    private var multiLongPressEnabled = false
    private var suppressionMode = false
    private var mediaControlEnabled = false
    private var defaultMediaApps = ""
    private var disableOnClimate = false
    private var disableDuringCall = false
    private var sourceManagement = false
    private var radioBtControl = true
    private var hideMediaWidget = false
    private var mediaDataTranslator = false
    private var deepLogs = false
    private var keyBinds: Map<String, KeyBindConfig> = emptyMap()
    private var altMute = false
    private var altMenu = false
    private var altLongPressTime = ADDITIONAL_KEYS_MIN_LONG_PRESS_TIME

    // Drive mode
    private var rememberDriveMode = false
    private var driveModeOverlay = false

    // Initialization flag to understand that the last mode
    // has been restored before storing new modes
    private var lastDriveModeRestored = false

    // Single mutex for this preference key
    private val toggleDriveModeTaskMutex = Mutex()
    private val carouselAudioSourceMutex = Mutex()

    // Notify by drive mode changed
    private var canNotify = false

    // one-shot gate for enabling notifications after init
    private var startupNotifGateJob: Job? = null

    private var keyInputInitJob: Job? = null
    private var phoneInitJob: Job? = null
    // private var themeInitJob: Job? = null

    // private var cameraInitJob: Job? = null
    private var launcherInitJob: Job? = null
    // private var acRebindingJob: Job? = null

    private var mediaCenterInitJob: Job? = null

    private var mediaPlayStateJob: Job? = null
    private var mediaMetadataStateJob: Job? = null
    private var mediaControllersJob: Job? = null

    private var whCurrentHomePage = 0
    private var whWidgetIsVisible = true

    private var keyBindingMode = false

    private var keyEventListenerBound = false
    private var sourceStateListenerBound = false
    private var karaokeFocusBoot = false
    private var karaokeRetryJob: Job? = null

    private var globalActiveMediaController: MediaController? = null
    private var globalMediaControllers: List<MediaController>? = null

    // Meta data collecting
    private val _playbackMetadataFlow =
        MutableSharedFlow<MediaController?>(extraBufferCapacity = 1)
    private val playbackMetadataFlow: SharedFlow<MediaController?> =
        _playbackMetadataFlow.asSharedFlow()

    private var lastPlaybackState: Boolean = false
    private var lastExternalPlayingState: Boolean = false
    private var lastOnlineSwitchAttemptAt: Long = 0L
    private var lastPlaybackMetadata: PlaybackMetadata? = null
    private var lastKnownStableAudioSource: MediaCenterConstant.AudioSource? = null

    private var adbIsEnabled = false
    private var currentVisibleApp = ""
    private var currentMediaAppInForeground = false
    private var geelyACIsOpened = false
    private var controlMediaApps = emptyList<String>()
    private var currentMediaAppPackage = ""

    // Temporary lock management
    private var taskMediaControlTimeLock: Job? = null
    private var mediaControlTimeLock = false

    lateinit var logActor: SendChannel<String>

    private val launcherEntryActivities =
        Collections.newSetFromMap(WeakHashMap<LauncherEntryActivity, Boolean>())

    @OptIn(ObsoleteCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()
        timberInit()
        activitiesTracker()

        logActor = appScope.actor(capacity = Channel.UNLIMITED) {
            for (msg in channel) {
                try {
                    GlobalState.logState.update { oldList ->
                        val now = System.currentTimeMillis()
                        val prev = oldList.lastOrNull()?.first ?: 0L
                        val ts = if (now > prev) now else prev + 1L
                        oldList.addAndTrim(ts to msg, LOGS_LIMIT)
                    }
                } catch (e: Throwable) {
                    Timber.e(e)
                }
            }
        }

        appScope.launch {
            initialLoggerState()
            initLogCollector()
            initAppScalesCollector()
            initVisibleAppCollector() // Accessibility event bridge
            handleToggleLauncher()
            handleAdbActions()

            // Start API init
            initOneOSApiManager()
            carManager.create()
        }

        // Launcher device packages tracker
        monitorPackageChangesOnDevice()

        FirebaseApp.initializeApp(this)
        Timber.d("[APP] CREATED")
    }

    private fun onOneOSApiConnected() = appScope.launch {
        initialRuntimePrefsState()
        initPlaybackMetadataCollector()
        initPrefsCollector()
        initSetAudioSourceCollector()
        initMediaControlToggleCollector()
        initRequestPlaybackInfoCollector()
        initDevicePackagesChangedCollector()
        initRequestPhoneCollector()
        initAccessibilityStateCollector()
        initMediaSessionsStateCollector()
        backupVisiblePackageCollector()
        initLauncherManagerWatchDog()
        handleKeyBindMode()
        collectDriveModeChanged()
        collectIgnitionState()
        handleNotifPlayTest()

        // TODO Test delay
        delay(250L)
        // KeyInputManager and mediaManager binding
        bindKeyInputAndMediaManagers()

        // Launcher manager binding flag
        stateKeeper.setLauncherManagerState(
            stateKeeper.launcherManagerState.value.copy(
                isOneOsApiReady = true
            )
        )
    }

    private fun timberInit() {
        if (BuildConfig.DEBUG) {
            Timber.plant(ExecTraceTree())
        }
    }

    private fun initOneOSApiManager(): Boolean {
        try {
            val api = OneOSApiManager.getInstance(this)
            api.registerServiceConnectionListener(BaseServiceConnectionListener())
            api.init()
            return true
        } catch (e: Exception) {
            Timber.e(e)
        }
        return false
    }

    // -----------------------------------
    // Activity tracker
    // -----------------------------------

    private fun activitiesTracker() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is LauncherEntryActivity) {
                    launcherEntryActivities.add(activity)
                }
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activity is LauncherEntryActivity) {
                    launcherEntryActivities.remove(activity)
                }
            }

            override fun onActivityStarted(activity: Activity) = Unit
            override fun onActivityResumed(activity: Activity) = Unit
            override fun onActivityPaused(activity: Activity) = Unit
            override fun onActivityStopped(activity: Activity) = Unit
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
        })
    }

    private fun finishLauncherEntryActivitiesIfRunning() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Handler(Looper.getMainLooper()).post { finishLauncherEntryActivitiesIfRunning() }
            return
        }

        runCatching {
            launcherEntryActivities.toList().forEach { activity ->
                if (!activity.isFinishing && !activity.isDestroyed) {
                    Timber.d("[LAUNCHER] Kill background launcher activity")
                    activity.finish()
                }
            }
        }.onFailure { Timber.e(it) }
    }

    // -----------------------------------
    // Keys
    // -----------------------------------

    private val inputListener = object : KeyInputManager.BaseInputListener() {
        private val keyStates = ConcurrentHashMap<Int, KeyState>()

        override fun onKeyCodeEvent(keyCode: Int, event: Int, softKeyFunction: Int) {
            if (!enableTracking) return
            debugDeepLog("HARDW onKeyCodeEvent $keyCode")
            if (isAdditionalKey(keyCode)) return

            when (event) {
                KeyCodeEvent.ACTION_DOWN -> handlePress(keyCode)

                KeyCodeEvent.ACTION_UP, KeyCodeEvent.ACTION_CANCEL ->
                    if (customShortPressEnabled) handleRelease(keyCode, softKeyFunction)
            }

            if (trackKeyEvents) {
                handleKeyCode(keyCode, event, softKeyFunction)
            }
        }

        private fun handlePress(keyCode: Int) {
            // create or reset state
            val state = keyStates.getOrDefault(keyCode, KeyState())
            state.pressState = PressState.PRESSED
            state.resetSelfDestroy()
            keyStates[keyCode] = state

            // schedule custom long if enabled
            if (customLongPressEnabled) {
                val delayMs = if (isAdditionalKey(keyCode)) {
                    maxOf(customLongPressTime, altLongPressTime.toLong())
                } else {
                    customLongPressTime
                }

                val customLongPressTimer = appScope.launch {
                    delay(delayMs)

                    val st = keyStates.getOrDefault(keyCode, KeyState())
                    if (!st.longPosted && st.pressState == PressState.PRESSED) {
                        keyStates[keyCode]?.longPosted = true
                        keyStates[keyCode]?.singleTimer = null
                        handleSyncLong(keyCode, 0)
                    }
                }
                keyStates[keyCode]?.singleTimer = customLongPressTimer
            }
        }

        /**
         * called by un-press or hardware short event
         */
        private fun handleRelease(keyCode: Int, softKeyFunction: Int) {
            val state = keyStates.getOrDefault(keyCode, KeyState())
            if (state.pressState == PressState.RELEASED) return

            // if custom long already fired, swallow short
            if (state.longPosted) return

            // double-click logic
            if (doubleClickEnabled) {
                if (state.doubleTimer != null && (state.doubleTimer as Job).isActive) {
                    // already in double click timer
                    keyStates[keyCode]?.pressState = PressState.RELEASED
                    keyStates[keyCode]?.reinitSelfDestroy(keyCode)
                    keyStates[keyCode]?.longPosted = false
                    keyStates[keyCode]?.doubleTimer?.cancel()
                    keyStates[keyCode]?.doubleTimer = null
                    handleDoubleClick(keyCode, 0, "C1")
                    return
                }

                val doubleClickTimer = appScope.launch {
                    delay(doubleClickTimeout)

                    handleShortClick(keyCode, softKeyFunction, "DC")
                    appScope.launch {
                        keyStates[keyCode]?.pressState = PressState.RELEASED
                        keyStates[keyCode]?.reinitSelfDestroy(keyCode)
                        keyStates[keyCode]?.longPosted = false
                        keyStates[keyCode]?.doubleTimer?.cancel()
                        keyStates[keyCode]?.doubleTimer = null
                    }
                }
                keyStates[keyCode]?.doubleTimer = doubleClickTimer
                return
            }

            // fallback short
            handleShortClick(keyCode, softKeyFunction, "")

            // Reset key state
            keyStates[keyCode]?.pressState = PressState.RELEASED
            keyStates[keyCode]?.reinitSelfDestroy(keyCode)
            keyStates[keyCode]?.longPosted = false
            keyStates[keyCode]?.singleTimer?.cancel()
            keyStates[keyCode]?.singleTimer = null
            keyStates[keyCode]?.doubleTimer?.cancel()
            keyStates[keyCode]?.doubleTimer = null
        }

        /**
         * Called by either a custom long press or a hardware press
         */
        private fun handleSyncLong(keyCode: Int, func: Int) {
            val state = keyStates.getOrDefault(keyCode, KeyState())

            // EVADE DOUBLE CLICK FAKE PRESS
            if (state.doubleTimer != null && (state.doubleTimer as Job).isActive) {
                keyStates[keyCode]?.pressState = PressState.RELEASED
                keyStates[keyCode]?.reinitSelfDestroy(keyCode)
                keyStates[keyCode]?.longPosted = false
                keyStates[keyCode]?.singleTimer?.cancel()
                keyStates[keyCode]?.singleTimer = null
                return
            }

            if (state.pressState == PressState.PRESSED) {
                if (multiLongPressEnabled) {
                    val pressedKeys = keyStates.filter { it.value.pressState == PressState.PRESSED }

                    // If the combo includes MENU/MUTE, do not emit multi-long
                    // until MENU/MUTE reaches its long threshold (min 820ms).
                    if (pressedKeys.shouldDelayMultiLongForAdditionalKeys()) return

                    // Reset pressed state
                    pressedKeys.forEach { (key, _) ->
                        keyStates[key]?.pressState = PressState.RELEASED
                        keyStates[keyCode]?.reinitSelfDestroy(keyCode)
                        keyStates[key]?.longPosted = false
                        keyStates[key]?.singleTimer?.cancel()
                        keyStates[key]?.singleTimer = null
                    }

                    if (pressedKeys.size > 1) {
                        // Send multi long
                        handleMultiLongPress(pressedKeys.map { it.key })
                    } else if (pressedKeys.size == 1) {
                        handleOnLongPress(pressedKeys.keys.first(), func, "ML")
                    }
                } else {
                    keyStates[keyCode]?.pressState = PressState.RELEASED
                    keyStates[keyCode]?.reinitSelfDestroy(keyCode)
                    keyStates[keyCode]?.longPosted = false
                    keyStates[keyCode]?.singleTimer?.cancel()
                    keyStates[keyCode]?.singleTimer = null

                    handleOnLongPress(keyCode, func, "DL")
                }
            }
        }

        override fun onShortClick(keyCode: Int, softKeyFunction: Int) {
            if (!enableTracking) return
            debugDeepLog("HARDW onShortClick $keyCode")
            // hardware short
            if (customShortPressEnabled || isAdditionalKey(keyCode)) return
            handleRelease(keyCode, softKeyFunction)
        }

        override fun onDoubleClick(keyCode: Int, softKeyFunction: Int) {
            if (!enableTracking) return
            debugDeepLog("HARDW onDoubleClick $keyCode")
            // hardware double
            if (isAdditionalKey(keyCode)) return
            handleDoubleClick(keyCode, softKeyFunction, "")
        }

        override fun onLongPressTriggered(keyCode: Int, softKeyFunction: Int) {
            if (!enableTracking) return
            debugDeepLog("HARDW onLongPressTriggered $keyCode")
            if (isAdditionalKey(keyCode)) return

            // No any other events = no state in list
            if (!keyStates.containsKey(keyCode)) {
                // Direct long press event
                handleOnLongPress(keyCode, softKeyFunction, "DC")
                return
            } else if (keyStates[keyCode]?.pressState == PressState.RELEASED) { // TODO TEST ZONE
                // Remove state if already released and hardware long triggered
                keyStates.remove(keyCode)
            }

            val state = keyStates.getOrDefault(keyCode, KeyState())
            if (!state.longPosted && state.pressState == PressState.PRESSED) {
                keyStates[keyCode]?.longPosted = true
                // keyStates[keyCode]?.singleTimer?.cancel()
                keyStates[keyCode]?.singleTimer = null
                handleSyncLong(keyCode, softKeyFunction)
            }
        }

        override fun onHoldingPressStarted(keyCode: Int, softKeyFunction: Int) {
            if (!enableTracking) return
            debugDeepLog("HARDW onHoldingPressStarted $keyCode")
            if (isAdditionalKey(keyCode)) return
            handleHoldStart(keyCode, softKeyFunction)
        }

        override fun onHoldingPressStopped(keyCode: Int, softKeyFunction: Int) {
            if (!enableTracking) return
            debugDeepLog("HARDW onHoldingPressStopped $keyCode")
            if (isAdditionalKey(keyCode)) return
            handleHoldStop(keyCode, softKeyFunction)
        }

        private fun KeyState.reinitSelfDestroy(keyCode: Int) {
            resetSelfDestroy()
            selfDestroyer = appScope.launch {
                delay(1500L)
                keyStates.remove(keyCode)
            }
        }

        private fun KeyState.resetSelfDestroy() {
            selfDestroyer?.cancel()
            selfDestroyer = null
        }

        // Accept key events from other sources and route them into the common pipeline.
        fun onExternalKeyPressed(keyCode: Int) {
            if (!enableTracking) return

            if (customShortPressEnabled) handlePress(keyCode)

            if (trackKeyEvents) handleKeyCode(keyCode, KeyCodeEvent.ACTION_DOWN, 0)
        }

        // Accept key events from other sources and route them into the common pipeline.
        fun onExternalKeyReleased(keyCode: Int) {
            if (!enableTracking) return

            if (customShortPressEnabled) handleRelease(keyCode, 0)

            if (trackKeyEvents) handleKeyCode(keyCode, KeyCodeEvent.ACTION_UP, 0)
        }

        // Guard to ensure multi-long combinations that include additional keys
        // are only emitted after the additional key reaches its minimum long threshold.
        private fun Map<Int, KeyState>.shouldDelayMultiLongForAdditionalKeys(): Boolean {
            // Only relevant for actual combos
            if (this.size <= 1) return false

            // If combo doesn't include MENU/MUTE, keep legacy behavior unchanged
            val hasAdditional = this.keys.any { isAdditionalKey(it) }
            if (!hasAdditional) return false

            // If MENU/MUTE is pressed but hasn't reached long yet, delay emitting multi-long
            return this.any { (code, state) ->
                isAdditionalKey(code) && !state.longPosted && state.pressState == PressState.PRESSED
            }
        }
    }

    // -----------------------------------
    // Initialization
    // -----------------------------------

    private fun CoroutineScope.initPrefsCollector() = launch {
        launch {
            dataStore.getValueFlow(GeneralPrefs.DEBUG_MODE).collect {
                debugMode = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.DEEP_LOGS).collect {
                deepLogs = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.DATA_SYNC_ENABLED).collect {
                enableTracking = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.FULL_BROADCAST).collect {
                fullBroadcast = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.TRACK_KEY_EVENTS).collect {
                trackKeyEvents = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.CUSTOM_LONG_PRESS_ENABLED).collect {
                customLongPressEnabled = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.CUSTOM_LONG_PRESS_TIME).collect {
                customLongPressTime = (it ?: 1500).toLong()
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.DOUBLE_CLICK_ENABLED).collect {
                doubleClickEnabled = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.DOUBLE_CLICK_TIME).collect {
                doubleClickTimeout = (it ?: 300).toLong()
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.CUSTOM_SHORT_CLICK_ENABLED).collect {
                customShortPressEnabled = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.MULTI_LONG_PRESS_ENABLED).collect {
                multiLongPressEnabled = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.SUPPRESSION_MODE).collect {
                suppressionMode = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(NoBackupPrefs.ENABLED_MEDIA_APPS).collect { serialized ->
                val enabledApps = (serialized ?: "")
                    .split('|')
                    .toSet()
                updateAvailableMediaApps(enabledApps)
            }
        }
        launch {
            dataStore.getValueFlow(NoBackupPrefs.DEFAULT_MEDIA_APP).collect {
                defaultMediaApps = it ?: ""
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.DISABLE_ON_CLIMATE).collect {
                disableOnClimate = it ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.DISABLE_DURING_CALLS).collect { isDisabled ->
                disableDuringCall = isDisabled ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.LEGACY_SOURCE_MANAGEMENT).collect {
                sourceManagement = it ?: false

                if (isOnlineBootSwitch()) {
                    resetIfOtherAudioSource()
                }
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.RADIO_BT_CONTROL).collect {
                radioBtControl = it ?: true
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.HIDE_MEDIA_WIDGET).collect {
                hideMediaWidget = it ?: false
                stateKeeper.setLauncherManagerState(
                    stateKeeper.launcherManagerState.value.copy(
                        isHideWidgetEnabled = hideMediaWidget
                    )
                )
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.REMEMBER_DRIVE_MODE).collect { enabled ->
                // Kill previous toggle task
                // startupNotifGateJob?.cancel()

                rememberDriveMode = enabled ?: false
                rememberDriveModeChanged()
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.DRIVE_MODE_OVERLAY).collect { enabled ->
                driveModeOverlay = enabled ?: false
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.MEDIA_DATA_TRANSLATOR).collect {
                mediaDataTranslator = it ?: false

                // Sync bind ad unbind media control by pref
                stateKeeper.setHandleMediaSessionState(
                    stateKeeper.handleMediaSessionState.value.copy(
                        isDataTranslatorEnabled = mediaDataTranslator
                    )
                )
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.MEDIA_CONTROL_ENABLED).collect { enabled ->
                mediaControlEnabled = enabled ?: false

                // Sync bind ad unbind media control by pref
                stateKeeper.setHandleMediaSessionState(
                    stateKeeper.handleMediaSessionState.value.copy(
                        isMediaControlEnabled = mediaControlEnabled
                    )
                )
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.KEY_BINDS).collect { json ->
                keyBinds = json?.let { keyBindStorage.parseBinds(it) } ?: emptyMap()
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.ALT_MUTE).collect { enabled ->
                altMute = enabled ?: true
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.ALT_MENU).collect { enabled ->
                altMenu = enabled ?: true
            }
        }
        launch {
            dataStore.getValueFlow(GeneralPrefs.ALT_LONG_TIME).collect { time ->
                altLongPressTime = time ?: ADDITIONAL_KEYS_MIN_LONG_PRESS_TIME
            }
        }
    }

    private fun releaseActiveMediaSessionFlow() {
        mediaPlayStateJob?.cancel()
        mediaPlayStateJob = null

        mediaMetadataStateJob?.cancel()
        mediaMetadataStateJob = null

        mediaControllersJob?.cancel()
        mediaControllersJob = null

        globalActiveMediaController = null
        globalMediaControllers = null
    }

    private fun CoroutineScope.bindKeyInputAndMediaManagers() {
        debugDeepLog("[OneOSApiManager] start subsidiary managers init")

        // Start retry for KeyInput if not already active
        if (keyInputInitJob?.isActive != true) {
            keyInputInitJob = launchDynamicRetry(
                initBlock = {
                    try {
                        val isAlive = OneOSApiManager.getInstance(this@App)
                            .keyInputManager
                            ?.takeIf { it.isAlive }
                            ?.let { initKeyInputManager(); true }
                            ?: false
                        debugDeepLog("[KeyInputManager] ${if (isAlive) "is" else "not"} alive")
                        isAlive
                    } catch (e: Exception) {
                        Timber.e(e)
                        debugDeepLog("[KeyInputManager] alive check error")
                        false
                    }
                },
                cancelBlock = { cancelKeyInputManager() }
            )
        }

        // Start retry for Phone if not already active
        if (phoneInitJob?.isActive != true) {
            phoneInitJob = launchDynamicRetry(
                initBlock = {
                    try {
                        val isAlive = OneOSApiManager.getInstance(this@App)
                            .phoneManager
                            ?.takeIf { it.isAlive }
                            ?.let { initPhoneManager(); true }
                            ?: false
                        debugDeepLog("[PhoneManager] ${if (isAlive) "is" else "not"} alive")
                        isAlive
                    } catch (e: Exception) {
                        Timber.e(e)
                        debugDeepLog("[PhoneManager] alive check error")
                        false
                    }
                },
                cancelBlock = { cancelPhoneManager() }
            )
        }

        // Start retry for Theme if not already active
        /* if (themeInitJob?.isActive != true) {
            themeInitJob = launchDynamicRetry(
                initBlock = {
                    try {
                        val isAlive = OneOSApiManager.getInstance(this@App)
                            .themeManager
                            ?.let { initThemeManager(); true }
                            ?: false
                        debugDeepLog("[ThemeManager] ${if (isAlive) "is" else "not"} alive")
                        isAlive
                    } catch (e: Exception) {
                        Timber.e(e)
                        debugDeepLog("[ThemeManager] alive check error")
                        false
                    }
                },
                cancelBlock = { cancelThemeManager() }
            )
        } */

        // Start retry for Camera if not already active
        /* if (cameraInitJob?.isActive != true) {
            cameraInitJob = launchDynamicRetry(
                initBlock = {
                    try {
                        val isAlive = OneOSApiManager.getInstance(this@App)
                            .cameraManager
                            ?.takeIf { it.isAlive }
                            ?.let { initCameraManager(); true }
                            ?: false
                        debugDeepLog("[CameraManager] ${if (isAlive) "is" else "not"} alive")
                        isAlive
                    } catch (e: Exception) {
                        Timber.e(e)
                        debugDeepLog("[CameraManager] alive check error")
                        false
                    }
                },
                cancelBlock = { cancelCameraManager() }
            )
        } */

        // Same for MediaCenter
        if (mediaCenterInitJob?.isActive != true) {
            mediaCenterInitJob = launchDynamicRetry(
                initBlock = {
                    try {
                        val isAlive = OneOSApiManager.getInstance(this@App)
                            .mediaCenterManager
                            ?.takeIf { it.isAlive }
                            ?.let { initMediaCenterManager(); true }
                            ?: false
                        debugDeepLog("[MediaCenterManager] ${if (isAlive) "is" else "not"} alive")
                        isAlive
                    } catch (e: Exception) {
                        Timber.e(e)
                        debugDeepLog("[MediaCenterManager] alive check error")
                        false
                    }
                },
                cancelBlock = { cancelMediaCenterManager() }
            )
        }
    }

    private fun releaseAllManagersBinding() {
        // Disconnected — cancel both retries and managers
        try {
            keyInputInitJob?.cancel()
            phoneInitJob?.cancel()
            launcherInitJob?.cancel()
            mediaCenterInitJob?.cancel()
            cancelKeyInputManager()
            cancelPhoneManager()
            cancelLauncherManager()
            cancelMediaCenterManager()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private fun CoroutineScope.initSetAudioSourceCollector() = launch {
        GlobalState.setAudioSourceFlow.collect { (target, appSource) ->
            val inputSource = target.asAudioSource()
            val inputAppSource = appSource.asAppSource()

            inputSource?.let { source ->
                try {
                    if (inputAppSource == null) {
                        mMediaCenterManager?.requestAudioSource(source)
                    } else {
                        mMediaCenterManager?.requestAudioSource(source, inputAppSource)
                    }
                    debugDeepLog("[MediaCenterManager] new source by intent: $source")
                } catch (e: Exception) {
                    Timber.e(e)
                    debugDeepLog("[MediaCenterManager] switch source by intent error")
                }
            }
        }
    }

    private fun CoroutineScope.initMediaControlToggleCollector() = launch {
        launch {
            GlobalState.toggleMediaControlFlow.collect { state ->
                // Reset temporary lock task
                resetMediaControlLockTask()

                // Handle media control task
                when (state) {
                    ToggleMediaControl.ENABLE ->
                        dataStore.saveValue(GeneralPrefs.MEDIA_CONTROL_ENABLED, true)

                    ToggleMediaControl.DISABLE ->
                        dataStore.saveValue(GeneralPrefs.MEDIA_CONTROL_ENABLED, false)

                    ToggleMediaControl.SMART -> {
                        val currentState =
                            dataStore.getValueFlow(GeneralPrefs.MEDIA_CONTROL_ENABLED).first()
                                ?: false
                        val handState =
                            dataStore.getValueFlow(GeneralPrefs.HAND_MEDIA_CONTROL_ENABLED).first()

                        // Save current state like hand state
                        if (handState == null) {
                            dataStore.saveValue(
                                GeneralPrefs.HAND_MEDIA_CONTROL_ENABLED,
                                currentState
                            )
                        }

                        // Set if hand state is enabled
                        if (handState == true) {
                            dataStore.saveValue(GeneralPrefs.MEDIA_CONTROL_ENABLED, true)
                        }
                    }
                }
            }
        }
        launch {
            GlobalState.tempDisableMediaControlFlow.collect { duration ->
                startMediaControlLockTask(duration)
            }
        }
    }

    private fun CoroutineScope.initRequestPlaybackInfoCollector() = launch {
        GlobalState.requestPlaybackInfoFlow.collect {
            sendPlayState(lastPlaybackState)
            lastPlaybackMetadata?.let { sendPlaybackMetadata(it) }
        }
    }

    private fun CoroutineScope.initRequestPhoneCollector() = launch {
        launch {
            GlobalState.requestPhoneCallFlow.collect { number ->
                if (number.isEmpty()) return@collect
                runCatching { mPhoneManager?.placeCall(number) }
                debugDeepLog("[PHONE] call $number")
            }
        }
        launch {
            GlobalState.requestPhoneAnswerFlow.collect {
                runCatching { mPhoneManager?.answerCall() }
                debugDeepLog("[PHONE] answer")
            }
        }
        launch {
            GlobalState.requestPhoneRejectFlow.collect {
                runCatching { mPhoneManager?.rejectCall() }
                debugDeepLog("[PHONE] reject")
            }
        }
        launch {
            GlobalState.requestPhoneDisconnectFlow.collect {
                runCatching { mPhoneManager?.disconnectCall() }
                debugDeepLog("[PHONE] disconnect")
            }
        }
    }

    private fun CoroutineScope.initDevicePackagesChangedCollector() = launch {
        GlobalState.devicePackagesChangedFlow.collect {
            updateAvailableMediaApps()
            debugDeepLog("[AS] Changed the list of installed apps")
        }
    }

    private fun CoroutineScope.initAccessibilityStateCollector() = launch {
        stateKeeper.canAccessibility.collect { isEnable ->
            if (isEnable) {
                stateKeeper.setLauncherManagerState(
                    stateKeeper.launcherManagerState.value.copy(
                        isAccessibilityReady = true
                    )
                )

                debugDeepLog("[AS] AccessibilityService has launched")
            } else {
                stateKeeper.setLauncherManagerState(
                    stateKeeper.launcherManagerState.value.copy(
                        isAccessibilityReady = false
                    )
                )

                debugDeepLog("[AS] AccessibilityService is down :(")
            }
        }
    }

    private fun CoroutineScope.initMediaSessionsStateCollector() = launch {
        stateKeeper.handleMediaSessionState.collect { state ->
            if (state.isMediaControlEnabled || state.isDataTranslatorEnabled) {

                if (mediaPlayStateJob?.isActive != true) {
                    mediaPlayStateJob = launch {
                        isMediaPlayingFlow().collect { isPlaying ->

                            // Set online audio source for any foreground external "play"
                            if (shouldSwitchOnline(isPlaying)) {
                                resetIfOtherAudioSource()
                            }

                            // MediaData translation
                            if (mediaDataTranslator && lastPlaybackState != isPlaying) {
                                sendPlayState(isPlaying)
                            }
                            lastPlaybackState = isPlaying
                        }
                    }
                }

                if (mediaMetadataStateJob?.isActive != true) {
                    mediaMetadataStateJob = launch {
                        activeMediaControllerFlow().collect { controller ->
                            globalActiveMediaController = controller

                            // MediaData translation
                            if (mediaDataTranslator) {
                                // Check and send base media metadata
                                _playbackMetadataFlow.emit(controller)
                            }
                        }
                    }
                }

                if (mediaControllersJob?.isActive != true) {
                    mediaControllersJob = launch {
                        activeMediaSessionControllerFlow().collect { (_, allControllers) ->
                            globalMediaControllers = allControllers
                        }
                    }
                }
            } else {
                releaseActiveMediaSessionFlow()
            }
        }
    }

    private fun CoroutineScope.backupVisiblePackageCollector() = launch {
        GlobalState.backupVisiblePackageFlow.collect { pkg ->
            // Set backup source visible app, if accessibility not available
            if (!stateKeeper.canAccessibility.value) {
                stateKeeper.setVisibleApp(pkg, this@App.packageName == pkg)
            }
        }
    }

    private fun CoroutineScope.initLauncherManagerWatchDog() = launch {
        stateKeeper.launcherManagerState.collect { state ->
            if (state.isAccessibilityReady && state.isHideWidgetEnabled && state.isOneOsApiReady) {
                // Start retry for Launcher if not already active
                if (launcherInitJob?.isActive != true) {
                    launcherInitJob = launchDynamicRetry(
                        initBlock = {
                            try {
                                val isAlive = OneOSApiManager.getInstance(this@App)
                                    .launcherManager
                                    ?.takeIf { it.isAlive }
                                    ?.let {
                                        cancelLauncherManager()
                                        initLauncherManager()
                                        true
                                    }
                                    ?: false
                                debugDeepLog("[LauncherManager] ${if (isAlive) "is" else "not"} alive")
                                isAlive
                            } catch (e: Exception) {
                                Timber.e(e)
                                debugDeepLog("[LauncherManager] alive check error")
                                false
                            }
                        },
                        cancelBlock = { cancelLauncherManager() }
                    )
                }
            } else {
                launcherInitJob?.cancel()
                launcherInitJob = null
            }
        }
    }

    private fun CoroutineScope.initVisibleAppCollector() = launch {
        stateKeeper.visibleAppState.collect { pkg ->
            geelyACIsOpened = false

            // Reroute multi-packages
            val targetName = when (pkg) {
                HAV_YM_UMA_PACKAGE -> HAV_YM_PACKAGE
                else -> pkg
            }

            // Set current visible app
            currentVisibleApp = targetName
            switchOnlineForFgPlayback(targetName)

            // Detect AC is opened
            if (targetName == GEELY_AC_PACKAGE) {
                geelyACIsOpened = true
            }

            // Check if the package is in our list of media apps
            val isControlApp = targetName in controlMediaApps

            // If it's a new media app, save it
            if (isControlApp && targetName != currentMediaAppPackage) {
                currentMediaAppPackage = targetName
                debugLog("[MEDIA_CONTROL]: $currentMediaAppPackage")
            }

            // Flag indicating whether the current media app is in the foreground
            currentMediaAppInForeground = targetName == currentMediaAppPackage
        }
    }

    private fun CoroutineScope.initAppScalesCollector() = launch(Dispatchers.IO) {
        dataStore.valuesFlowWithDefaults(
            arrayOf(GeneralPrefs.APP_UI_SCALE, LauncherPrefs.LAUNCHER_SCALE),
            listOf(DEFAULT_UI_SCALE, DEFAULT_UI_SCALE)
        ).collect { prefs ->
            stateKeeper.setUiScale(prefs[0] as Float to prefs[1] as Float)
        }
    }

    private fun CoroutineScope.initLogCollector() = launch {
        stateKeeper.logChannel.collect { (msg, deep) ->
            if (deep) {
                debugDeepLog(msg)
            } else debugLog(msg)
        }
    }

    private fun CoroutineScope.handleKeyBindMode() = launch {
        GlobalState.keyBindingMode.collect { keyBindingMode = it }
    }

    private suspend fun initialLoggerState() {
        // Early debug mode set
        debugMode = dataStore.getValueFlow(GeneralPrefs.DEBUG_MODE).first() ?: false
        deepLogs = dataStore.getValueFlow(GeneralPrefs.DEEP_LOGS).first() ?: false
    }

    // Getting initial values, otherwise race conditions with true defaults
    private suspend fun initialRuntimePrefsState() = withContext(Dispatchers.IO) {
        sourceManagement = dataStore.getValueFlow(GeneralPrefs.LEGACY_SOURCE_MANAGEMENT).first() ?: false
        radioBtControl = dataStore.getValueFlow(GeneralPrefs.RADIO_BT_CONTROL).first() ?: true
        altMute = dataStore.getValueFlow(GeneralPrefs.ALT_MUTE).first() ?: true
        altMenu = dataStore.getValueFlow(GeneralPrefs.ALT_MENU).first() ?: true
    }

    private fun CoroutineScope.initPlaybackMetadataCollector() = launch(Dispatchers.Default) {
        playbackMetadataFlow.collect { handlePlaybackMetadataChanged(it) }
    }

    private fun initKeyInputManager(): Boolean {
        var listenerResult = false
        try {
            mKeyInputManager =
                OneOSApiManager.getInstance(this@App).keyInputManager
            unregisterKeyEventListener()
            listenerResult = registerKeyEventListener()
            registerAdditionKeys()
            debugDeepLog("[KeyInputManager] ready")
        } catch (e: Exception) {
            Timber.e(e)
            debugDeepLog("[KeyInputManager] error")
        }
        return mKeyInputManager != null && listenerResult
    }

    private fun cancelKeyInputManager() {
        // Destroy key binder
        try {
            unregisterKeyEventListener()
            mKeyInputManager = null
            debugDeepLog("[KeyInputManager] Destroyed")
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private fun initPhoneManager(): Boolean {
        var listenerResult = false
        try {
            mPhoneManager = OneOSApiManager.getInstance(this@App).phoneManager

            mCustomIBluetoothServicesListener = CustomIBluetoothServicesListener()
            mPhoneManager?.registerListener(mCustomIBluetoothServicesListener, packageName)

            listenerResult = true
            debugDeepLog("[PhoneManager] ready")
        } catch (e: Exception) {
            Timber.e(e)
            debugDeepLog("[PhoneManager] error")
        }
        return mPhoneManager != null && listenerResult
    }

    private fun cancelPhoneManager() {
        // Destroy phone binder
        try {
            mCustomIBluetoothServicesListener?.let { listener ->
                mPhoneManager
                    .takeIf { it?.isAlive == true }
                    ?.unRegisterListener(listener, packageName)
            }
            mCustomIBluetoothServicesListener = null

            mPhoneManager = null
            debugDeepLog("[PhoneManager] Destroyed")
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    /* private fun initThemeManager(): Boolean {
        var listenerResult = false
        try {
            mThemeManager = OneOSApiManager.getInstance(this@App).themeManager
            listenerResult = true
            debugDeepLog("[ThemeManager] ready")
        } catch (e: Exception) {
            Timber.e(e)
            debugDeepLog("[ThemeManager] error")
        }
        return mThemeManager != null && listenerResult
    }

    private fun cancelThemeManager() {
        // Destroy theme binder
        try {
            mThemeManager = null
            debugDeepLog("[ThemeManager] Destroyed")
        } catch (e: Exception) {
            Timber.e(e)
        }
    } */

    /* private fun initCameraManager(): Boolean {
        var listenerResult = false
        try {
            mCameraManager = OneOSApiManager.getInstance(this@App).cameraManager
            listenerResult = true
            debugDeepLog("[CameraManager] ready")
        } catch (e: Exception) {
            Timber.e(e)
            debugDeepLog("[CameraManager] error")
        }
        return mCameraManager != null && listenerResult
    }

    private fun cancelCameraManager() {
        try {
            mCameraManager = null
            debugDeepLog("[CameraManager] Destroyed")
        } catch (e: Exception) {
            Timber.e(e)
        }
    } */

    private fun initLauncherManager(): Boolean {
        var listenerResult = false
        try {
            mLauncherManager =
                OneOSApiManager.getInstance(this@App).launcherManager

            mCustomILauncherPageSwitchListener = CustomILauncherPageSwitchListener()
            mLauncherManager?.registerLauncherPageSwitchListener(mCustomILauncherPageSwitchListener)

            mCustomIWidgetListDisplayChangeListener = CustomIWidgetListDisplayChangeListener()
            mLauncherManager?.registerWidgetListDisplayChangeListener(
                mCustomIWidgetListDisplayChangeListener
            )

            listenerResult = true
            debugDeepLog("[LauncherManager] ready")
        } catch (e: Exception) {
            Timber.e(e)
            debugDeepLog("[LauncherManager] error")
        }
        return mLauncherManager != null && listenerResult
    }

    private fun cancelLauncherManager() {
        mCustomILauncherPageSwitchListener?.let { listener ->
            mLauncherManager
                .takeIf { it?.isAlive == true }
                ?.unRegisterLauncherPageSwitchListener(listener)
        }
        mCustomILauncherPageSwitchListener = null

        mCustomIWidgetListDisplayChangeListener?.let { listener ->
            mLauncherManager
                .takeIf { it?.isAlive == true }
                ?.unRegisterWidgetListDisplayChangeListener(listener)
        }
        mCustomIWidgetListDisplayChangeListener = null

        mLauncherManager = null
        debugDeepLog("[LauncherManager] Cleared")
    }

    private fun initMediaCenterManager(): Boolean {
        // Init media center and set listener
        try {
            mMediaCenterManager =
                OneOSApiManager.getInstance(this@App).mediaCenterManager
            mSourceStateListener =
                SourceStateListener { source, _ ->
                    lastKnownStableAudioSource = source
                    val sourceKey = source.asString()
                    sendAudioSourceChanged(sourceKey)
                    debugLog("AUDIO SOURCE CHANGED: $sourceKey")
                }
            mSourceStateListener?.let {
                mMediaCenterManager?.addSourceStateListener(it)
                sourceStateListenerBound = true
            }

            if (isOnlineBootSwitch()) {
                val sourceBeforeSwitch = mMediaCenterManager?.currentAudioSource
                resetIfOtherAudioSource()
                if (sourceBeforeSwitch in BT_RADIO_SOURCES) {
                    applyKaraokeFocusOnBootIfNeeded()
                    kickKaraokeRetry()
                }
            }
            debugDeepLog("[MediaCenterManager] ready")
        } catch (e: Exception) {
            Timber.e(e)
            debugDeepLog("[MediaCenterManager] error")
        }

        // Send current source after init
        try {
            mMediaCenterManager?.currentAudioSource?.let { source ->
                lastKnownStableAudioSource = source
                val sourceKey = source.asString()
                sendAudioSourceChanged(sourceKey)
                // debugLog("AUDIO SOURCE CHANGED: $sourceKey")
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return mMediaCenterManager != null
    }

    private fun cancelMediaCenterManager() {
        // Destroy media center session
        try {
            mSourceStateListener?.let {
                if (sourceStateListenerBound) {
                    mMediaCenterManager?.removeSourceStateListener(it)
                    sourceStateListenerBound = false
                }
            }
            mMediaCenterManager = null
            mSourceStateListener = null
            lastKnownStableAudioSource = null
            karaokeFocusBoot = false
            karaokeRetryJob?.cancel()
            karaokeRetryJob = null
            debugDeepLog("[MediaCenterManager] Destroyed")
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    // -----------------------------------
    // Api key listener binding
    // -----------------------------------

    private fun registerKeyEventListener(): Boolean {
        try {
            if (mKeyInputManager != null && mKeyInputManager?.isAlive == true) {
                mKeyInputManager?.registerListener(inputListener, packageName, FULL_KEYS)
                keyEventListenerBound = true
                return true
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return false
    }

    private fun unregisterKeyEventListener() {
        // media app: com.tencent.wecarflow
        // mKeyInputManager?.releaseKeyCode(it, packageName)
        try {
            if (keyEventListenerBound) {
                mKeyInputManager?.unregisterListener(inputListener, packageName)
                keyEventListenerBound = false
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    // -----------------------------------
    // Additional key listener
    // -----------------------------------

    private fun registerAdditionKeys(): Boolean {
        return try {
            val interceptKeys = intArrayOf(KeyCode.KEYCODE_R_MENU, KeyCode.KEYCODE_R_VOLUME_MUTE)
            additionalInputManager = Input.create(this@App)
            val callback = object : IKeyCallback {
                override fun onKeyPressed(code: Int): Boolean {
                    if (!enableTracking) return true
                    debugDeepLog("HARDW[A] onKeyPressed $code")
                    if (!isAdditionalKey(code)) return true
                    inputListener.onExternalKeyPressed(code)
                    return true
                }

                override fun onKeyReleased(code: Int): Boolean {
                    if (!enableTracking) return true
                    debugDeepLog("HARDW[A] onKeyReleased $code")
                    if (!isAdditionalKey(code)) return true
                    inputListener.onExternalKeyReleased(code)
                    return true
                }
            }
            additionalInputManager?.requestKeysInterception(interceptKeys, callback)
            true
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

    private fun isAdditionalKey(keyCode: Int) =
        (altMenu && keyCode == KeyCode.KEYCODE_R_MENU) || (altMute && keyCode == KeyCode.KEYCODE_R_VOLUME_MUTE)

    // -----------------------------------
    // Drive mode
    // -----------------------------------

    // TODO DO WITH ignitionStateFlow ??
    private fun ignitionDriving() = carManager.getIntPropertyWithType(
        propertyId = CarPropertyKey.SENSOR_TYPE_IGNITION_STATE,
        type = IdType.ID_TYPE_SENSOR
    ) == CarPropertyValue.IGNITION_STATE_DRIVING

    // Single time task
    private var changeDriveModeJob: Job? = null
    private fun rememberDriveModeChanged() {
        val prev = changeDriveModeJob
        changeDriveModeJob = appScope.launch {
            // Make sure previous run is fully stopped before we start side effects
            prev?.cancelAndJoin()

            if (rememberDriveMode) {
                // Do not mark restored yet
                lastDriveModeRestored = false

                // Hard timeout
                val success = withTimeoutOrNull(RESTORE_DRIVE_MODE_TIMEOUT) {
                    val rememberedDM =
                        dataStore.getValueFlow(GeneralPrefs.REMEMBERED_DRIVE_MODE).first() ?: -1

                    // save to pref current DM or restore saved
                    if (rememberedDM == -1 || rememberedDM == 0) {
                        // save to pref current DM
                        var saved = false
                        while (!saved) {
                            val currentDM =
                                carManager.getIntProperty(CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT)

                            if (currentDM != -1 && currentDM != 0) {
                                if (rememberDriveMode) {
                                    dataStore.saveValue(
                                        GeneralPrefs.REMEMBERED_DRIVE_MODE,
                                        currentDM
                                    )
                                }
                                saved = true
                                debugLog("[DRIVE MODE] save current: ${currentDM.getDriveModeName()}")
                            } else {
                                debugLog("[DRIVE MODE] try save loop: ${currentDM.getDriveModeName()}")
                                delay(250L)
                            }
                            if (!rememberDriveMode) break
                        }

                        lastDriveModeRestored = true
                    } else {
                        // Force set target recovery dm or real last saved
                        val targetRecoveryDriveMode = dataStore
                            .getValueFlow(GeneralPrefs.TARGET_RECOVERY_DRIVE_MODE).first()
                            ?: LAST_DM_ID
                        val recoveryDM = if (targetRecoveryDriveMode != LAST_DM_ID) {
                            targetRecoveryDriveMode
                        } else rememberedDM

                        // restore saved DM
                        var restored = false
                        while (!restored) {
                            val currentDM =
                                carManager.getIntProperty(CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT)

                            if (currentDM != -1 && currentDM != 0) {
                                if (currentDM != recoveryDM) {
                                    // Little stable pause
                                    delay(100L)
                                    if (rememberDriveMode) {
                                        // guard: disable notif before switching
                                        startupNotifGateJob?.cancel()
                                        canNotify = false

                                        // switch to recovery
                                        carManager.setPropertyIntValue(
                                            CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT,
                                            Integer.MIN_VALUE,
                                            recoveryDM
                                        )
                                    }

                                    // Confirm set
                                    delay(100L)
                                    var setCheck = false
                                    while (!setCheck) {
                                        val isSet =
                                            carManager.getIntProperty(CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT)
                                        if (isSet == recoveryDM) {
                                            setCheck = true
                                        } else {
                                            delay(250L)
                                        }
                                        if (!rememberDriveMode) break
                                    }

                                    debugLog("[DRIVE MODE] ${recoveryDM.getDriveModeName()} restored; bootGate=${!5.hasElapsedSinceBoot()}")
                                } else {
                                    debugLog("[DRIVE MODE] no need to restore: ${recoveryDM.getDriveModeName()}")
                                }

                                restored = true
                            } else {
                                debugLog("[DRIVE MODE] try restore loop ${recoveryDM.getDriveModeName()}")
                                delay(250L)
                            }
                            if (!rememberDriveMode) break
                        }

                        lastDriveModeRestored = true
                    }
                }

                if (success == null) {
                    // Timeout
                    debugLog("[DRIVE MODE] operation timed out")
                    lastDriveModeRestored = false
                }

                // Enable notifications ONLY after restoration (or timeout) finishes
                startShortTimeEnableNotifTask()
            } else {
                // Feature disabled -> clear state and saved value
                lastDriveModeRestored = false
                debugLog("[DRIVE MODE] not memorable")
                if (dataStore.exists(GeneralPrefs.REMEMBERED_DRIVE_MODE)) {
                    dataStore.removeValue(GeneralPrefs.REMEMBERED_DRIVE_MODE)
                }
            }
        }
    }

    private fun canNotifLog(source: String, action: String) {
        if (!debugMode) return
        debugLog("canNotify: $source $action; sinceBoot=${runtimeTimer.time}")
    }

    @OptIn(FlowPreview::class)
    private fun CoroutineScope.collectDriveModeChanged() = launch {
        carManager.driveModeStateFlow.debounce(50L).collect { (past, new) ->
            runCatching {
                // guard: no init
                if (new == -1) return@collect

                // No toggle before
                val isDefaultDM = past == -1 && CarPropertyValue.DRIVE_MODE_SELECTION_COMFORT == new

                canNotifLog(
                    "DMChanged",
                    "[${past.getDriveModeName()}, ${new.getDriveModeName()}], isDefault=$isDefaultDM"
                )

                // Optional headrest notification
                if (canNotify && !isDefaultDM && runtimeTimer.hasElapsed(SILENT_START)) {
                    canNotifLog("DMChanged", "play notif")

                    playHeadrestNotification(driveMode = new)

                    // Show DM overlay if enabled
                    if (driveModeOverlay) {
                        stateKeeper.setDriveModeInOverlay(new)
                        withContext(Dispatchers.Main) { startOverlay<DriveModeOverlayService>(this@App) }
                    }
                }

                // App started when drive mode was already changed
                if (past == -1 && CarPropertyValue.DRIVE_MODE_SELECTION_COMFORT != new) {
                    canNotifLog(
                        "DMChanged",
                        "short enable (past = -1 and new != comfort)"
                    )
                    startupNotifGateJob?.cancel()
                    canNotify = true
                }

                // TODO TEST: The driving mode has already been switched, but notifications are still not enabled.
                if (past != -1 && !canNotify) {
                    canNotifLog("DMChanged", "short enable (past != -1 and !canNotify)")
                    startupNotifGateJob?.cancel()
                    canNotify = true
                }

                // TODO TEST: Active notif enable timer if default is comfort mode and no toggle after start
                if (isDefaultDM && !canNotify && (startupNotifGateJob == null || startupNotifGateJob?.isActive == false)) {
                    startLongTimeEnableNotifTask()
                }

                // Only after restoration confirmed
                if (!rememberDriveMode || !lastDriveModeRestored) return@collect

                val rememberedMode =
                    dataStore.getValueFlow(GeneralPrefs.REMEMBERED_DRIVE_MODE).first() ?: 0
                if (rememberedMode == new || !ignitionDriving()) return@collect

                debugLog("[DRIVE MODE] saved mode has been changed = ${new.getDriveModeName()}")
                dataStore.saveValue(GeneralPrefs.REMEMBERED_DRIVE_MODE, new)
            }.onFailure { Timber.e(it) }
        }
    }

    private fun CoroutineScope.collectIgnitionState() = launch {
        carManager.ignitionStateFlow.collect { value ->
            // Oh shit, let's go
            if (CarPropertyValue.IGNITION_STATE_DRIVING == value) {
                // Always start with notifications disabled on driving ignition
                canNotifLog("ignition", "reset by ignition")
                startupNotifGateJob?.cancel()
                canNotify = false

                val canRestoreDM = rememberDriveMode
                if (canRestoreDM) {
                    // Restoration flow will re-enable canNotify when done
                    rememberDriveModeChanged()
                } else {
                    // No restoration: enable notifications after short grace
                    startLongTimeEnableNotifTask()
                }

            }

            val displayValue = when (value) {
                CarPropertyValue.IGNITION_STATE_ACC -> "Acc"
                CarPropertyValue.IGNITION_STATE_DRIVING -> "Driving"
                CarPropertyValue.IGNITION_STATE_LOCK -> "Lock"
                CarPropertyValue.IGNITION_STATE_OFF -> "Off"
                CarPropertyValue.IGNITION_STATE_ON -> "On"
                CarPropertyValue.IGNITION_STATE_START -> "Start"
                CarPropertyValue.IGNITION_STATE_UNDEFINED -> "Undefined"
                else -> "Unknown"
            }
            debugDeepLog("[IGNITION STATE] $displayValue")
        }
    }

    private fun startLongTimeEnableNotifTask() {
        canNotifLog("longTask", "start delayed init")
        startupNotifGateJob?.cancel()
        startupNotifGateJob = appScope.launch {
            // Delay to waiting external flips
            if (hasOtherRestoreDMApps) {
                delay(NOTIFICATION_WITHOUT_DM_REMEMBER_LONG_DELAY)
            } else {
                delay(NOTIFICATION_WITHOUT_DM_REMEMBER_SHORT_DELAY)
            }
            if (ignitionDriving()) {
                canNotify = true
                canNotifLog("longTask", "ready")
            }
        }
    }

    private fun startShortTimeEnableNotifTask() {
        canNotifLog("shortTask", "start delayed init")
        startupNotifGateJob?.cancel()
        startupNotifGateJob = appScope.launch {
            delay(NOTIFICATION_WITH_DM_REMEMBER_DELAY) // small grace to avoid racing the final car event
            if (ignitionDriving()) {
                canNotifLog("shortTask", "ready")
                canNotify = true
            }
        }
    }

    private fun CoroutineScope.handleNotifPlayTest() = launch {
        stateKeeper.notifPlayTestFlow.collect { (sampleId, volume) ->
            driveModeNotifStore
                .find { it.id == sampleId }
                ?.let {
                    playHeadrestNotification(
                        driveMode = -1,
                        forceRes = it.res,
                        volume = volume
                    )
                }
        }
    }

    private fun CoroutineScope.handleToggleLauncher() = launch {
        stateKeeper.toggleLauncherFlow.collect {
            runCatching { toggleLauncher() }
            stateKeeper.sendLog("[LAUNCHER] toggle", true)
        }
    }

    private fun CoroutineScope.handleAdbActions() = launch {
        var stopDimByLaunch: Job? = null

        dataStore.getValueFlow(GeneralPrefs.ENABLE_ADB_HELPER, false).collect { enabled ->
            adbIsEnabled = enabled

            stopDimByLaunch?.cancel()
            stopDimByLaunch = if (enabled) launch {
                dataStore.getValueFlow(GeneralPrefs.ADB_DIM_AUTO_STOP, false).collect { needStop ->
                    if (needStop) {
                        adb.forceStop("com.autolink.diminteraction")
                        Timber.d("[ADB] force stop DIM")
                    }
                }
            } else null
        }
    }

    // -----------------------------------
    // Key triggers
    // -----------------------------------

    private fun handleKeyCode(keyCode: Int, event: Int, func: Int) = appScope.launch {
        debugLog("KEY_EVENT: code=$keyCode event=$event func=$func")
        if (suppressionMode) return@launch
        sendKeyCode(keyCode, event, func)
    }

    private fun handleShortClick(keyCode: Int, func: Int, tag: String) = appScope.launch {
        debugLog("SHORT_CLICK${if (tag.isNotEmpty()) "[$tag]" else ""}: code=$keyCode func=$func")
        if (suppressionMode) return@launch

        val key = KeyBindPattern.ShortClick(keyCode)
        if (keyBindingMode) {
            GlobalState.keyBindingFlow.emit(key)
            return@launch
        }
        key.handleTrigger()

        customShortClickAction(keyCode, func)
        sendShortClick(keyCode)
    }

    private fun handleOnLongPress(keyCode: Int, func: Int, tag: String) = appScope.launch {
        debugLog("LONG_PRESS${if (tag.isNotEmpty()) "[$tag]" else ""}: code=$keyCode func=$func")
        if (suppressionMode) return@launch

        val key = KeyBindPattern.LongPress(keyCode)
        if (keyBindingMode) {
            GlobalState.keyBindingFlow.emit(key)
            return@launch
        }
        key.handleTrigger()

        sendLongPress(keyCode)
    }

    private fun handleMultiLongPress(keyCodes: List<Int>) = appScope.launch {
        val keys = keyCodes.sorted() // less to bigger
        debugLog("MULTI_LONG_PRESS[C]: code=${keys.joinToString("+")}")
        if (suppressionMode) return@launch

        val key = KeyBindPattern.MultiLong(keyCodes)
        if (keyBindingMode) {
            GlobalState.keyBindingFlow.emit(key)
            return@launch
        }
        key.handleTrigger()

        sendMultiLongPress(keys)
    }

    @Suppress("SameParameterValue", "unused")
    private fun handleMultiClick(
        keyCodes: List<Int>,
        func: Int
    ) = appScope.launch {
        val keys = keyCodes.sorted() // less to bigger
        debugLog("MULTI_SHORT_CLICK: code=${keys.joinToString("+")} func=$func")
        if (suppressionMode) return@launch
        sendMultiClick(keys)
    }

    private fun handleDoubleClick(
        keyCode: Int,
        func: Int,
        tag: String
    ) = appScope.launch {
        debugLog("DOUBLE_CLICK${if (tag.isNotEmpty()) "[$tag]" else ""}: code=$keyCode func=$func")
        if (suppressionMode) return@launch

        val key = KeyBindPattern.DoubleClick(keyCode)
        if (keyBindingMode) {
            GlobalState.keyBindingFlow.emit(key)
            return@launch
        }
        key.handleTrigger()

        sendDoubleClick(keyCode)
    }

    private fun handleHoldStart(keyCode: Int, func: Int) = appScope.launch {
        debugDeepLog("HOLD_START: code=$keyCode func=$func")
        if (suppressionMode) return@launch
        sendHoldStart(keyCode)
    }

    private fun handleHoldStop(keyCode: Int, func: Int) = appScope.launch {
        debugDeepLog("HOLD_STOP: code=$keyCode func=$func")
        if (suppressionMode) return@launch
        sendHoldStop(keyCode)
    }

    private fun KeyBindPattern.handleTrigger() {
        val bindName = keyBindStorage.getBindName(this)
        keyBinds[bindName]?.let { bind ->
            when (bind.action) {
                KeyBindAction.LAUNCH_APP -> runCatching {
                    val pkg = bind.value
                    launchApp(pkg)
                    debugDeepLog("[KEY_BIND]: $bindName triggered, launching $pkg")
                }.onFailure { Timber.e(it) }

                KeyBindAction.LAUNCH_LINK -> runCatching {
                    val intent = Intent.parseUri(bind.value, Intent.URI_INTENT_SCHEME)
                    val shortcutName = intent.getStringExtra("gib_name") ?: ""
                    intent.removeExtra("gib_name")
                    intent.removeExtra("gib_package")
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    appScope.launch(Dispatchers.Main) { runCatching { startActivity(intent) } }
                    debugDeepLog("[KEY_BIND]: $bindName triggered, launching $shortcutName shortcut")
                }.onFailure { Timber.e(it) }

                KeyBindAction.TOGGLE_DM -> bind.toggleDriveMode()

                KeyBindAction.CAROUSEL_DM -> bind.carouselDriveMode()

                KeyBindAction.PHONE_CALL -> appScope.launch {
                    GlobalState.requestPhoneCallFlow.emit(bind.value)
                }

                KeyBindAction.CAMERAS_360 -> stateKeeper.setToggleCamera(true)

                KeyBindAction.CAROUSEL_LAMP -> bind.carouselLampMode()

                KeyBindAction.CAROUSEL_AUDIO_SOURCE -> bind.carouselAudioSource()

                KeyBindAction.APP_LAUNCHER -> toggleLauncher()

                KeyBindAction.TASK_MANAGER -> callTaskManager()

                KeyBindAction.ANDROID_BACK -> if (adbIsEnabled) {
                    appScope.launch(Dispatchers.IO) { adb.pressBack() }
                } else {
                    stateKeeper.sendAccessibilityServiceSignal(AccessibilityServiceSignal.GoBack)
                }

                KeyBindAction.ANDROID_HOME -> if (adbIsEnabled) {
                    appScope.launch(Dispatchers.IO) { adb.pressHome() }
                } else {
                    stateKeeper.sendAccessibilityServiceSignal(AccessibilityServiceSignal.GoHome)
                }

                KeyBindAction.NAVIGATE_TO_PAST_APP -> navigateToPastApp()
            }
        }
    }

    private fun navigateToPastApp() = appScope.launch(Dispatchers.IO) {
        if (stateKeeper.canAccessibility.value) {
            val available = launcherData.allApps.value.map { it.packageName }
            val current = stateKeeper.visibleAppState.value
            val target = stateKeeper.visibleAppsState.value
                .asSequence()
                .map { it.trim() }
                .filter { it.isNotEmpty() && it in available }
                .firstOrNull { it != current }

            if (target != null) softOpenApp(target)
        } else {
            withContext(Dispatchers.Main) { inMainToast(getString(R.string.accessibility_service_disabled)) }
        }
    }

    private fun KeyBindConfig.toggleDriveMode() = appScope.launch(Dispatchers.IO) {
        runCatching {
            toggleDriveModeTaskMutex.withLock {
                // Guard: ignition must be on
                if (!ignitionDriving()) {
                    inMainToast(getString(R.string.turn_on_ignition))
                    return@withLock
                }

                // Reset enable notify task
                canNotifLog("toggleDriveMode", "short enable (user keybind event)")
                startupNotifGateJob?.cancel()
                canNotify = true

                val currentDM =
                    carManager.getIntProperty(CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT)
                val targetDM = value.toIntOrNull() ?: 0

                // Guard: invalid values
                if (targetDM == 0 || currentDM == 0 || currentDM == -1) {
                    inMainToast(getString(R.string.driving_mode_switch_error))
                    return@withLock
                }

                // Read remembered pair under the same lock (no nested locking)
                val raw = dataStore.getValueFlow(GeneralPrefs.TOGGLE_DM_TASK).first().orEmpty()
                val parts = raw.split('|').map { it.trim().toIntOrNull() ?: 0 }
                val toggleDmTask =
                    if (parts.size == 2 && parts.all { it != 0 }) parts[0] to parts[1] else null

                if (toggleDmTask == null) {
                    // Only skip if already in target on the first leg
                    if (currentDM == targetDM) {
                        return@withLock
                    }

                    // Remember switching
                    dataStore.saveValue(GeneralPrefs.TOGGLE_DM_TASK, "$currentDM|$targetDM")

                    // Send toggle car command
                    carManager.setPropertyIntValue(
                        CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT,
                        Integer.MIN_VALUE,
                        targetDM
                    )
                    debugLog("[DRIVE MODE] toggle to ${targetDM.getDriveModeName()}")
                } else {
                    val (pastDM, rememberedDm) = toggleDmTask

                    // DM was changed from other source when toggle pair remembered
                    if ((currentDM != pastDM && currentDM != rememberedDm && currentDM != targetDM) ||
                        (currentDM == pastDM && rememberedDm != targetDM)
                    ) {
                        carManager.setPropertyIntValue(
                            CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT,
                            Integer.MIN_VALUE,
                            targetDM
                        )
                        dataStore.saveValue(GeneralPrefs.TOGGLE_DM_TASK, "$currentDM|$targetDM")
                        debugLog("[DRIVE MODE] toggle to ${targetDM.getDriveModeName()}")
                    } else if (currentDM == rememberedDm && targetDM == rememberedDm) {
                        // Second leg: go back to past mode if we reached the remembered target
                        carManager.setPropertyIntValue(
                            CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT,
                            Integer.MIN_VALUE,
                            pastDM
                        )
                        dataStore.removeValue(GeneralPrefs.TOGGLE_DM_TASK)
                        debugLog("[DRIVE MODE] toggle to ${pastDM.getDriveModeName()}")
                    } else {
                        // Update pair and go to new target
                        if (currentDM != targetDM) {
                            carManager.setPropertyIntValue(
                                CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT,
                                Integer.MIN_VALUE,
                                targetDM
                            )
                            debugLog("[DRIVE MODE] toggle to ${targetDM.getDriveModeName()}")
                        }
                        dataStore.saveValue(GeneralPrefs.TOGGLE_DM_TASK, "$pastDM|$targetDM")
                    }
                }
            }
        }.onFailure { Timber.e(it) }
    }

    private fun KeyBindConfig.carouselDriveMode() = appScope.launch(Dispatchers.IO) {
        runCatching {
            toggleDriveModeTaskMutex.withLock {
                // Guard: ignition must be on
                if (!ignitionDriving()) {
                    inMainToast(getString(R.string.turn_on_ignition))
                    return@withLock
                }

                // Reset enable notify task
                canNotifLog("toggleDriveMode", "short enable (user keybind event)")
                startupNotifGateJob?.cancel()
                canNotify = true

                val currentDM =
                    carManager.getIntProperty(CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT)
                val targetDM = value
                    .split('|')
                    .map { it.trim().toIntOrNull() ?: 0 }
                    .filter { it != 0 }
                    .nextAfterOrFirst(currentDM, 0)

                // Guard: wrong value
                if (targetDM == 0 || currentDM == 0 || currentDM == -1) {
                    inMainToast(getString(R.string.driving_mode_switch_error))
                    return@withLock
                }

                carManager.setPropertyIntValue(
                    CarPropertyKey.DM_FUNC_DRIVE_MODE_SELECT,
                    Integer.MIN_VALUE,
                    targetDM
                )
                debugLog("[DRIVE MODE] toggle to ${targetDM.getDriveModeName()}")
            }
        }.onFailure { Timber.e(it) }
    }

    private fun KeyBindConfig.carouselLampMode() = appScope.launch(Dispatchers.IO) {
        runCatching {
            val currentLM =
                carManager.getIntProperty(CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL)
            val listLM = value
                .split('|')
                .map { it.trim().toIntOrNull() ?: -1 }
                .filter { it != -1 }
            val targetLM = listLM.nextAfterOrFirst(currentLM, -1)

            if (targetLM == -1 || currentLM == -1) {
                inMainToast(getString(R.string.error))
                return@runCatching
            }

            carManager.setPropertyIntValue(
                CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL,
                Integer.MIN_VALUE,
                targetLM
            )

            // Notify and show overlay
            stateKeeper.setLampModeInOverlay(listLM to targetLM)
            withContext(Dispatchers.Main) { startOverlay<LampModeOverlayService>(this@App) }

            DISPLAY_LAMP_MODES.find { it.id == targetLM }?.let { lm ->
                debugLog("[LAMP MODE] toggle to ${lm.displayName}")
            }
        }.onFailure { Timber.e(it) }
    }

    private fun KeyBindConfig.carouselAudioSource() = appScope.launch(Dispatchers.IO) {
        runCatching {
            carouselAudioSourceMutex.withLock {
                val manager = mMediaCenterManager?.takeIf { it.isAlive }
                if (manager == null) {
                    inMainToast(getString(R.string.error))
                    return@withLock
                }
                val sources = value
                    .split('|')
                    .map { it.trim() }
                    .mapNotNull { it.asAudioSource() }
                    .distinct()
                if (sources.isEmpty()) return@withLock
                val current = manager.currentAudioSource ?: return@withLock
                val target = nextCarouselAudioSource(sources, current)
                if (target == current) {
                    debugDeepLog("[KEY_BIND] carousel audio: already on $target")
                    return@withLock
                }
                inMainToast(this@App.getAudioSourceDisplayLabel(target))

                runCatching { carouselPauseOldSourceBeforeSwitch(current) }
                    .onFailure { Timber.e(it) }
                runCatching { requestCarouselAudioSourceForTarget(manager, target) }
                    .onFailure { Timber.e(it) }
                val settled = waitForCarouselAudioSourceToSettle(manager, target)
                runCatching { carouselPlayNewSourceAfterSwitch(target, settled) }
                    .onFailure { Timber.e(it) }
                debugDeepLog("[KEY_BIND] carousel audio: $current -> $target (settled=$settled)")
            }
        }.onFailure { Timber.e(it) }
    }

    private fun carouselPauseOldSourceBeforeSwitch(oldSource: MediaCenterConstant.AudioSource) {
        when (oldSource) {
            MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO -> {
                mMediaCenterManager?.radioManager?.pause()
                debugDeepLog("[KEY_BIND] carousel audio: pause old RADIO (radioManager)")
                return
            }

            MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT,
            MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB -> {
                runCatching { mMediaCenterManager?.musicAdapterManager?.pause() == 1 }.onFailure { Timber.e(it) }
                debugDeepLog("[KEY_BIND] carousel audio: pause old BT/USB (musicAdapterManager)")
                return
            }

            else -> Unit
        }
        val controller = resolvePreferredControllerForPlayPause()
            ?: globalMediaControllers?.find { isAllowedMediaSessionPackage(it.packageName) }
        if (controller?.playbackState?.state == PlaybackState.STATE_PLAYING) {
            controller.transportControls?.pause()
            debugDeepLog("[KEY_BIND] carousel audio: pause old MediaSession ${controller.packageName}")
        } else {
            debugDeepLog("[KEY_BIND] carousel audio: pause old: session idle or no controller, $oldSource")
        }
    }

    private suspend fun carouselPlayNewSourceAfterSwitch(
        target: MediaCenterConstant.AudioSource,
        sourceSettled: Boolean
    ) {
        if (target == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            runCatching {
                mMediaCenterManager?.radioManager?.requestAudioSource()
                mMediaCenterManager?.radioManager?.play()
            }.onFailure { Timber.e(it) }
            debugDeepLog("[KEY_BIND] carousel audio: play RADIO (radioManager)")
            return
        }

        if (target == MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT ||
            target == MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB
        ) {
            val mediaCenter = mMediaCenterManager?.takeIf { it.isAlive } ?: return
            if (!sourceSettled && mediaCenter.currentAudioSource != target) {
                debugDeepLog("[KEY_BIND] carousel audio: skip BT/USB play, source=${mediaCenter.currentAudioSource} target=$target")
                return
            }
            runCatching { mediaCenter.musicAdapterManager?.play() == 1 }.onFailure { Timber.e(it) }
            debugDeepLog("[KEY_BIND] carousel audio: play new BT/USB (musicAdapterManager)")
            return
        }

        val mediaCenter = mMediaCenterManager?.takeIf { it.isAlive } ?: return
        if (!sourceSettled && mediaCenter.currentAudioSource != target) {
            debugDeepLog("[KEY_BIND] carousel audio: skip streaming play, source=${mediaCenter.currentAudioSource} target=$target")
            return
        }

        val activeController = resolvePreferredControllerForPlayPause()
            ?.takeIf { it.packageName in controlMediaApps }
        if (activeController != null) {
            if (isLegacySourceManagement()) resetIfOtherAudioSource()
            debugDeepLog("[KEY_BIND] carousel: play via ${activeController.packageName} (resolvePreferred)")
            activeController.transportControls?.play()
            currentMediaAppPackage = activeController.packageName ?: ""
            return
        }

        if (currentMediaAppPackage.isNotEmpty() && currentMediaAppPackage in controlMediaApps) {
            val byCurrent = globalMediaControllers?.find { it.packageName == currentMediaAppPackage }
            if (byCurrent != null) {
                if (isLegacySourceManagement()) resetIfOtherAudioSource()
                byCurrent.transportControls?.play()
                return
            }
        }

        val findController = globalMediaControllers?.find { it.packageName in controlMediaApps }
        if (findController != null) {
            if (isLegacySourceManagement()) resetIfOtherAudioSource()
            debugDeepLog("[KEY_BIND] carousel: play via control app ${findController.packageName}")
            findController.transportControls?.play()
            currentMediaAppPackage = findController.packageName
            return
        }

        if (defaultMediaApps.isNotEmpty()) {
            val findDefaultController = globalMediaControllers?.find { it.packageName == defaultMediaApps }
            if (findDefaultController != null) {
                if (findDefaultController.playbackState?.state == PlaybackState.STATE_PLAYING) {
                    debugDeepLog("[KEY_BIND] carousel: default app session already playing, no-op play")
                } else {
                    if (isLegacySourceManagement()) resetIfOtherAudioSource()
                    findDefaultController.transportControls?.play()
                }
                currentMediaAppPackage = defaultMediaApps
                return
            }
            if (isLegacySourceManagement()) resetIfOtherAudioSource()
            debugDeepLog("[KEY_BIND] carousel: open default and play $defaultMediaApps")
            if (defaultMediaApps == YAM_PACKAGE) {
                sendYmAutoPlayCompat()
            } else if (defaultMediaApps == HAV_YM_PACKAGE) {
                openApp(HAV_YM_UMA_PACKAGE)
            } else {
                openApp(defaultMediaApps)
                delay(OPEN_APP_TO_SEND_PLAY_PAUSE)
                sendMediaActionToApp(defaultMediaApps, AppMediaAction.PLAY)
                if (defaultMediaApps == MURGLAR_PACKAGE) {
                    delay(PLAYER_COMPAT_ACTION_DELAY)
                    sendMurglarAutoPlayCompat()
                }
                if (defaultMediaApps == VKX_PACKAGE) {
                    delay(PLAYER_COMPAT_ACTION_DELAY)
                    sendVkxAutoPlayCompat()
                }
            }
            currentMediaAppPackage = defaultMediaApps
        } else {
            debugDeepLog("[KEY_BIND] carousel: no default player to start")
        }
    }

    private fun callTaskManager() = appScope.launch(Dispatchers.Main) {
        if (!adbIsEnabled) {
            inMainToast(getString(R.string.adb_required_title))
            return@launch
        }
        runCatching {
            if (isServiceRunning<TaskManagerOverlayService>(this@App)) {
                stateKeeper.callTaskManager()
            } else {
                startOverlay<TaskManagerOverlayService>(this@App)
            }
        }.onFailure { Timber.e(it) }
    }

    private fun <T> List<T>.nextAfterOrFirst(target: T, default: T): T {
        if (isEmpty()) return default
        val i = indexOf(target)
        val nextIndex = when (i) {
            -1, lastIndex -> 0
            else -> i + 1
        }
        return this[nextIndex]
    }

    private suspend fun String.minimizePkg() {
        fun goHomeViaAs() =
            stateKeeper.sendAccessibilityServiceSignal(AccessibilityServiceSignal.GoHome)

        if (adbIsEnabled) withContext(Dispatchers.IO) {
            adb.getTaskId(this@minimizePkg)?.let { taskId ->
                adb.minimize(taskId)
            } ?: run { goHomeViaAs() }
        } else goHomeViaAs()
    }

    private fun toggleLauncher() = appScope.launch(Dispatchers.Main) {
        // Open overlay when user in restricted apps
        if (currentVisibleApp in OVERLAY_RESTRICTED_PKGS) {
            // Stop overlay and kill launcher background activity
            if (isLauncherServiceRunning(this@App)) {
                stopLauncherOverlay(this@App)
            }
            finishLauncherEntryActivitiesIfRunning()

            // Go home with delay
            currentVisibleApp.minimizePkg()
            delay(MINIMIZE_SYSTEM_DELAY)

            // Relaunch overlay
            startLauncherOverlay(this@App)
            return@launch
        }

        // Normal overlay toggling
        if (isLauncherServiceRunning(this@App)) {
            stopLauncherOverlay(this@App)
        } else {
            startLauncherOverlay(this@App)
        }
    }

    // -----------------------------------
    // Custom actions
    // -----------------------------------

    private suspend fun customShortClickAction(keyCode: Int, func: Int) {
        if (mediaControlEnabled) {

            // Wrong audio source and not foreground available player
            if (isLegacySourceManagement() &&
                mMediaCenterManager?.currentAudioSource != AUDIO_SOURCE &&
                currentVisibleApp !in controlMediaApps
            ) return

            // AC opened
            if (disableOnClimate && geelyACIsOpened) return

            // During phone call
            // if (disableDuringCall && isCallActive()) return

            // No media apps
            if (controlMediaApps.isEmpty()) {
                inMainToast(getString(R.string.configure_media_apps))
                return
            }

            // Locked by time task
            if (mediaControlTimeLock) return

            customMediaControlAction(keyCode, func)
        }
    }

    private suspend fun customMediaControlAction(keyCode: Int, func: Int) {
        if (radioBtControl && handleBtRadioByMediaCenter(keyCode, func)) return

        when (keyCode) {
            KeyCode.KEYCODE_R_MEDIA_PREVIOUS -> try {
                debugDeepLog("[MEDIA_EVENT]: Previous")
                val activeController = globalActiveMediaController
                    ?.takeIf { isAllowedMediaSessionPackage(it.packageName) }

                if (activeController != null) {
                    debugDeepLog("[MEDIA_EVENT]: Sending 'Previous' to active MediaSession")
                    sendSessionSkip(activeController, isNext = false)
                    currentMediaAppPackage = activeController.packageName ?: ""
                } else if (currentMediaAppPackage.isEmpty()) {
                    debugDeepLog("[MEDIA_EVENT]: No current player")
                    val findController =
                        globalMediaControllers?.find { isAllowedMediaSessionPackage(it.packageName) }
                    if (findController != null) {
                        debugDeepLog("[MEDIA_EVENT]: Sending 'Previous' to found MediaSession")
                        sendSessionSkip(findController, isNext = false)
                        currentMediaAppPackage = findController.packageName
                    } else if (defaultMediaApps.isNotEmpty()) {
                        debugDeepLog("[MEDIA_EVENT]: Sending 'Previous' via intent to default app")
                        sendMediaActionToApp(defaultMediaApps, AppMediaAction.PREVIOUS)
                    }
                } else {
                    debugDeepLog("[MEDIA_EVENT]: With current player")
                    debugDeepLog("[MEDIA_EVENT]: Find session and sending 'Previous' to current player $currentMediaAppPackage")
                    val findController =
                        globalMediaControllers?.find {
                            it.packageName == currentMediaAppPackage &&
                                    isAllowedMediaSessionPackage(it.packageName)
                        }
                    findController?.let { sendSessionSkip(it, isNext = false) } ?: run {
                        sendMediaActionToApp(
                            currentMediaAppPackage,
                            AppMediaAction.PREVIOUS
                        )
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }

            KeyCode.KEYCODE_R_MEDIA_PLAY_PAUSE -> try {
                val activeController = resolvePreferredControllerForPlayPause()
                if (activeController != null) {
                    if (activeController.playbackState?.state == PlaybackState.STATE_PLAYING) {
                        debugDeepLog("[MEDIA_EVENT]: Send pause to active MediaSession")
                        activeController.transportControls?.pause()
                    } else {
                        if (isLegacySourceManagement()) resetIfOtherAudioSource()
                        debugDeepLog("[MEDIA_EVENT]: Send play to active MediaSession")
                        activeController.transportControls?.play()
                    }
                    currentMediaAppPackage = activeController.packageName ?: ""
                } else if (currentMediaAppPackage.isEmpty()) {
                    debugDeepLog("[MEDIA_EVENT]: No current player")
                    // Trying to locate the active media controller on the player
                    val findController =
                        globalMediaControllers?.find { it.packageName in controlMediaApps }
                    if (findController != null) {
                        if (findController.playbackState?.state == PlaybackState.STATE_PLAYING) {
                            debugDeepLog("[MEDIA_EVENT]: Found MediaSession and send pause")
                            findController.transportControls.pause()
                        } else {
                            // Switching the audio source if required
                            if (isLegacySourceManagement()) resetIfOtherAudioSource()
                            debugDeepLog("[MEDIA_EVENT]: Found MediaSession and send play")
                            findController.transportControls.play()
                        }
                        currentMediaAppPackage = findController.packageName
                    } else if (defaultMediaApps.isNotEmpty()) {
                        debugDeepLog("[MEDIA_EVENT]: With default player")
                        // Trying to locate the default media controller on the player
                        val findDefaultController =
                            globalMediaControllers?.find { it.packageName == defaultMediaApps }
                        findDefaultController?.let { controller ->
                            if (controller.playbackState?.state == PlaybackState.STATE_PLAYING) {
                                debugDeepLog("[MEDIA_EVENT]: Found MediaSession by default player and send pause")
                                controller.transportControls.pause()
                            } else {
                                // Switching the audio source if required
                                if (isLegacySourceManagement()) resetIfOtherAudioSource()
                                debugDeepLog("[MEDIA_EVENT]: Found MediaSession by default player and send play")
                                controller.transportControls.play()
                            }
                            currentMediaAppPackage = defaultMediaApps
                        } ?: run {
                            // Switching the audio source if required
                            if (isLegacySourceManagement()) resetIfOtherAudioSource()
                            debugDeepLog("[MEDIA_EVENT]: Send default open app and play $defaultMediaApps")
                            // launch and play default player
                            if (defaultMediaApps == YAM_PACKAGE) {
                                sendYmAutoPlayCompat()
                            } else if (defaultMediaApps == HAV_YM_PACKAGE) {
                                openApp(HAV_YM_UMA_PACKAGE)
                            } else {
                                openApp(defaultMediaApps)
                                delay(OPEN_APP_TO_SEND_PLAY_PAUSE)
                                sendMediaActionToApp(defaultMediaApps, AppMediaAction.PLAY)

                                if (defaultMediaApps == MURGLAR_PACKAGE) {
                                    delay(PLAYER_COMPAT_ACTION_DELAY)
                                    sendMurglarAutoPlayCompat()
                                }

                                if (defaultMediaApps == VKX_PACKAGE) {
                                    delay(PLAYER_COMPAT_ACTION_DELAY)
                                    sendVkxAutoPlayCompat()
                                } else Unit
                            }
                        }
                    }
                } else {
                    debugDeepLog("[MEDIA_EVENT]: With current player")
                    // Trying to locate the active media controller on the player
                    val findController =
                        globalMediaControllers?.find { it.packageName == currentMediaAppPackage }
                    findController?.let { controller ->
                        if (controller.playbackState?.state == PlaybackState.STATE_PLAYING) {
                            debugDeepLog("[MEDIA_EVENT]: Find last MediaSession by current player and send pause")
                            controller.transportControls.pause()
                        } else {
                            // Switching the audio source if required
                            if (isLegacySourceManagement()) resetIfOtherAudioSource()
                            debugDeepLog("[MEDIA_EVENT]: Find last MediaSession by current player and send play")
                            controller.transportControls.play()
                        }
                    } ?: run {
                        // Switching the audio source if required
                        if (isLegacySourceManagement()) resetIfOtherAudioSource()
                        debugDeepLog("[MEDIA_EVENT]: Sending 'Play' via intent to current player $currentMediaAppPackage")
                        // Sending a command via the intent
                        sendMediaActionToApp(
                            currentMediaAppPackage,
                            AppMediaAction.TOGGLE
                        )
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }

            KeyCode.KEYCODE_R_MEDIA_NEXT -> try {
                debugDeepLog("[MEDIA_EVENT]: Next")
                val activeController = globalActiveMediaController
                    ?.takeIf { isAllowedMediaSessionPackage(it.packageName) }

                if (activeController != null) {
                    debugDeepLog("[MEDIA_EVENT]: Sending 'Next' to active MediaSession")
                    sendSessionSkip(activeController, isNext = true)
                    currentMediaAppPackage = activeController.packageName ?: ""
                } else if (currentMediaAppPackage.isEmpty()) {
                    debugDeepLog("[MEDIA_EVENT]: No current player")
                    val findController =
                        globalMediaControllers?.find { isAllowedMediaSessionPackage(it.packageName) }
                    if (findController != null) {
                        debugDeepLog("[MEDIA_EVENT]: Sending 'Next' to found MediaSession")
                        sendSessionSkip(findController, isNext = true)
                        currentMediaAppPackage = findController.packageName
                    } else if (defaultMediaApps.isNotEmpty()) {
                        debugDeepLog("[MEDIA_EVENT]: Sending 'Next' via intent to default app $defaultMediaApps")
                        sendMediaActionToApp(defaultMediaApps, AppMediaAction.NEXT)
                    }
                } else {
                    debugDeepLog("[MEDIA_EVENT]: With current player")
                    debugDeepLog("[MEDIA_EVENT]: Find session and sending 'Next' to current player $currentMediaAppPackage")
                    val findController =
                        globalMediaControllers?.find {
                            it.packageName == currentMediaAppPackage &&
                                    isAllowedMediaSessionPackage(it.packageName)
                        }
                    findController?.let { sendSessionSkip(it, isNext = true) } ?: run {
                        sendMediaActionToApp(
                            currentMediaAppPackage,
                            AppMediaAction.NEXT
                        )
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }

            else -> Unit
        }
    }

    private suspend fun handleBtRadioByMediaCenter(keyCode: Int, func: Int): Boolean {
        val mediaCenter = mMediaCenterManager?.takeIf { it.isAlive } ?: return false
        val source = mediaCenter.currentAudioSource
        if (source !in BT_RADIO_SOURCES) return false

        return runCatching {
            val handled = when (keyCode) {
                KeyCode.KEYCODE_R_MEDIA_PREVIOUS -> {
                    source == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO
                        && mediaCenter.radioManager?.seekAsync(1) == true ||
                        mediaCenter.musicAdapterManager?.prev() == 1
                }

                KeyCode.KEYCODE_R_MEDIA_NEXT -> {
                    source == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO
                        && mediaCenter.radioManager?.seekAsync(0) == true ||
                        mediaCenter.musicAdapterManager?.next() == 1
                }

                KeyCode.KEYCODE_R_MEDIA_PLAY_PAUSE -> {
                    val forcePause = func == MEDIA_CODE_PAUSE
                    val forcePlay = func == MEDIA_CODE_PLAY

                    val fgPackage = currentVisibleApp
                    if (source in BT_RADIO_SOURCES && fgPackage.isNotEmpty() && fgPackage in controlMediaApps) {
                        ensureOnlineAudioSource()
                        return@runCatching false
                    }

                    if (source == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
                        val radioStatus = mediaCenter.radioManager?.radioStatus ?: 0
                        if (radioStatus == MEDIA_CODE_PAUSE) {
                            mediaCenter.radioManager?.pause() == true
                        } else if (radioStatus == MEDIA_CODE_PLAY) {
                            mediaCenter.radioManager?.requestAudioSource()
                            mediaCenter.radioManager?.play() == true
                        } else {
                            mediaCenter.radioManager?.requestAudioSource()
                            mediaCenter.radioManager?.play() == true
                        }
                    } else if (forcePause) {
                        mediaCenter.musicAdapterManager?.pause() == 1
                    } else if (forcePlay) {
                        mediaCenter.musicAdapterManager?.play() == 1
                    } else {
                        false
                    }
                }

                else -> false
            }

            if (handled) {
                debugDeepLog("[MEDIA_EVENT]: BT/Radio routed via MediaCenter, source=$source")
            }
            handled
        }.getOrElse {
            Timber.e(it)
            false
        }
    }

    private fun resolvePreferredControllerForPlayPause(): MediaController? {
        val fgPackage = currentVisibleApp

        if (isAllowedMediaSessionPackage(fgPackage)) {
            globalMediaControllers
                ?.find { it.packageName == fgPackage }
                ?.let { return it }
        }

        return globalActiveMediaController
            ?.takeIf { isAllowedMediaSessionPackage(it.packageName) }
    }

    private suspend fun ensureOnlineAudioSource() {
        val manager = mMediaCenterManager?.takeIf { it.isAlive } ?: return
        if (manager.currentAudioSource == AUDIO_SOURCE) return
        resetAudioSource()
        repeat(6) {
            if (manager.currentAudioSource == AUDIO_SOURCE) return
            delay(120)
        }
    }

    private fun applyKaraokeFocusOnBootIfNeeded() {
        if (karaokeFocusBoot) return

        val manager = mMediaCenterManager?.takeIf { it.isAlive } ?: return
        runCatching {
            sendBroadcast(
                Intent(KARAOKE_FOCUS_ACTION).apply {
                    `package` = "com.geely.mediacenterservice"
                    component = ComponentName(
                        "com.geely.mediacenterservice",
                        "com.geely.mediacenterservice.keyinput.KaraokeAppFocusReceiver"
                    )
                    putExtra(KARAOKE_FOCUS_EXTRA, true)
                }
            )
            karaokeFocusBoot = true
            debugDeepLog("[MediaCenterManager] karaoke focus enabled, source=${manager.currentAudioSource}")
        }.onFailure {
            Timber.e(it)
            debugDeepLog("[MediaCenterManager] karaoke focus failed")
        }
    }

    private fun kickKaraokeRetry() {
        if (karaokeFocusBoot) return
        if (karaokeRetryJob?.isActive == true) return

        karaokeRetryJob = appScope.launch {
            repeat(KARAOKE_RETRY_COUNT) {
                if (karaokeFocusBoot) return@launch
                delay(KARAOKE_RETRY_DELAY_MS)
                applyKaraokeFocusOnBootIfNeeded()
            }
        }
    }

    private fun sendSessionSkip(controller: MediaController, isNext: Boolean) {
        val keyCode = if (isNext) KeyEvent.KEYCODE_MEDIA_NEXT else KeyEvent.KEYCODE_MEDIA_PREVIOUS

        val down = runCatching {
            controller.dispatchMediaButtonEvent(KeyEvent(KeyEvent.ACTION_DOWN, keyCode))
        }.getOrDefault(false)
        val up = runCatching {
            controller.dispatchMediaButtonEvent(KeyEvent(KeyEvent.ACTION_UP, keyCode))
        }.getOrDefault(false)

        if (down || up) return

        controller.transportControls?.let {
            if (isNext) it.skipToNext() else it.skipToPrevious()
        }
    }

    // -----------------------------------
    // Push intent
    // -----------------------------------

    private fun sendKeyCode(keyCode: Int, event: Int, func: Int) {
        val intent = Intent().apply {
            action = "$BASE_PATH.KEY_EVENT"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCode)
            putExtra("event", event)
            putExtra("func", func)
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendShortClick(keyCode: Int) {
        val intent = Intent().apply {
            action = "$BASE_PATH.SHORT_CLICK"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCode)
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendLongPress(keyCode: Int) {
        val intent = Intent().apply {
            action = "$BASE_PATH.LONG_PRESS"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCode)
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendMultiLongPress(keyCodes: List<Int>) {
        val intent = Intent().apply {
            action = "$BASE_PATH.MULTI_LONG_PRESS"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCodes.joinToString("+"))
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendMultiClick(keyCodes: List<Int>) {
        val intent = Intent().apply {
            action = "$BASE_PATH.MULTI_SHORT_CLICK"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCodes.joinToString("+"))
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendDoubleClick(keyCode: Int) {
        val intent = Intent().apply {
            action = "$BASE_PATH.DOUBLE_CLICK"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCode)
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendHoldStart(keyCode: Int) {
        val intent = Intent().apply {
            action = "$BASE_PATH.HOLD_START"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCode)
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendHoldStop(keyCode: Int) {
        val intent = Intent().apply {
            action = "$BASE_PATH.HOLD_STOP"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("code", keyCode)
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendAudioSourceChanged(source: String) = appScope.launch {
        val intent = Intent().apply {
            action = "$BASE_PATH.AUDIO_SOURCE_CHANGED"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("source", source)
        }
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }

        // Send GMH
        runCatching {
            Intent().apply {
                action = "com.salat.gmediahud.UPDATE_AUDIO_SOURCE"
                setPackage("com.salat.gmediahud")
                putExtra("source", source)
            }.also { sendBroadcast(it) }
        }
    }

    private fun sendPlayState(isPlaying: Boolean) = appScope.launch {
        val intent = Intent().apply {
            action = "$BASE_PATH.PLAYBACK_STATE"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("isPlaying", if (isPlaying) "1" else "0")
        }
        debugDeepLog("[PLAYBACK_STATE] isPlaying: $isPlaying")
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    private fun sendPlaybackMetadata(data: PlaybackMetadata) = appScope.launch {
        val intent = Intent().apply {
            action = "$BASE_PATH.PLAYBACK_METADATA"
            if (!fullBroadcast) {
                `package` = MACRO_DROID_PACKAGE
            }
            putExtra("id", data.id)
            putExtra("packageName", data.packageName)
            putExtra("appName", data.appName)
            putExtra("title", data.title)
            putExtra("artist", data.artist)
            putExtra("album", data.album)
            putExtra("uri", data.uri)
            putExtra("coverUri", data.coverUri)
        }
        debugDeepLog("[PLAYBACK_METADATA] changed (${data})")
        appScope.launch(Dispatchers.IO) { sendBroadcast(intent) }
    }

    // -----------------------------------
    // Helper func
    // -----------------------------------

    private val hasOtherRestoreDMApps
        get() = systemApps.isMConfigInstalled() || systemApps.isDebugMInstalled()

    private fun startMediaControlLockTask(duration: Int) {
        if (taskMediaControlTimeLock?.isActive == true) {
            stopMediaControlLockTask()
        }
        mediaControlTimeLock = true
        debugDeepLog("[MEDIA_CONTROL]: lock out for $duration sec.")

        taskMediaControlTimeLock = appScope.launch {
            delay(duration * 1000L)
            mediaControlTimeLock = false
            debugDeepLog("[MEDIA_CONTROL]: unlock by time task")
            stopMediaControlLockTask()
        }
    }

    private fun resetMediaControlLockTask() {
        if (!mediaControlTimeLock) debugDeepLog("[MEDIA_CONTROL]: force unlock time task")
        stopMediaControlLockTask()
        mediaControlTimeLock = false
    }

    private fun stopMediaControlLockTask() {
        val bufferedTask = taskMediaControlTimeLock
        taskMediaControlTimeLock = null
        bufferedTask?.cancel()
    }

    private fun launchApp(packageName: String) {
        appScope.launch(Dispatchers.Main) {
            when (packageName) {
                DUSI_ASSISTANT_PACKAGE -> {
                    val intent = Intent().apply {
                        component = ComponentName(
                            DUSI_ASSISTANT_PACKAGE,
                            "$DUSI_ASSISTANT_PACKAGE.AssistActivity"
                        )
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }

                else -> this@App.openApp(packageName)
            }
        }
    }

    private suspend fun handlePlaybackMetadataChanged(playbackController: MediaController?) =
        playbackController?.let { controller ->
            try {
                val meta: MediaMetadata? = controller.metadata
                val packageName = controller.packageName ?: ""
                val appName = try {
                    systemApps
                        .getApps(roundIcon = false, iconQuality = 0, packageName)
                        .first()
                        .appName
                } catch (e: Exception) {
                    Timber.e(e)
                    ""
                }

                meta?.let {
                    // ID: media ID or fallback to title_artist
                    val mediaId = it.getString(MediaMetadata.METADATA_KEY_MEDIA_ID) ?: ""
                    val id = mediaId.takeUnless { id -> id.isBlank() }
                        ?: run {
                            val title = it.getString(MediaMetadata.METADATA_KEY_TITLE) ?: ""
                            val artist = it.getString(MediaMetadata.METADATA_KEY_ARTIST) ?: ""
                            ("${title}_$artist").generateFileId()
                        }

                    val title = meta.getString(MediaMetadata.METADATA_KEY_DISPLAY_TITLE)
                        ?: meta.getString(MediaMetadata.METADATA_KEY_TITLE)
                        ?: ""
                    val artist = meta.getString(MediaMetadata.METADATA_KEY_DISPLAY_SUBTITLE)
                        ?: meta.getString(MediaMetadata.METADATA_KEY_ARTIST)
                        ?: ""
                    val album = meta.getString(MediaMetadata.METADATA_KEY_ALBUM) ?: ""

                    val duration = it.getLong(MediaMetadata.METADATA_KEY_DURATION)

                    // Track URI
                    val uri = it.getString(MediaMetadata.METADATA_KEY_MEDIA_URI) ?: ""

                    // Cover art URI
                    val coverUri = it.getString(MediaMetadata.METADATA_KEY_ALBUM_ART_URI) ?: ""

                    val metaData = PlaybackMetadata(
                        id = id,
                        packageName = packageName,
                        appName = appName,
                        title = title,
                        artist = artist,
                        album = album,
                        duration = duration,
                        uri = uri,
                        coverUri = coverUri
                    )

                    if (metaData != lastPlaybackMetadata) {
                        sendPlaybackMetadata(metaData)
                        lastPlaybackMetadata = metaData
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    private fun resetIfOtherAudioSource() = runCatching {
        if (mMediaCenterManager?.currentAudioSource != AUDIO_SOURCE) resetAudioSource()
    }.onFailure { Timber.e(it) }

    private fun isLegacySourceManagement(): Boolean {
        return sourceManagement && !radioBtControl
    }

    private fun isOnlineBootSwitch(): Boolean {
        return sourceManagement || radioBtControl
    }

    private fun isAllowedMediaSessionPackage(packageName: String?): Boolean {
        if (packageName.isNullOrEmpty()) return false
        return packageName in controlMediaApps || packageName in NATIVE_SOURCE_SESSION_PACKAGES
    }

    private fun shouldSwitchToOnlineOnExternal(isPlaying: Boolean): Boolean {
        val fgPackage = currentVisibleApp
        val allowedExternalPlayerInForeground = fgPackage.isNotEmpty() && fgPackage in controlMediaApps
        val currentExternalPlaying = isPlaying && allowedExternalPlayerInForeground
        val isPlayEdge = !lastExternalPlayingState && currentExternalPlaying
        lastExternalPlayingState = currentExternalPlaying
        return isPlayEdge
    }

    private fun shouldSwitchOnline(isPlaying: Boolean): Boolean {
        if (!shouldSwitchToOnlineOnExternal(isPlaying)) return false

        if (!isOnlineBootSwitch() || !isPlaying) return false
        val activePkg = globalActiveMediaController?.packageName.orEmpty()
        if (activePkg in NATIVE_SOURCE_SESSION_PACKAGES) return false
        val currentSource = mMediaCenterManager?.currentAudioSource ?: return false
        if (currentSource !in BT_RADIO_SOURCES) return false

        val now = System.currentTimeMillis()
        if (now - lastOnlineSwitchAttemptAt < ONLINE_SWITCH_RETRY_INTERVAL_MS) return false
        lastOnlineSwitchAttemptAt = now
        return true
    }

    private fun switchOnlineForFgPlayback(fgPackage: String) {
        if (!isOnlineBootSwitch()) return
        if (fgPackage.isEmpty() || fgPackage !in controlMediaApps) return

        val isPlaying = globalMediaControllers
            ?.firstOrNull { it.packageName == fgPackage }
            ?.playbackState
            ?.state == PlaybackState.STATE_PLAYING
        if (!isPlaying) return

        val currentSource = mMediaCenterManager?.currentAudioSource ?: return
        if (currentSource !in BT_RADIO_SOURCES) return

        val now = System.currentTimeMillis()
        if (now - lastOnlineSwitchAttemptAt < ONLINE_SWITCH_RETRY_INTERVAL_MS) return
        lastOnlineSwitchAttemptAt = now
        resetIfOtherAudioSource()
    }

    private fun resetAudioSource() = runCatching {
        mMediaCenterManager?.requestAudioSource(AUDIO_SOURCE, MediaCenterConstant.AppSource.WECARFLOW)
    }.onFailure { Timber.e(it) }

    private suspend fun updateAvailableMediaApps(enabledApps: Set<String>? = null) {
        try {
            val fromParam = enabledApps?.filter { it.isNotEmpty() }.orEmpty()
            val includedApps = fromParam.ifEmpty {
                (dataStore.getValueFlow(NoBackupPrefs.ENABLED_MEDIA_APPS).first() ?: "")
                    .split('|')
                    .filter { it.trim().isNotEmpty() }
                    .ifEmpty {
                        systemApps.getAllApps(roundIcon = false, true, iconQuality = 0)
                            .filter { it.isMedia }
                            .map { it.packageName }
                            .filter { it !in IGNORED_MEDIA_APPS }
                    }
            }

            val allApps =
                systemApps.getAllApps(roundIcon = false, true, iconQuality = 0)
                    .map { it.packageName }
            val availableApps = allApps.filter { it in includedApps }
            controlMediaApps = availableApps

            // Set default
            if (defaultMediaApps.isEmpty() || defaultMediaApps !in controlMediaApps) {
                defaultMediaApps = availableApps.first()
            }
            // Reset current media app
            if (currentMediaAppPackage !in controlMediaApps) {
                currentMediaAppPackage = ""
            }
            Timber.d("[AS] Default: $defaultMediaApps Active: $currentMediaAppPackage Available apps: $availableApps")
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private fun debugLog(msg: String) {
        Timber.d(msg)
        if (!debugMode) return
        try {
            logActor.trySend(msg)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun debugDeepLog(msg: String) {
        Timber.d(msg)
        if (!debugMode || !deepLogs) return
        try {
            logActor.trySend(msg)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    // -----------------------------------
    // Classes
    // -----------------------------------

    inner class CustomIWidgetListDisplayChangeListener : IWidgetListDisplayChangeListener.Stub() {
        override fun psdWidgetListDisplay(isVisible: Boolean) {
        }

        override fun widgetListDisplay(isVisible: Boolean) {
            try {
                whWidgetIsVisible = isVisible
                if (hideMediaWidget && whWidgetIsVisible && whCurrentHomePage == 0) {
                    mLauncherManager?.toggleWidget()
                }
            } catch (e: Exception) {
                debugDeepLog("widgetListDisplay exception ${e.message ?: ""}")
            }
        }
    }

    inner class CustomILauncherPageSwitchListener : ILauncherPageSwitchListener.Stub() {
        override fun onPageSwitch(currentPage: Int) {
            try {
                whCurrentHomePage = currentPage
                if (hideMediaWidget && whWidgetIsVisible && whCurrentHomePage == 0) {
                    mLauncherManager?.toggleWidget()
                }
            } catch (e: Exception) {
                debugDeepLog("onPageSwitch exception ${e.message ?: ""}")
            }
        }

        override fun onPsdPageSwitch(currentPage: Int) {
        }
    }

    inner class CustomIBluetoothServicesListener : IBluetoothServicesListener.Stub() {
        override fun onCallAdded(callItem: GlyCallItem?) {
            val callNumber = callItem?.number
            val callState = callItem?.state
            debugDeepLog("[PHONE_MANAGER] call added $callState $callNumber")
        }

        override fun onCallAddedOther(callItem: GlyCallItem?, otherCallItem: GlyCallItem?) {
            val callNumber = callItem?.number
            val callState = callItem?.state
            debugDeepLog("[PHONE_MANAGER] call added other $callState $callNumber")
        }

        override fun onCallViewStateChange(statue: Int) {
            debugDeepLog("[PHONE_MANAGER] call state changed: $statue")
        }
    }

    inner class BaseServiceConnectionListener : ServiceConnectionListener {
        override fun onServiceConnectionChanged(isConnected: Boolean) {

            if (isConnected) {
                appScope.launch {
                    try {
                        OneOSApiManager
                            .getInstance(this@App)
                            .mediaCenterManager
                            ?.runHeartbeatPacket()
                    } catch (e: Exception) {
                        Timber.e(e)
                    }

                    // TODO Test delay
                    delay(500L)
                    onOneOSApiConnected()
                }
            } else {
                releaseAllManagersBinding()
            }
        }

        override fun onServiceBinderUpdated(code: Int) {
            if (code == 3) {
                appScope.launch {
                    try {
                        val mgr = OneOSApiManager
                            .getInstance(this@App)
                            .mediaCenterManager
                        if (mgr?.isAlive() == true) {
                            mgr.runHeartbeatPacket()
                        }
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }
        }
    }

    private fun <T> List<T>.addAndTrim(newItem: T, maxSize: Int = 100): List<T> {
        val appended: List<T> = this + newItem
        return if (appended.size <= maxSize) {
            appended
        } else {
            appended.drop(appended.size - maxSize)
        }
    }

    private suspend fun playHeadrestNotification(
        driveMode: Int,
        @RawRes forceRes: Int? = null,
        volume: Float? = null
    ): Boolean {
        // play test
        if (driveMode == -1) {
            val res = forceRes ?: R.raw.notif1
            return HeadrestNotifier.play(
                this@App,
                res,
                HeadrestNotifier.Policy.REPLACE,
                volume ?: 1f
            )
        } else {
            val notifId =
                dataStore.getValueFlow(intPreferencesKey("DM_NOTIF_SAMPLE_${driveMode}"))
                    .first() ?: -1
            if (notifId == -1) return false
            val notifVolume = volume
                ?: dataStore.getValueFlow(floatPreferencesKey("DM_NOTIF_VOLUME_${driveMode}"))
                    .first()
                ?: 1f

            val res = forceRes ?: driveModeNotifStore.find { it.id == notifId }?.res ?: R.raw.notif1
            return HeadrestNotifier.play(
                context = this@App,
                resId = res,
                policy = HeadrestNotifier.Policy.REPLACE,
                volumeFactor = notifVolume
            )
        }
    }

    private fun monitorPackageChangesOnDevice() = appScope.launch {
        runCatching {
            val launcherApps = getSystemService(LAUNCHER_APPS_SERVICE) as LauncherApps
            val mainHandler = Handler(Looper.getMainLooper())

            val callback = object : LauncherApps.Callback() {
                // Called when a package is newly installed for the current user
                override fun onPackageAdded(packageName: String?, user: android.os.UserHandle?) {
                    if (packageName != null) {
                        Timber.i("LA: added %s", packageName)
                        appScope.launch {
                            GlobalState.devicePackagesChangedFlow.emit(
                                PackagesChangedEvent.Added(packageName)
                            )
                        }
                    }
                }

                // Called when a package is removed for the current user
                override fun onPackageRemoved(packageName: String?, user: android.os.UserHandle?) {
                    if (packageName != null) {
                        Timber.i("LA: removed %s", packageName)
                        appScope.launch {
                            GlobalState.devicePackagesChangedFlow.emit(
                                PackagesChangedEvent.Removed(packageName)
                            )
                        }
                    }
                }

                // Called when the contents of an existing package change (enabled comps, perms, etc.)
                override fun onPackageChanged(packageName: String?, user: android.os.UserHandle?) {
                    if (packageName != null) {
                        Timber.i("LA: changed %s", packageName)
                        appScope.launch {
                            GlobalState.devicePackagesChangedFlow.emit(
                                PackagesChangedEvent.Changed(packageName)
                            )
                        }
                    }
                }

                // Optional: when packages become available (e.g., after move to storage)
                override fun onPackagesAvailable(
                    packageNames: Array<out String>?,
                    user: android.os.UserHandle?,
                    replacing: Boolean
                ) {
                }

                // Optional: when packages become unavailable
                override fun onPackagesUnavailable(
                    packageNames: Array<out String>?,
                    user: android.os.UserHandle?,
                    replacing: Boolean
                ) {
                }
            }

            // Register on main; keep a strong reference to avoid GC
            launcherApps.registerCallback(callback, mainHandler)
        }.onFailure { Timber.e(it) }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .allowRgb565(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(File(cacheDir, "coil"))
                    .maxSizePercent(0.15)
                    .build()
            }
            .components {
                add(IconRefKeyer())
                add(IconRefFetcher.Factory(this@App))
            }
            .crossfade(false)
            // .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
            .build()
    }
}
