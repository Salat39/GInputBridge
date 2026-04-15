package com.salat.gbinder.util

import android.content.ComponentName
import android.content.Context
import android.media.MediaMetadata
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.media.session.PlaybackState
import android.os.Handler
import android.os.HandlerThread
import com.salat.gbinder.MediaNotificationListenerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


private fun Context.obtainListenerComponent(): ComponentName =
    ComponentName(this, MediaNotificationListenerService::class.java)

/**
 * Flow emitting the package name of the application whose media playback state is currently
 * PLAYING, or, if nothing is playing, the package of the most recently active session.
 */
fun Context.activeMediaSessionControllerFlow(): Flow<Pair<MediaController?, List<MediaController>>> =
    callbackFlow {
        val sessionManager =
            getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
        val listenerComponent = obtainListenerComponent()

        // Select the appropriate packageName from the list of controllers
        fun pickPackage(controllers: List<MediaController>?): MediaController? {
            val list = controllers.orEmpty()
            // 1) If someone is playing, return their package
            list.firstOrNull { ctrl ->
                ctrl.playbackState?.state == PlaybackState.STATE_PLAYING
            }?.let { return it }

            // 2) Otherwise, return the session that played most recently
            return list
                .mapNotNull { ctrl ->
                    ctrl.playbackState?.let { pb ->
                        ctrl to pb.lastPositionUpdateTime
                    }
                }
                .maxByOrNull { it.second }
                ?.first
        }

        // Listener for changes in active sessions
        val sessionsListener =
            MediaSessionManager.OnActiveSessionsChangedListener { controllers ->
                val list = controllers?.toList().orEmpty()
                val active = pickPackage(list)
                trySend(active to list)
            }

        // Register listener and send initial value
        try {
            withContext(Dispatchers.Main) {
                sessionManager.addOnActiveSessionsChangedListener(
                    sessionsListener,
                    listenerComponent
                )
            }
            val initialList = sessionManager.getActiveSessions(listenerComponent).toList()
            val active = pickPackage(initialList)
            trySend(active to initialList)
        } catch (e: Exception) {
            Timber.e(e)
        }
        awaitClose {
            try {
                launch(Dispatchers.Main) {
                    sessionManager.removeOnActiveSessionsChangedListener(sessionsListener)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }.distinctUntilChanged { old, new -> old.first == new.first && old.second == new.second }

/**
 * Flow emitting only Boolean isPlaying, but emitting again if track changed.
 */
fun Context.isMediaPlayingFlow(): Flow<Boolean> = callbackFlow {
    val sessionMgr = getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
    val component = obtainListenerComponent()

    val cbThread = HandlerThread("MediaSessionCb").apply { start() }
    val cbHandler = Handler(cbThread.looper)

    // Map of controller -> its callback, for proper unsubscribe
    val callbacks = mutableMapOf<MediaController, MediaController.Callback>()

    // State holders for manual distinct filtering
    var lastIsPlaying = false

    // Compute and send playing state if either play/pause or trackId changed
    fun emitIfChanged(controllers: Collection<MediaController>) {
        try {
            // Determine if any session is playing
            val isPlaying =
                controllers.any { it.playbackState?.state == PlaybackState.STATE_PLAYING }

            // Only send when play/pause OR trackId changed
            if (isPlaying != lastIsPlaying) {
                lastIsPlaying = isPlaying
                trySend(isPlaying)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    // Subscribe to playbackState and metadata changes on each controller
    fun subscribe(controllers: List<MediaController>) {
        callbacks.forEach { (ctrl, cb) ->
            try {
                ctrl.unregisterCallback(cb)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        callbacks.clear()

        controllers.forEach { ctrl ->
            val cb = object : MediaController.Callback() {
                override fun onPlaybackStateChanged(state: PlaybackState?) {
                    super.onPlaybackStateChanged(state)
                    emitIfChanged(callbacks.keys)
                }

                override fun onMetadataChanged(metadata: MediaMetadata?) {
                    super.onMetadataChanged(metadata)
                    emitIfChanged(callbacks.keys)
                }
            }
            // register on main looper to avoid threading issues
            ctrl.registerCallback(cb, cbHandler)
            callbacks[ctrl] = cb
        }
    }

    // Listener for session list changes
    val sessionListener =
        MediaSessionManager.OnActiveSessionsChangedListener { newList ->
            val list = newList.orEmpty().toList()
            subscribe(list)
            emitIfChanged(list)
        }

    // Register session listener and initialize
    withContext(Dispatchers.Main) {
        sessionMgr.addOnActiveSessionsChangedListener(sessionListener, component)
    }
    val initial = sessionMgr.getActiveSessions(component).toList()
    subscribe(initial)
    emitIfChanged(initial)

    // Clean-up on close
    awaitClose {
        try {
            launch(Dispatchers.Main) {
                sessionMgr.removeOnActiveSessionsChangedListener(sessionListener)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        callbacks.forEach { (ctrl, cb) ->
            try {
                ctrl.unregisterCallback(cb)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        cbThread.quitSafely()
    }
}

/**
 * Flow emitting the current "active" MediaController (or null)
 * whenever the controller itself, its metadata, or its state changes.
 */
fun Context.activeMediaControllerFlow(): Flow<MediaController?> = callbackFlow {
    val mgr = getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
    val component = obtainListenerComponent()

    // Thread for callbacks
    val thread = HandlerThread("MediaCbThread").apply { start() }
    val handler = Handler(thread.looper)

    // Store callbacks for unsubscription
    val callbacks = mutableMapOf<MediaController, MediaController.Callback>()

    // Select the "active" controller
    fun pickActive(ctrls: Collection<MediaController>): MediaController? {
        return ctrls.firstOrNull { it.playbackState?.state == PlaybackState.STATE_PLAYING }
            ?: ctrls.maxByOrNull { it.playbackState?.lastPositionUpdateTime ?: 0L }
    }

    // Subscribe to all controllers
    fun subscribeAll(ctrls: List<MediaController>) {
        // Unsubscribe from old controllers
        callbacks.forEach { (ctrl, cb) ->
            try {
                ctrl.unregisterCallback(cb)
            } catch (_: Exception) {
            }
        }
        callbacks.clear()

        // Subscribe to each controller
        ctrls.forEach { ctrl ->
            val cb = object : MediaController.Callback() {
                override fun onPlaybackStateChanged(state: PlaybackState?) {
                    // Always send the current active controller
                    trySend(pickActive(callbacks.keys)).isSuccess
                }

                override fun onMetadataChanged(metadata: MediaMetadata?) {
                    trySend(pickActive(callbacks.keys)).isSuccess
                }
            }
            ctrl.registerCallback(cb, handler)
            callbacks[ctrl] = cb
        }
    }

    // Listener for changes in the session list
    val sessionListener = MediaSessionManager.OnActiveSessionsChangedListener { newList ->
        val list = newList.orEmpty().toList()
        subscribeAll(list)
        // Send initial value after the list changes
        trySend(pickActive(list)).isSuccess
    }

    // Register the listener
    mgr.addOnActiveSessionsChangedListener(sessionListener, component, handler)

    // Initialization
    val initial = mgr.getActiveSessions(component).toList()
    subscribeAll(initial)
    trySend(pickActive(initial)).isSuccess

    // Cleanup on flow closure
    awaitClose {
        mgr.removeOnActiveSessionsChangedListener(sessionListener)
        callbacks.forEach { (ctrl, cb) ->
            try {
                ctrl.unregisterCallback(cb)
            } catch (_: Exception) {
            }
        }
        thread.quitSafely()
    }
}.buffer(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
