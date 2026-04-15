package com.salat.gbinder.coil

import coil.key.Keyer
import coil.request.Options
import com.salat.gbinder.entity.DisplayIconRef

class IconRefKeyer : Keyer<DisplayIconRef> {
    override fun key(data: DisplayIconRef, options: Options): String {
        return "pkg:${data.packageName}|res:${data.resId}|dpi:${data.densityDpi}|vc:${data.versionCode}"
    }
}
