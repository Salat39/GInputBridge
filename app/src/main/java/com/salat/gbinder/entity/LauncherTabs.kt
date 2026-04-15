package com.salat.gbinder.entity

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.salat.gbinder.R

@Immutable
internal enum class LauncherTabs(@StringRes val title: Int) {
    MyApss(0),
    AllApps(R.string.all)
}
