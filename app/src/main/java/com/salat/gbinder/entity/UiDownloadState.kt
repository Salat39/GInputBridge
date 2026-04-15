package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UiDownloadState {

    /** In-progress state with 0..100 percent */
    @Immutable
    data class InProgress(val percent: Int) : UiDownloadState

    /** Finished successfully and ready to use */
    @Immutable
    data class Success(val uri: String) : UiDownloadState

    /** Failed with human-readable message */
    @Immutable
    data class Error(val message: String) : UiDownloadState
}
