@file:Suppress("DEPRECATION")

package com.salat.gbinder.features.launcher

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.DEFAULT_UI_SCALE
import com.salat.gbinder.R
import com.salat.gbinder.components.extractPackageName
import com.salat.gbinder.components.requireDisplayOverlay
import com.salat.gbinder.isLauncherServiceRunning
import com.salat.gbinder.statekeeper.domain.entity.LauncherActivitySignal
import com.salat.gbinder.statekeeper.domain.entity.LauncherOverlaySignal
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.stopLauncherOverlay
import com.salat.gbinder.ui.BaseButton
import com.salat.gbinder.ui.BaseDialog
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LauncherEntryActivity : ComponentActivity() {

    companion object {
        private const val BACK_HANDLER = true
        private const val HALF_SECOND_MS = 500L
        private const val FREEZE_SYSTEM_CONFIRM_DELAY_SECONDS = 12
    }

    @Inject
    lateinit var stateKeeper: StateKeeperRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT
        androidx.core.view.WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)

        // Overlay closing system when re-calling activity
        if (!isDelaySatisfied()) {
            finish()
            return
        }

        // Ignore opening if system in foreground
        if (stateKeeper.visibleAppState.value in OVERLAY_RESTRICTED_PKGS) {
            Timber.d("[LAUNCHER] detected restricted pkgs")

            // Kill overlay if exist
            if (isLauncherServiceRunning(this)) {
                stopLauncherOverlay(this)
            }

            // Finish current instance
            finish()

            // Resend toggle launcher
            stateKeeper.toggleLauncher()
            return
        }

        stateKeeper.setLauncherActivityEnabled(true)

        // Render dialog ui
        val uiScale = if (BuildConfig.DEBUG) {
            1f
        } else stateKeeper.uiScales.value?.second ?: DEFAULT_UI_SCALE
        setContent {
            AppTheme(darkTheme = true) {
                RenderOverlayDialogs(uiScale)
                RenderShortcutCatcher()
                RenderImagePicker(uiScale)
            }
        }

        if (requireDisplayOverlay()) {
            if (!stateKeeper.launcherOverlayEnabled.value) {
                stateKeeper.toggleLauncher() // TODO TEST
                // startLauncherOverlay(this)
            }

            if (BACK_HANDLER) {
                lifecycleScope.launch {
                    stateKeeper.launcherOverlayEnabled
                        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                        .filter { !it }
                        .collect { finish() }
                }

                onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        stateKeeper.sendLauncherActivitySignal(LauncherActivitySignal.OnBackPressed)
                    }
                })
            } else {
                finish()
            }
        } else {
            finish()
        }
    }

    private fun isDelaySatisfied(): Boolean {
        val last = stateKeeper.launcherActivityCloseTime.value
        if (last == 0L) return true
        val elapsed = SystemClock.elapsedRealtime() - last
        return elapsed >= HALF_SECOND_MS
    }

    override fun onPause() {
        super.onPause()
        stateKeeper.sendLauncherActivitySignal(LauncherActivitySignal.OnPause)
        stateKeeper.setLauncherActivityCloseTime(SystemClock.elapsedRealtime())
    }

    override fun onResume() {
        super.onResume()
        stateKeeper.sendLauncherActivitySignal(LauncherActivitySignal.OnResume)
    }

    /* private fun killOverlay() {
        if (isLauncherServiceRunning(this@LauncherEntryActivity)) {
            stopLauncherOverlay(this@LauncherEntryActivity)
        }
        finish()
    } */

    override fun finish() {
        stateKeeper.setLauncherActivityEnabled(false)
        super.finish()
        overridePendingTransition(0, 0)
    }

    @Composable
    private fun RenderOverlayDialogs(uiScale: Float) {
        var editGroupNameDialog by remember { mutableStateOf<Pair<Long, String>?>(null) }
        var freezeConfirmationDialog by remember { mutableStateOf<FreezeConfirmationState?>(null) }

        LaunchedEffect(Unit) {
            stateKeeper.launcherOverlaySignalFlow.collect { signal ->
                when (signal) {
                    is LauncherOverlaySignal.ChangeGroupName -> editGroupNameDialog =
                        signal.id to signal.title

                    is LauncherOverlaySignal.ConfirmFreezeApp -> freezeConfirmationDialog =
                        FreezeConfirmationState(signal.packageName, signal.isSystem)

                    else -> Unit
                }
            }
        }

        Box(
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .clickableNoRipple { finish() }
        ) {
            // Input group name
            editGroupNameDialog?.let { (id, title) ->
                InputMyAppNameDialog(
                    title = title,
                    uiScaleState = uiScale,
                    onNewGroup = { title ->
                        val action = LauncherActivitySignal.ApplyGroupDivider(id, title)
                        stateKeeper.sendLauncherActivitySignal(action)
                        editGroupNameDialog = null
                    },
                    onDismiss = {
                        stateKeeper.sendLauncherActivitySignal(LauncherActivitySignal.OnResume)
                        editGroupNameDialog = null
                    }
                )
            }

            freezeConfirmationDialog?.let { state ->
                FreezeConfirmationDialog(
                    state = state,
                    uiScale = uiScale,
                    onConfirm = { packageName ->
                        val action = LauncherActivitySignal.ApplyFreezeApp(packageName)
                        stateKeeper.sendLauncherActivitySignal(action)
                        freezeConfirmationDialog = null
                    },
                    onDismiss = {
                        stateKeeper.sendLauncherActivitySignal(LauncherActivitySignal.OnResume)
                        freezeConfirmationDialog = null
                    }
                )
            }
        }
    }

    @Composable
    private fun FreezeConfirmationDialog(
        state: FreezeConfirmationState,
        uiScale: Float,
        onConfirm: (String) -> Unit,
        onDismiss: () -> Unit
    ) {
        var remainingSeconds by remember(state) {
            mutableIntStateOf(if (state.isSystem) FREEZE_SYSTEM_CONFIRM_DELAY_SECONDS else 0)
        }

        LaunchedEffect(state) {
            while (remainingSeconds > 0) {
                delay(1000)
                remainingSeconds -= 1
            }
        }

        val canConfirm = remainingSeconds == 0
        val message = stringResource(
            if (state.isSystem) {
                R.string.confirm_freeze_system_app_message
            } else {
                R.string.confirm_freeze_app_message
            }
        )
        val confirmTitle = if (remainingSeconds > 0) {
            "${stringResource(R.string.yes)} ($remainingSeconds)"
        } else {
            stringResource(R.string.yes)
        }

        BaseDialog(
            uiScaleState = uiScale,
            maxWidth = 560,
            onDismiss = onDismiss
        ) {
            Column(modifier = Modifier.padding(top = 22.dp)) {
                Text(
                    text = message,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = if (state.isSystem) {
                        AppTheme.colors.warning
                    } else AppTheme.colors.contentPrimary,
                    style = AppTheme.typography.dialogListTitle
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(R.string.no),
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onDismiss() }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = AppTheme.colors.contentAccent,
                        style = AppTheme.typography.alertDialogButton
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = confirmTitle,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable(enabled = canConfirm) { onConfirm(state.packageName) }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = if (canConfirm) {
                            AppTheme.colors.deleteButton.copy(alpha = .9f)
                        } else {
                            AppTheme.colors.contentPrimary.copy(alpha = .45f)
                        },
                        style = AppTheme.typography.alertDialogButton
                    )
                }
            }
        }
    }

    @SuppressLint("DiscouragedApi")
    @Composable
    private fun RenderShortcutCatcher() {
        val context = LocalContext.current
        val pickShortcut = rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val extras = result.data?.extras

                val scIntent = extras
                    ?.getParcelable<Intent>(Intent.EXTRA_SHORTCUT_INTENT)

                val scName = extras
                    ?.getString(Intent.EXTRA_SHORTCUT_NAME)
                    ?: "Unnamed"

                val iconBitmap = extras
                    ?.getParcelable<Bitmap>(Intent.EXTRA_SHORTCUT_ICON)

                scIntent?.let { intent ->
                    runCatching {
                        // val subtitle = scIntent.extractPackageName(context) ?: "Shortcut"
                        val uri = intent.toUri(Intent.URI_INTENT_SCHEME)
                        val pkg = scIntent.extractPackageName(context).orEmpty()

                        val action = LauncherActivitySignal.CreateShortcut(
                            title = scName,
                            packageName = pkg,
                            intent = uri,
                            bitmap = iconBitmap
                        )
                        stateKeeper.sendLauncherActivitySignal(action)
                    }.onFailure { Timber.e(it) }
                }
            }
        }

        LaunchedEffect(Unit) {
            stateKeeper.launcherOverlaySignalFlow.collect { signal ->
                when (signal) {
                    LauncherOverlaySignal.CreateShortcut -> {
                        val intent = Intent(Intent.ACTION_CREATE_SHORTCUT)
                        pickShortcut.launch(intent)
                    }

                    else -> Unit
                }
            }
        }
    }

    @Suppress("AssignedValueIsNeverRead")
    @Composable
    private fun RenderImagePicker(uiScale: Float) {
        val context = LocalContext.current
        var target by remember { mutableStateOf(0L to "") }
        var changeImageDialog by remember { mutableStateOf(false) }

        val pickImage = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.OpenDocument(),
            onResult = { uri: Uri? ->
                if (uri != null) {
                    // Persist read/write if granted.
                    val flags =
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    runCatching { context.contentResolver.takePersistableUriPermission(uri, flags) }

                    val (id, packageName) = target
                    val action = LauncherActivitySignal.ApplyNewIcon(
                        id = id,
                        uri = uri,
                        packageName = packageName
                    )
                    stateKeeper.sendLauncherActivitySignal(action)
                }
            }
        )

        if (changeImageDialog) {
            BaseDialog(
                uiScaleState = uiScale,
                onDismiss = {
                    target = 0L to ""
                    changeImageDialog = false
                    stateKeeper.sendLauncherActivitySignal(LauncherActivitySignal.OnResume)
                }
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
                ) {
                    Text(
                        text = stringResource(R.string.change_icon),
                        color = AppTheme.colors.contentPrimary,
                        style = AppTheme.typography.dialogListTitle
                    )

                    BaseButton(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.change),
                        enable = true
                    ) {
                        runCatching { pickImage.launch(arrayOf("image/*")) }
                        changeImageDialog = false
                    }

                    BaseButton(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.clear),
                        backgroundColor = AppTheme.colors.surfaceMenu,
                        enable = true
                    ) {
                        val (id, packageName) = target
                        val action = LauncherActivitySignal.CancelIcon(
                            id = id,
                            packageName = packageName
                        )
                        stateKeeper.sendLauncherActivitySignal(action)
                        changeImageDialog = false
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            stateKeeper.launcherOverlaySignalFlow.collect { signal ->
                when (signal) {
                    is LauncherOverlaySignal.ChangeAppIconById -> {
                        target = signal.id to ""

                        if (signal.withClear) {
                            changeImageDialog = true
                        } else {
                            runCatching { pickImage.launch(arrayOf("image/*")) }
                        }
                    }

                    is LauncherOverlaySignal.ChangeAppIconByPackage -> {
                        target = 0L to signal.packageName

                        if (signal.withClear) {
                            changeImageDialog = true
                        } else {
                            runCatching { pickImage.launch(arrayOf("image/*")) }
                        }
                    }

                    else -> Unit
                }
            }
        }
    }
}
