package com.salat.gbinder.components

import java.security.MessageDigest
import java.util.UUID

fun String.generateFileId(): String {
    val digest = MessageDigest.getInstance("MD5") // Or "SHA-256" if needed
    val hashBytes = digest.digest(this.toByteArray())
    return hashBytes.joinToString("") { "%02x".format(it) } //.take(16) // Truncate to 16 characters
}

fun String.generateUuid(): String {
    return UUID.nameUUIDFromBytes(this.toByteArray(Charsets.UTF_8)).toString()
}

fun String.onlyDigitsAndLeadingPlus(): String {
    val out = StringBuilder()
    var plusAdded = false
    for (ch in this) {
        when {
            ch.isDigit() -> out.append(ch)
            ch == '+' && out.isEmpty() && !plusAdded -> {
                out.append('+')
                plusAdded = true
            }

            else -> Unit
        }
    }
    return out.toString()
}
