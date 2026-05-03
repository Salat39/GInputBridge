package com.salat.gbinder.entity

fun parseAppCarouselValueSegment(segment: String): Pair<String, Boolean> {
    val s = segment.trim()
    if ('+' !in s) return s to false
    val idx = s.indexOf('+')
    val pkg = s.substring(0, idx).trim()
    val flagPart = s.substring(idx + 1).trim()
    return pkg to (flagPart == "1")
}
