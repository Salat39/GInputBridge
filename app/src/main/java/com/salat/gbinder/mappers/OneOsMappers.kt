package com.salat.gbinder.mappers

import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant

internal fun String.asAudioSource() = when (this) {
    "UNKNOWN" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_UNKNOWN
    "USB" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB
    "BT" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT
    "RADIO" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO
    "ONLINE" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE
    "OTHER" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_OTHER
    "YUNTING" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING
    "CPAA" -> MediaCenterConstant.AudioSource.AUDIO_SOURCE_CPAA
    else -> null
}

internal fun MediaCenterConstant.AudioSource.asString() = when (this) {
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_UNKNOWN -> "UNKNOWN"
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB -> "USB"
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT -> "BT"
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO -> "RADIO"
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE -> "ONLINE"
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_OTHER -> "OTHER"
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING -> "YUNTING"
    MediaCenterConstant.AudioSource.AUDIO_SOURCE_CPAA -> "CPAA"
}

internal fun String.asAppSource(): MediaCenterConstant.AppSource? = when (this) {
    "UNKNOWN" -> MediaCenterConstant.AppSource.UNKNOWN
    "WECARFLOW" -> MediaCenterConstant.AppSource.WECARFLOW
    "KUWO" -> MediaCenterConstant.AppSource.KUWO
    "KUGOU" -> MediaCenterConstant.AppSource.KUGOU
    "NETEASE_CLOUD" -> MediaCenterConstant.AppSource.NETEASE_CLOUD
    "SOHU" -> MediaCenterConstant.AppSource.SOHU
    "JINRI" -> MediaCenterConstant.AppSource.JINRI
    "QYS" -> MediaCenterConstant.AppSource.QYS
    "OTHER" -> MediaCenterConstant.AppSource.OTHER
    "FANDENG" -> MediaCenterConstant.AppSource.FANDENG
    "TENCENT_VIDEO" -> MediaCenterConstant.AppSource.TENCENT_VIDEO
    "XMLY" -> MediaCenterConstant.AppSource.XMLY
    "GC" -> MediaCenterConstant.AppSource.GC
    "HUOSHAN" -> MediaCenterConstant.AppSource.HUOSHAN
    "BILIBILI" -> MediaCenterConstant.AppSource.BILIBILI
    "THUNDER_VOICE" -> MediaCenterConstant.AppSource.THUNDER_VOICE
    "CMVIDEO_VOICE" -> MediaCenterConstant.AppSource.CMVIDEO_VOICE
    else -> null
}
