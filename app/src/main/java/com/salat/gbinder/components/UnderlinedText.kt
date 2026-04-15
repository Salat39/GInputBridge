package com.salat.gbinder.components

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withAnnotation
import timber.log.Timber

private const val UNDERLINE_TAG = "underline"
private const val IGNORE_ANNOTATION = "ignored"

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic),
                    start,
                    end
                )
            }

            is UnderlineSpan -> {
                addStringAnnotation(UNDERLINE_TAG, "#$UNDERLINE_TAG", start, end)
            }

            is URLSpan -> {
                addStringAnnotation(UNDERLINE_TAG, span.url.toString(), start, end)
            }

            is ForegroundColorSpan -> addStyle(
                SpanStyle(color = Color(span.foregroundColor)),
                start,
                end
            )

            else -> Timber.d("Unknown text span: ${span::class.java}")
        }
    }
}

@OptIn(ExperimentalTextApi::class)
fun AnnotatedString.Builder.underline(
    annotation: String = IGNORE_ANNOTATION,
    action: AnnotatedString.Builder.() -> Unit
): AnnotatedString.Builder {
    withAnnotation(UNDERLINE_TAG, annotation = annotation) { action() }
    return this
}

@OptIn(ExperimentalTextApi::class)
fun AnnotatedString.Builder.appendUnderline(
    annotation: String = IGNORE_ANNOTATION,
    text: String
): AnnotatedString.Builder {
    withAnnotation(UNDERLINE_TAG, annotation = annotation) { append(text) }
    return this
}

@Immutable
data class SegmentRenderBond(val left: Float, val right: Float)
