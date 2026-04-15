package com.salat.gbinder.mappers

import com.salat.gbinder.entity.DisplayIconRef
import com.salat.gbinder.entity.IconRef

fun IconRef.toDisplayIcon() = DisplayIconRef(
    packageName = packageName,
    resId = resId,
    densityDpi = densityDpi,
    versionCode = versionCode
)

fun DisplayIconRef.toDataIcon() = IconRef(
    packageName = packageName,
    resId = resId,
    densityDpi = densityDpi,
    versionCode = versionCode
)
