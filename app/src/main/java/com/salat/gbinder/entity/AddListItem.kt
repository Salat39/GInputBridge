package com.salat.gbinder.entity

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
sealed class AddListItem() {
    @Immutable
    data class App(
        val id: Int,
        val isSelected: Boolean,
        val showActivity: Boolean,
        val iconRef: DisplayIconRef,
        val customIcon: Uri?,
        val title: String,
        val packageName: String,
        val launchActivity: String?,
        val isFrozen: Boolean,
        val isSingleActivity: Boolean,
        val selectedCount: Int,
        val isSystem: Boolean = false
    ) : AddListItem()

    @Immutable
    data class Activity(
        val id: Int,
        val parentId: Int,
        val isSelected: Boolean,
        val isShow: Boolean,
        val title: String,
        val launchActivity: String,
        val packageName: String,
        val isSystem: Boolean = false
    ) : AddListItem()
}
