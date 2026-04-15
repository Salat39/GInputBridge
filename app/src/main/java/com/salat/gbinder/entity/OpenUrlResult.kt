package com.salat.gbinder.entity

enum class OpenUrlResult {
    OPENED_IN_APP,      // started an external app via ACTION_VIEW
    SHARED,             // showed system share sheet with the link
    COPIED_TO_CLIPBOARD,// copied when no handlers found at all
    INVALID_URL         // couldn't even parse the URL
}
