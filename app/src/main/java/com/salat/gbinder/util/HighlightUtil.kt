package com.salat.gbinder.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

fun createHighlightedText(inputText: String, searchText: String): AnnotatedString {
    val startIndexes = mutableListOf<Int>()

    var startIndex = inputText.indexOf(searchText, ignoreCase = true)
    while (startIndex != -1) {
        startIndexes.add(startIndex)
        startIndex =
            inputText.indexOf(searchText, startIndex + searchText.length, ignoreCase = true)
    }

    return buildAnnotatedString {
        append(inputText)

        // Apply span to each occurrence
        startIndexes.forEach { index ->
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3797d3)
                ),
                start = index,
                end = index + searchText.length
            )
        }
    }
}
