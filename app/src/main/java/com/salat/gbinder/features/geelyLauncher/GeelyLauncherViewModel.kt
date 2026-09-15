package com.salat.gbinder.features.geelyLauncher

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salat.gbinder.APP_ICON_QUALITY
import com.salat.gbinder.APP_ICON_ROUND
import com.salat.gbinder.R
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.features.geelyLauncher.entity.GLScreenState
import com.salat.gbinder.mappers.toDisplayIcon
import com.salat.gbinder.util.SystemAppsLightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GeelyLauncherViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val adbRepository: AdbRepository,
    private val systemApps: SystemAppsLightRepository
) : ViewModel() {

    private val _screenType = MutableStateFlow(GLScreenState.LOADING)
    val screenType = _screenType.asStateFlow()

    private val _availableApps = MutableStateFlow<List<GeelyLauncherApp>>(emptyList())
    val availableApps = _availableApps.asStateFlow()

    private val _launcherApps = MutableStateFlow<List<GeelyLauncherApp>>(emptyList())
    val launcherApps = _launcherApps.asStateFlow()

    private val _isApplying = MutableStateFlow(false)
    val isApplying = _isApplying.asStateFlow()

    private val _applyProgress = MutableStateFlow(0f)
    val applyProgress = _applyProgress.asStateFlow()

    private val _applyProgressAnimationDurationMs = MutableStateFlow(APPLY_PROGRESS_ANIMATION_MS)
    val applyProgressAnimationDurationMs = _applyProgressAnimationDurationMs.asStateFlow()

    private val _hasChanges = MutableStateFlow(false)
    val hasChanges = _hasChanges.asStateFlow()

    private val _toastMessage = MutableSharedFlow<Int>()
    val toastMessage = _toastMessage.asSharedFlow()

    private var initialLauncherPackages: List<String> = emptyList()
    private var loadingStarted = false

    fun initialCheck() = viewModelScope.launch(Dispatchers.IO) {
        setProviderState(isGeelyAppStoreProviderRegistered(context))
    }

    fun setProviderState(withAppStorage: Boolean) {
        if (!withAppStorage) {
            loadingStarted = false
            _screenType.value = GLScreenState.NEED_APP_STORAGE
            return
        }

        if (loadingStarted && _screenType.value != GLScreenState.NEED_APP_STORAGE) return

        loadingStarted = true
        _screenType.value = GLScreenState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val currentPackages = context.contentResolver.queryNativeLauncherPackages()
                val installedApps = systemApps
                    .getAllApps(APP_ICON_ROUND, true, APP_ICON_QUALITY)
                    .filter { !it.isSystem }
                    .distinctBy { it.packageName }
                    .map {
                        GeelyLauncherApp(
                            packageName = it.packageName,
                            appName = it.appName,
                            iconRef = it.iconRef.toDisplayIcon()
                        )
                    }
                //.sortedBy { it.appName.lowercase() }

                val installedByPackage = installedApps.associateBy { it.packageName }
                val currentApps = currentPackages.mapNotNull { installedByPackage[it] }

                // TODO init loading system apps
                withContext(Dispatchers.Main) {
                    _availableApps.value = installedApps
                    initialLauncherPackages = currentApps.map { it.packageName }
                    _launcherApps.value = currentApps
                    _hasChanges.value = false
                    _screenType.value = GLScreenState.BUILDER
                }
            }.onFailure { e ->
                Timber.e(e)
                withContext(Dispatchers.Main) {
                    loadingStarted = false
                    _screenType.value = GLScreenState.NEED_APP_STORAGE
                }
            }
        }
    }

    fun removeApp(packageName: String) {
        if (_isApplying.value) return
        _launcherApps.value = _launcherApps.value.filterNot { it.packageName == packageName }
        updateChangesState()
    }

    fun moveApp(fromIndex: Int, toIndex: Int) {
        if (_isApplying.value) return
        val source = _launcherApps.value
        if (fromIndex !in source.indices || toIndex !in source.indices || fromIndex == toIndex) return
        _launcherApps.value = source.toMutableList().apply {
            add(toIndex, removeAt(fromIndex))
        }
        updateChangesState()
    }

    fun setSelectedApps(packageNames: Set<String>) {
        if (_isApplying.value) return
        val byPackage = _availableApps.value.associateBy { it.packageName }
        val currentPackages = _launcherApps.value.map { it.packageName }
        val availablePackages = _availableApps.value.map { it.packageName }
        val orderedPackages = buildList {
            currentPackages.forEach { packageName ->
                if (packageName in packageNames && packageName !in this) add(packageName)
            }
            availablePackages.forEach { packageName ->
                if (packageName in packageNames && packageName !in this) add(packageName)
            }
        }
        _launcherApps.value = orderedPackages.mapNotNull { byPackage[it] }
        updateChangesState()
    }

    fun applyChanges() {
        if (_isApplying.value || !_hasChanges.value) return
        val snapshot = _launcherApps.value.distinctBy { it.packageName }
        val previousPackages = initialLauncherPackages
        _isApplying.value = true
        _applyProgress.value = 0f
        _applyProgressAnimationDurationMs.value = APPLY_PROGRESS_ANIMATION_MS
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val resolver = context.contentResolver
                val finalPackages = snapshot.map { it.packageName }.toSet()
                val storedPackages = resolver.queryNativeLauncherPackages().toSet()
                _applyProgress.value = 1f / APPLY_STEPS

                resolver.removeNativeLauncherApps(previousPackages.filter { it !in finalPackages })
                _applyProgress.value = 2f / APPLY_STEPS

                resolver.addNativeLauncherApps(
                    snapshot
                        .filter { it.packageName !in storedPackages }
                        .map { it.packageName to it.appName }
                )
                _applyProgress.value = 3f / APPLY_STEPS

                adbRepository.restartLauncher3()
                _applyProgressAnimationDurationMs.value = APPLY_FINISH_DELAY_MS.toInt()
                _applyProgress.value = 1f
                delay(APPLY_FINISH_DELAY_MS)
                withContext(Dispatchers.Main) {
                    initialLauncherPackages = snapshot.map { it.packageName }
                    _hasChanges.value = false
                    _toastMessage.emit(R.string.geely_launcher_apply_success)
                }
            }.onFailure { e ->
                Timber.e(e)
                withContext(Dispatchers.Main) {
                    _toastMessage.emit(R.string.geely_launcher_apply_error)
                }
            }
            withContext(Dispatchers.Main) {
                _isApplying.value = false
                _applyProgress.value = 0f
                _applyProgressAnimationDurationMs.value = APPLY_PROGRESS_ANIMATION_MS
            }
        }
    }

    fun restartLauncher() = viewModelScope.launch(Dispatchers.IO) {
        adbRepository.restartLauncher3()
        Timber.d("Restart launcher")
    }

    private fun updateChangesState() {
        _hasChanges.value =
            _launcherApps.value.map { it.packageName }.toSet() != initialLauncherPackages.toSet()
    }

    private companion object {
        const val APPLY_STEPS = 4
        const val APPLY_FINISH_DELAY_MS = 3500L
        const val APPLY_PROGRESS_ANIMATION_MS = 180
    }
}
