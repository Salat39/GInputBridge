package com.salat.gbinder

import com.salat.gbinder.entity.CarModel

object ModelHelper {

    fun readProductModel(): String {
        return try {
            val clazz = Class.forName("android.os.SystemProperties")
            val get = clazz.getMethod("get", String::class.java, String::class.java)
            (get.invoke(null, "ro.product.model", "") as String).trim()
        } catch (_: Exception) {
            ""
        }
    }

    fun detectCarModel(): CarModel? {
        val model = readProductModel()
        return when {
            model.startsWith("FS11", ignoreCase = true) -> CarModel.PREFACE
            model.startsWith("G636", ignoreCase = true) -> CarModel.ATLAS
            model.startsWith("G426", ignoreCase = true) -> CarModel.CITYRAY
            else -> null
        }
    }
}
