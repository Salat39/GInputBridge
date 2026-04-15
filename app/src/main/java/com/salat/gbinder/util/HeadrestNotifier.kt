package com.salat.gbinder.util

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioDeviceInfo
import android.media.AudioFocusRequest
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.MediaPlayer
import android.media.audiofx.LoudnessEnhancer
import android.os.Build
import androidx.annotation.RawRes
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.log10

@Suppress("UNNECESSARY_SAFE_CALL")
object HeadrestNotifier {
    enum class Policy { REPLACE, SKIP }

    private val lock = Any()
    private var current: Playback? = null

    private const val MAX_BUST = 12f

    // Keeps no Context to avoid leaks; holds only AudioManager/Focus/Player
    private class Playback(
        private val am: AudioManager,
        private val focusReq: AudioFocusRequest,
        val mp: MediaPlayer,
        var le: LoudnessEnhancer? = null
    ) {
        private val closed = AtomicBoolean(false)

        fun stop(interrupted: Boolean) {
            runCatching { mp.setOnCompletionListener(null) }
            runCatching { mp.setOnErrorListener(null) }
            runCatching { mp.stop() }
            cleanup(pingMedia = !interrupted)
        }

        fun cleanup(pingMedia: Boolean) {
            if (!closed.compareAndSet(false, true)) return
            runCatching { le?.release() }.also { le = null }
            runCatching { mp.release() }
            runCatching { am.abandonAudioFocusRequest(focusReq) }
            if (pingMedia) pingMediaStreamNoFocus()
        }
    }

    fun play(
        context: Context,
        @RawRes resId: Int,
        policy: Policy = Policy.REPLACE,
        volumeFactor: Float = 1f // 0.0..++ ( >1.0 = boost via LoudnessEnhancer )
    ): Boolean {
        val appCtx = context.applicationContext
        val am = appCtx.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val navAttrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_NAVIGATION_GUIDANCE)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH) // or CONTENT_TYPE_SONIFICATION
            .build()

        val musicActive = runCatching { am.isMusicActive }.getOrDefault(false)
        val focusGain = if (musicActive)
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
        else
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT

        val focusReq = AudioFocusRequest.Builder(focusGain)
            .setAudioAttributes(navAttrs)
            .setAcceptsDelayedFocusGain(false)
            .build()

        if (am.requestAudioFocus(focusReq) != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) return false

        val afd = appCtx.resources.openRawResourceFd(resId) ?: run {
            am.abandonAudioFocusRequest(focusReq); return false
        }

        val mp = MediaPlayer()
        val pb = Playback(am, focusReq, mp)

        try {
            mp.setAudioAttributes(navAttrs)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                runCatching {
                    val outs = am.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
                    val candidate = outs.firstOrNull { info ->
                        info.type == AudioDeviceInfo.TYPE_BUS &&
                                (info.address?.contains("driver", true) == true ||
                                        info.address?.contains("headrest", true) == true)
                    }
                    candidate?.let { mp.setPreferredDevice(it) }
                }
            }

            try {
                mp.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            } finally {
                runCatching { afd.close() }
            }

            // --- VOLUME / BOOST ---
            // For <1.0 we attenuate via setVolume; for >1.0 we enable LoudnessEnhancer.
            val vf = volumeFactor.coerceAtLeast(0f)
            if (vf <= 1f) {
                // plain attenuation or unity
                mp.setVolume(vf, vf)
            } else {
                // unity to avoid double scaling; boost is done by the enhancer
                mp.setVolume(1f, 1f)
                // prepare enhancer after the player has an audio session
                // (safe to create now; session id is 0 before prepare on some devices)
            }

            mp.setOnCompletionListener {
                val doPing = synchronized(lock) {
                    val isCurrent = (current === pb)
                    if (isCurrent) current = null
                    isCurrent
                }
                pb.cleanup(pingMedia = doPing)
            }

            mp.setOnErrorListener { _, _, _ ->
                val doPing = synchronized(lock) {
                    val isCurrent = (current === pb)
                    if (isCurrent) current = null
                    isCurrent
                }
                pb.cleanup(pingMedia = doPing)
                true
            }

            mp.prepare()

            // Create/enable LoudnessEnhancer *after* prepare(), when session id is valid
            if (vf > 1f) {
                val sessionId = mp.audioSessionId
                val le = LoudnessEnhancer(sessionId)
                // Convert factor to dB: gainDb = 20 * log10(vf)
                val gainDb = (20f * log10(vf))
                // Android API expects millibels (1 dB = 100 mB). Cap to a sane max, e.g. +MAX_BUST dB.
                val gainMb = (gainDb.coerceAtMost(MAX_BUST) * 100f).toInt()
                runCatching {
                    le.setTargetGain(gainMb)
                    le.enabled = true
                }.onFailure { runCatching { le.release() } }
                pb.le = le
            }

            synchronized(lock) {
                when (policy) {
                    Policy.REPLACE -> {
                        current?.stop(interrupted = true)
                        current = pb
                        mp.start()
                        return true
                    }

                    Policy.SKIP -> {
                        if (current != null) {
                            pb.cleanup(pingMedia = false)
                            return false
                        } else {
                            current = pb
                            mp.start()
                            return true
                        }
                    }
                }
            }
        } catch (_: Throwable) {
            pb.cleanup(pingMedia = false)
            return false
        }
    }

    // Make MEDIA the last-active stream by playing ~40 ms of silence (no focus, no pause).
    private fun pingMediaStreamNoFocus() {
        val mediaAttrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        val sampleRate = 48000
        val channelMask = AudioFormat.CHANNEL_OUT_MONO
        val encoding = AudioFormat.ENCODING_PCM_16BIT

        val minSys = AudioTrack.getMinBufferSize(sampleRate, channelMask, encoding)
        if (minSys <= 0) return

        val framesTarget = 2048 // ≈42.7 ms @48 kHz
        val bytesTarget = framesTarget * 2 // 16-bit mono
        val bytes = kotlin.math.max(minSys, bytesTarget)
        val framesForMarker = bytes / 2

        val track = try {
            AudioTrack.Builder()
                .setAudioAttributes(mediaAttrs)
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setSampleRate(sampleRate)
                        .setChannelMask(channelMask)
                        .setEncoding(encoding)
                        .build()
                )
                .setTransferMode(AudioTrack.MODE_STATIC)
                .setBufferSizeInBytes(bytes)
                .build()
        } catch (_: Throwable) {
            return
        }

        val silence = ByteArray(bytes)
        try {
            track.write(silence, 0, silence.size)
            runCatching { track.setVolume(0f) } // absolute silence
            track.setNotificationMarkerPosition(framesForMarker) // frames, not bytes
            track.setPlaybackPositionUpdateListener(object :
                AudioTrack.OnPlaybackPositionUpdateListener {
                override fun onMarkerReached(t: AudioTrack?) {
                    runCatching { t?.stop() }
                    runCatching { t?.release() }
                }

                override fun onPeriodicNotification(t: AudioTrack?) {}
            })
            track.play()
        } catch (_: Throwable) {
            runCatching { track.release() }
        }
    }
}
