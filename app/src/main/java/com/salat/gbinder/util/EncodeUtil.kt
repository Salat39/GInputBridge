package com.salat.gbinder.util

import java.nio.charset.StandardCharsets
import java.util.Base64

fun encodeBase64Jvm(text: String): String {
    return Base64.getEncoder().encodeToString(text.toByteArray(StandardCharsets.UTF_8))
}

fun decodeBase64Jvm(b64: String): String {
    val bytes = Base64.getDecoder().decode(b64)
    return String(bytes, StandardCharsets.UTF_8)
}

fun encodeBase64UrlJvm(text: String, withoutPadding: Boolean = false): String {
    val encoder =
        if (withoutPadding) Base64.getUrlEncoder().withoutPadding() else Base64.getUrlEncoder()
    return encoder.encodeToString(text.toByteArray(StandardCharsets.UTF_8))
}

fun decodeBase64UrlJvm(b64url: String): String {
    val bytes = Base64.getUrlDecoder().decode(b64url)
    return String(bytes, StandardCharsets.UTF_8)
}
