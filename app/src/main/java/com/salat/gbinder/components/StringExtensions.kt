package com.salat.gbinder.components

import android.annotation.SuppressLint
import android.text.Html
import android.text.Spanned
import java.security.MessageDigest
import java.util.UUID

private const val LINE_SEPARATOR = "\n"
private const val HTML_LINE_SEPARATOR = "<br>"

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

internal fun String.spannedFromHtml(): Spanned {
    return fromHtml() as Spanned
}

@SuppressLint("ObsoleteSdkInt")
internal fun String.fromHtml(): CharSequence {
    // Keep original line breakers
    val htmlString = trim().replace(LINE_SEPARATOR, HTML_LINE_SEPARATOR)
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION") Html.fromHtml(htmlString)
    }
}
