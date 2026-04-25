package com.salat.gbinder.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.salat.gbinder.APP_ICON_QUALITY
import com.salat.gbinder.APP_ICON_ROUND
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.GlobalState
import com.salat.gbinder.R
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.components.ObserveAsEvents
import com.salat.gbinder.components.extractPackageName
import com.salat.gbinder.components.inMainToast
import com.salat.gbinder.components.onlyDigitsAndLeadingPlus
import com.salat.gbinder.components.requireDisplayOverlay
import com.salat.gbinder.datastore.KeyBindStorageRepository
import com.salat.gbinder.entity.DISPLAY_DRIVE_MODES
import com.salat.gbinder.entity.DISPLAY_LAMP_MODES
import com.salat.gbinder.entity.DeviceAppInfo
import com.salat.gbinder.entity.DisplayDriveMode
import com.salat.gbinder.entity.DraggableDMItem
import com.salat.gbinder.entity.DraggableLampItem
import com.salat.gbinder.entity.KeyBindAction
import com.salat.gbinder.entity.KeyBindConfig
import com.salat.gbinder.entity.KeyBindPattern
import com.salat.gbinder.mappers.keyCodeMap
import com.salat.gbinder.mappers.toAllDisplay
import com.salat.gbinder.ui.reordable.ReorderableItem
import com.salat.gbinder.ui.reordable.rememberReorderableLazyListState
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.SystemAppsLightRepository
import com.salat.gbinder.util.rememberIsLandscape
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private enum class KeyBindingDialogStep {
    SET_KEY_BIND,
    SET_ACTION,
    SET_APP,
    SET_LINK,
    SET_CALL_PHONE_NUMBER,
    DRIVE_MODE_WARNING,
    SET_DRIVE_MODE_CHOOSE_METHOD,
    SET_TOGGLE_DRIVE_MODE,
    SET_CAROUSEL_DRIVE_MODE,
    SET_CAROUSEL_CAR_LAMP,
}

private enum class KeyBindingDialogActions {
    APP_LAUNCH,
    LINK_LAUNCH,
    APP_LAUNCHER,
    DRIVE_MODE_CHOOSE,
    PHONE_CALL,
    CAMERAS_360,
    CAR_LAMP,
    TASK_MANAGER,
    ANDROID_BACK,
    ANDROID_HOME,
    NAVIGATE_TO_PAST_APP
}

private enum class DriveModeAction {
    SWITCHING,
    CAROUSEL
}

private data class PickedKeyBind(
    val title: String,
    val bind: KeyBindPattern,
    val keyTitles: Map<Int, String>
)

private data class PickedLink(
    val intentUri: String,
    val icon: Any?,
    val title: String,
    val subtitle: String
)

@SuppressLint("DiscouragedApi")
@Suppress("DEPRECATION")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeyBindingDialog(
    uiScaleState: Float? = null,
    systemApps: SystemAppsLightRepository,
    keyBindStorage: KeyBindStorageRepository,
    onDismiss: () -> Unit = {}
) = BaseDialog(uiScaleState = uiScaleState, onDismiss = onDismiss) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var step: KeyBindingDialogStep by remember { mutableStateOf(KeyBindingDialogStep.SET_KEY_BIND) }
    var bind: PickedKeyBind? by remember { mutableStateOf(null) }
    val actions = remember {
        listOf(
            KeyBindingDialogActions.APP_LAUNCH,
            KeyBindingDialogActions.LINK_LAUNCH,
            KeyBindingDialogActions.APP_LAUNCHER,
            KeyBindingDialogActions.DRIVE_MODE_CHOOSE,
            KeyBindingDialogActions.CAR_LAMP,
            KeyBindingDialogActions.PHONE_CALL,
            KeyBindingDialogActions.CAMERAS_360,
            // KeyBindingDialogActions.TASK_MANAGER,
            KeyBindingDialogActions.ANDROID_BACK,
            KeyBindingDialogActions.ANDROID_HOME,
            KeyBindingDialogActions.NAVIGATE_TO_PAST_APP,
        )
    }
    val dmActions = remember {
        listOf(
            DriveModeAction.SWITCHING,
            DriveModeAction.CAROUSEL
        )
    }
    var apps: List<DeviceAppInfo>? by remember { mutableStateOf(null) }
    var link: PickedLink? by remember { mutableStateOf(null) }
    var dmToggleSelected by remember { mutableStateOf<DisplayDriveMode?>(null) }
    var carouselDriveModes by remember { mutableStateOf<List<DraggableDMItem>>(emptyList()) }
    var carouselLightModes by remember { mutableStateOf<List<DraggableLampItem>>(emptyList()) }
    var numberValue: TextFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    val pickShortcut = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val extras = result.data?.extras

            val scIntent = extras
                ?.getParcelable<Intent>(Intent.EXTRA_SHORTCUT_INTENT)

            val scName = extras
                ?.getString(Intent.EXTRA_SHORTCUT_NAME)
                ?: "Unnamed"

            val iconBitmap = extras
                ?.getParcelable<Bitmap>(Intent.EXTRA_SHORTCUT_ICON)
            val iconRes = extras
                ?.getParcelable<Intent.ShortcutIconResource>(Intent.EXTRA_SHORTCUT_ICON_RESOURCE)
            val iconModel: Any? = when {
                iconBitmap != null -> iconBitmap

                iconRes != null -> {
                    val pkg = iconRes.packageName
                    val name = iconRes.resourceName
                    val resId = context.packageManager
                        .getResourcesForApplication(pkg)
                        .getIdentifier(name, null, null)
                    "android.resource://$pkg/$resId"
                }

                else -> null
            }

            scIntent?.let { intent ->
                runCatching {
                    intent.putExtra("gib_name", scName)
                    val subtitle = scIntent.extractPackageName(context) ?: "Shortcut"
                    intent.putExtra("gib_package", subtitle)
                    val uri = intent.toUri(Intent.URI_INTENT_SCHEME)

                    link = PickedLink(
                        intentUri = uri,
                        icon = iconModel,
                        title = scName,
                        subtitle = subtitle
                    )

                    step = KeyBindingDialogStep.SET_LINK
                }.onFailure { Timber.e(it) }
            }
        }
    }

    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            val installedApps = systemApps.getAllApps(APP_ICON_ROUND, false, APP_ICON_QUALITY)
            apps = installedApps.toAllDisplay()
        }
        // Debug test bind set
        if (BuildConfig.DEBUG) {
            bind = PickedKeyBind(
                title = "test",
                bind = KeyBindPattern.ShortClick(1456),
                keyTitles = mapOf(1456 to "Any key")
            )
        }
    }

    Column(modifier = Modifier.padding(top = 22.dp)) {
        Text(
            text = when (step) {
                KeyBindingDialogStep.SET_KEY_BIND -> stringResource(R.string.kbd_title_keys)
                KeyBindingDialogStep.SET_ACTION -> stringResource(R.string.kbd_title_action)
                KeyBindingDialogStep.SET_APP -> stringResource(R.string.kbd_title_app)
                KeyBindingDialogStep.SET_LINK -> stringResource(R.string.selected_shortcut)
                KeyBindingDialogStep.DRIVE_MODE_WARNING -> stringResource(R.string.attention)
                KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> stringResource(R.string.driving_mode_switch_type)
                KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> stringResource(R.string.switching)
                KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> stringResource(R.string.carousel)
                KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> stringResource(R.string.call)
                KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> stringResource(R.string.headlight_mode)
            },
            modifier = Modifier.padding(horizontal = 24.dp),
            color = AppTheme.colors.contentPrimary,
            style = AppTheme.typography.dialogTitle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        if (step !in listOf(
                KeyBindingDialogStep.DRIVE_MODE_WARNING,
                KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD
            )
        ) {
            Spacer(Modifier.height(5.dp))

            Text(
                text = when (step) {
                    KeyBindingDialogStep.SET_KEY_BIND -> stringResource(R.string.kbd_desc_bind_keys)
                    KeyBindingDialogStep.SET_ACTION -> stringResource(R.string.kbd_desc_select_action)
                    KeyBindingDialogStep.SET_APP -> stringResource(R.string.kbd_desc_select_app)
                    KeyBindingDialogStep.SET_LINK -> stringResource(R.string.selected_shortcut_desc)
                    KeyBindingDialogStep.DRIVE_MODE_WARNING -> ""
                    KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> ""
                    KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> stringResource(R.string.switching_desc)
                    KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> stringResource(R.string.drag_modes_to_switch)
                    KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> stringResource(R.string.enter_phone_number)
                    KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> stringResource(R.string.drag_modes_to_switch)
                },
                modifier = Modifier.padding(horizontal = 23.dp),
                color = AppTheme.colors.contentPrimary.copy(.4f),
                style = AppTheme.typography.dialogSubtitle
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(.1f))
        )

        when (step) {
            KeyBindingDialogStep.SET_KEY_BIND -> {

                // Apply key interceptor
                DisposableEffect(true) {
                    GlobalState.keyBindingMode.tryEmit(true)
                    onDispose {
                        GlobalState.keyBindingMode.tryEmit(false)
                    }
                }

                // Intercept key
                ObserveAsEvents(GlobalState.keyBindingFlow) { keyBind ->
                    val decorTitle = when (keyBind) {
                        is KeyBindPattern.DoubleClick -> context.getString(R.string.kbd_pattern_double)
                        is KeyBindPattern.LongPress -> context.getString(R.string.kbd_pattern_long)
                        is KeyBindPattern.MultiLong -> context.getString(R.string.kbd_pattern_multi)
                        is KeyBindPattern.ShortClick -> context.getString(R.string.kbd_pattern_short)
                    }

                    val decorItems = mutableMapOf<Int, String>()
                    when (keyBind) {
                        is KeyBindPattern.DoubleClick -> {
                            decorItems[keyBind.keyCode] =
                                keyCodeMap.getOrDefault(keyBind.keyCode, "Unknown")
                        }

                        is KeyBindPattern.LongPress -> {
                            decorItems[keyBind.keyCode] =
                                keyCodeMap.getOrDefault(keyBind.keyCode, "Unknown")
                        }

                        is KeyBindPattern.MultiLong -> {
                            keyBind.keyCodes.forEach { code ->
                                decorItems[code] = keyCodeMap.getOrDefault(code, "Unknown")
                            }
                        }

                        is KeyBindPattern.ShortClick -> {
                            decorItems[keyBind.keyCode] =
                                keyCodeMap.getOrDefault(keyBind.keyCode, "Unknown")
                        }
                    }

                    bind = PickedKeyBind(
                        title = decorTitle,
                        bind = keyBind,
                        keyTitles = decorItems.toMap()
                    )
                }

                val isLandscape = rememberIsLandscape()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, false)
                        .verticalScroll(rememberScrollState())
                        .then(
                            if(isLandscape) Modifier else {
                                Modifier.heightIn(min = 180.dp)
                            }
                        )
                        .padding(vertical = 26.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    bind?.let { b ->
                        Text(
                            text = b.title,
                            modifier = Modifier.padding(horizontal = 23.dp),
                            color = AppTheme.colors.contentPrimary.copy(.9f),
                            style = AppTheme.typography.dialogTitle
                        )

                        Spacer(Modifier.height(24.dp))

                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                b.keyTitles.forEach { (code, title) ->
                                    BaseButton(
                                        title = title,
                                        backgroundColor = AppTheme.colors.surfaceMenu,
                                        enable = false
                                    ) { }

                                    if (b.keyTitles.keys.last() != code) {
                                        Text(
                                            text = "+",
                                            modifier = Modifier.padding(horizontal = 23.dp),
                                            color = AppTheme.colors.contentPrimary,
                                            style = AppTheme.typography.cardFormatTitle
                                        )
                                    }

                                }
                            }
                        }
                    } ?: run {
                        Text(
                            text = stringResource(R.string.press_buttons),
                            modifier = Modifier.padding(horizontal = 23.dp),
                            color = AppTheme.colors.contentPrimary.copy(.4f),
                            style = AppTheme.typography.dialogSubtitle
                        )
                    }
                }
            }

            KeyBindingDialogStep.SET_ACTION -> Column(
                modifier = Modifier
                    .weight(1f, false)
                    .verticalScroll(rememberScrollState())
            ) {

                actions.forEach { action ->

                    if (action == actions.first()) {
                        Spacer(Modifier.height(10.dp))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(AppTheme.colors.surfaceMenu)
                            .clickable {
                                when (action) {
                                    KeyBindingDialogActions.APP_LAUNCH -> {
                                        step = KeyBindingDialogStep.SET_APP
                                    }

                                    KeyBindingDialogActions.LINK_LAUNCH -> runCatching {
                                        val intent = Intent(Intent.ACTION_CREATE_SHORTCUT)
                                        pickShortcut.launch(intent)
                                    }

                                    KeyBindingDialogActions.APP_LAUNCHER -> scope.launch(Dispatchers.IO) {
                                        val name = bind?.bind
                                            ?.let { keyBindStorage.getBindName(it) }
                                            ?: ""

                                        keyBindStorage.saveBinds(
                                            name, KeyBindConfig(
                                                action = KeyBindAction.APP_LAUNCHER,
                                                value = ""
                                            )
                                        )
                                        onDismiss()
                                    }

                                    KeyBindingDialogActions.DRIVE_MODE_CHOOSE -> {
                                        step = KeyBindingDialogStep.DRIVE_MODE_WARNING
                                    }

                                    KeyBindingDialogActions.PHONE_CALL -> {
                                        step = KeyBindingDialogStep.SET_CALL_PHONE_NUMBER
                                    }

                                    KeyBindingDialogActions.CAMERAS_360 -> scope.launch(Dispatchers.IO) {
                                        val name = bind?.bind
                                            ?.let { keyBindStorage.getBindName(it) }
                                            ?: ""

                                        keyBindStorage.saveBinds(
                                            name, KeyBindConfig(
                                                action = KeyBindAction.CAMERAS_360,
                                                value = ""
                                            )
                                        )
                                        onDismiss()
                                    }

                                    KeyBindingDialogActions.TASK_MANAGER -> scope.launch(Dispatchers.IO) {
                                        val name = bind?.bind
                                            ?.let { keyBindStorage.getBindName(it) }
                                            ?: ""

                                        keyBindStorage.saveBinds(
                                            name, KeyBindConfig(
                                                action = KeyBindAction.TASK_MANAGER,
                                                value = ""
                                            )
                                        )
                                        onDismiss()
                                    }

                                    KeyBindingDialogActions.ANDROID_BACK -> scope.launch(Dispatchers.IO) {
                                        val name = bind?.bind
                                            ?.let { keyBindStorage.getBindName(it) }
                                            ?: ""

                                        keyBindStorage.saveBinds(
                                            name, KeyBindConfig(
                                                action = KeyBindAction.ANDROID_BACK,
                                                value = ""
                                            )
                                        )
                                        onDismiss()
                                    }

                                    KeyBindingDialogActions.ANDROID_HOME -> scope.launch(Dispatchers.IO) {
                                        val name = bind?.bind
                                            ?.let { keyBindStorage.getBindName(it) }
                                            ?: ""

                                        keyBindStorage.saveBinds(
                                            name, KeyBindConfig(
                                                action = KeyBindAction.ANDROID_HOME,
                                                value = ""
                                            )
                                        )
                                        onDismiss()
                                    }

                                    KeyBindingDialogActions.NAVIGATE_TO_PAST_APP -> scope.launch(Dispatchers.IO) {
                                        val name = bind?.bind
                                            ?.let { keyBindStorage.getBindName(it) }
                                            ?: ""

                                        keyBindStorage.saveBinds(
                                            name, KeyBindConfig(
                                                action = KeyBindAction.NAVIGATE_TO_PAST_APP,
                                                value = ""
                                            )
                                        )
                                        onDismiss()
                                    }

                                    KeyBindingDialogActions.CAR_LAMP -> {
                                        if (context.requireDisplayOverlay()) {
                                            step = KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP
                                        }
                                    }
                                }
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 23.dp),
                                text = when (action) {
                                    KeyBindingDialogActions.APP_LAUNCH -> stringResource(R.string.kbd_action_launch_title)

                                    KeyBindingDialogActions.LINK_LAUNCH -> stringResource(R.string.launch_shortcut)

                                    KeyBindingDialogActions.APP_LAUNCHER -> stringResource(R.string.launcher_name)

                                    KeyBindingDialogActions.DRIVE_MODE_CHOOSE -> stringResource(R.string.driving_mode_change)

                                    KeyBindingDialogActions.PHONE_CALL -> stringResource(R.string.call)

                                    KeyBindingDialogActions.CAMERAS_360 -> stringResource(R.string.circle_cameras)

                                    KeyBindingDialogActions.TASK_MANAGER -> "[ADB] ${stringResource(R.string.recents)}"

                                    KeyBindingDialogActions.ANDROID_BACK -> stringResource(R.string.back)

                                    KeyBindingDialogActions.ANDROID_HOME -> stringResource(R.string.home)

                                    KeyBindingDialogActions.NAVIGATE_TO_PAST_APP -> stringResource(R.string.return_to_previous_app)

                                    KeyBindingDialogActions.CAR_LAMP -> stringResource(R.string.headlight_mode)
                                },
                                style = AppTheme.typography.screenTitle,
                                color = AppTheme.colors.contentPrimary
                            )

                            Spacer(Modifier.height(5.dp))

                            Text(
                                text = when (action) {
                                    KeyBindingDialogActions.APP_LAUNCH -> stringResource(R.string.kbd_action_launch_desc)

                                    KeyBindingDialogActions.LINK_LAUNCH -> stringResource(R.string.launch_shortcut_desc)

                                    KeyBindingDialogActions.APP_LAUNCHER -> stringResource(R.string.app_launcher_toggle_desc)

                                    KeyBindingDialogActions.DRIVE_MODE_CHOOSE -> stringResource(R.string.driving_mode_switch)

                                    KeyBindingDialogActions.PHONE_CALL -> stringResource(R.string.call_number_desc)

                                    KeyBindingDialogActions.CAMERAS_360 -> stringResource(R.string.circle_cameras_desc)

                                    KeyBindingDialogActions.TASK_MANAGER -> stringResource(R.string.recents_action_description)

                                    KeyBindingDialogActions.ANDROID_BACK -> stringResource(R.string.back_action_simulation)

                                    KeyBindingDialogActions.ANDROID_HOME -> stringResource(R.string.home_action_simulation)

                                    KeyBindingDialogActions.NAVIGATE_TO_PAST_APP -> stringResource(R.string.return_to_previous_app_desc)

                                    KeyBindingDialogActions.CAR_LAMP -> stringResource(R.string.headlight_mode_desc)
                                },
                                modifier = Modifier.padding(horizontal = 23.dp),
                                color = AppTheme.colors.contentPrimary.copy(.4f),
                                style = AppTheme.typography.dialogSubtitle
                            )
                        }
                        Spacer(Modifier.width(20.dp))
                    }

                    if (action == actions.last()) {
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }

            KeyBindingDialogStep.SET_APP -> if (apps == null || apps?.isEmpty() == true) {
                RenderScan()
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    item(key = -1) {
                        Spacer(
                            Modifier
                                .height(.8.dp)
                        )
                    }
                    itemsIndexed(
                        items = apps as List<DeviceAppInfo>,
                        key = { index, _ -> index }
                    ) { _, item ->

                        fun selectSelect() {
                            apps = (apps as List<DeviceAppInfo>).map {
                                if (item.packageName == it.packageName) {
                                    it.copy(isSelected = true)
                                } else it.copy(isSelected = false)
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        withContext(Dispatchers.Default) {
                                            selectSelect()
                                        }
                                    }
                                }
                                .padding(vertical = 8.dp)
                                .padding(end = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(16.dp))

                            item.icon.let { icon ->
                                DrawableImage(
                                    icon = icon,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                )
                                Spacer(Modifier.width(10.dp))
                            }

                            Column {
                                Text(
                                    text = item.appName,
                                    style = AppTheme.typography.dialogListTitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary
                                )
                                Text(
                                    text = item.packageName,
                                    style = AppTheme.typography.dialogSubtitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary.copy(.5f)
                                )
                            }

                            Spacer(Modifier.weight(1f))

                            RadioButton(
                                selected = (item.isSelected),
                                onClick = {
                                    scope.launch {
                                        withContext(Dispatchers.Default) {
                                            selectSelect()
                                        }
                                    }
                                },
                                colors = RadioButtonColors(
                                    selectedColor = AppTheme.colors.contentAccent.copy(.8f),
                                    unselectedColor = AppTheme.colors.contentPrimary.copy(
                                        .3f
                                    ),
                                    disabledSelectedColor = AppTheme.colors.contentPrimary.copy(
                                        .3f
                                    ),
                                    disabledUnselectedColor = AppTheme.colors.contentPrimary.copy(
                                        .3f
                                    )
                                )
                            )

                            Spacer(Modifier.width(12.dp))
                        }
                    }
                }
            }

            KeyBindingDialogStep.SET_LINK -> link?.let { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.width(24.dp))

                    item.icon?.let { icon ->
                        AsyncImage(
                            model = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(46.dp)
                                .clip(RoundedCornerShape(6.dp))
                        )
                        Spacer(Modifier.width(16.dp))
                    }

                    Column {
                        Text(
                            text = item.title,
                            style = AppTheme.typography.dialogListTitle,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = AppTheme.colors.contentPrimary
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = item.subtitle,
                            style = AppTheme.typography.dialogSubtitle,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = AppTheme.colors.contentPrimary.copy(.5f)
                        )
                    }
                    Spacer(Modifier.width(24.dp))
                }
            }

            KeyBindingDialogStep.DRIVE_MODE_WARNING -> Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                text = stringResource(R.string.custom_driving_modes_warning),
                style = AppTheme.typography.dialogListTitle,
                color = AppTheme.colors.contentPrimary
            )

            KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> dmActions.forEach { dmAction ->
                if (dmAction == dmActions.first()) {
                    Spacer(Modifier.height(10.dp))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppTheme.colors.surfaceMenu)
                        .clickable {
                            when (dmAction) {
                                DriveModeAction.SWITCHING -> step =
                                    KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE

                                DriveModeAction.CAROUSEL -> step =
                                    KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE
                            }
                        }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 23.dp),
                            text = when (dmAction) {
                                DriveModeAction.SWITCHING -> stringResource(R.string.switching)

                                DriveModeAction.CAROUSEL -> stringResource(R.string.carousel)
                            },
                            style = AppTheme.typography.screenTitle,
                            color = AppTheme.colors.contentPrimary
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = when (dmAction) {
                                DriveModeAction.SWITCHING -> stringResource(R.string.switching_desc)

                                DriveModeAction.CAROUSEL -> stringResource(R.string.carousel_desc)
                            },
                            modifier = Modifier.padding(horizontal = 23.dp),
                            color = AppTheme.colors.contentPrimary.copy(.4f),
                            style = AppTheme.typography.dialogSubtitle
                        )
                    }
                    Spacer(Modifier.width(20.dp))
                }

                if (dmAction == dmActions.last()) {
                    Spacer(Modifier.height(10.dp))
                }
            }

            KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> {
                val driveModes by remember { mutableStateOf(DISPLAY_DRIVE_MODES) }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    item(key = -1) {
                        Spacer(
                            Modifier
                                .height(.8.dp)
                        )
                    }
                    itemsIndexed(
                        items = driveModes,
                        key = { _, item -> item.id }
                    ) { _, item ->

                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { dmToggleSelected = item }
                                    .padding(vertical = 12.dp)
                                    .padding(end = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(Modifier.width(24.dp))

                                Column(Modifier.weight(1f)) {
                                    Text(
                                        text = item.displayName,
                                        style = AppTheme.typography.cardTitle,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        color = AppTheme.colors.contentPrimary
                                    )

                                    Spacer(Modifier.height(4.dp))

                                    Text(
                                        text = stringResource(item.description),
                                        style = AppTheme.typography.idTitle,
                                        color = AppTheme.colors.contentPrimary.copy(.5f)
                                    )
                                }

                                Spacer(Modifier.width(16.dp))

                                RadioButton(
                                    selected = (item == dmToggleSelected),
                                    onClick = { dmToggleSelected = item },
                                    colors = RadioButtonColors(
                                        selectedColor = AppTheme.colors.contentAccent.copy(.8f),
                                        unselectedColor = AppTheme.colors.contentPrimary.copy(
                                            .3f
                                        ),
                                        disabledSelectedColor = AppTheme.colors.contentPrimary.copy(
                                            .3f
                                        ),
                                        disabledUnselectedColor = AppTheme.colors.contentPrimary.copy(
                                            .3f
                                        )
                                    )
                                )

                                Spacer(Modifier.width(12.dp))
                            }

                            if (item.id == CarPropertyValue.DRIVE_MODE_SELECTION_ECO) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp)
                                        .background(AppTheme.colors.surfaceMenu)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(top = 16.dp, bottom = 12.dp)
                                            .padding(horizontal = 24.dp),
                                        text = stringResource(R.string.additional),
                                        style = AppTheme.typography.sourceType.copy(fontSize = 11.sp),
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        color = AppTheme.colors.contentPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }

            KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> {

                LaunchedEffect(Unit) {
                    val dmList = DISPLAY_DRIVE_MODES.map {
                        DraggableDMItem.DriveMode(
                            index = 0,
                            item = it,
                            showPos = false
                        )
                    }
                    carouselDriveModes = listOf(DraggableDMItem.Divider) + dmList
                }

                val lazyListState = rememberLazyListState()
                val reorderableLazyListState =
                    rememberReorderableLazyListState(lazyListState) { from, to ->
                        val fromIdx = from.index
                        val toIdx = to.index

                        if (fromIdx !in carouselDriveModes.indices || toIdx !in carouselDriveModes.indices || fromIdx == toIdx) return@rememberReorderableLazyListState

                        carouselDriveModes = carouselDriveModes.toMutableList().apply {
                            val moved = removeAt(fromIdx)
                            val insertAt = if (toIdx > fromIdx) toIdx else toIdx
                            add(insertAt, moved)
                        }.toList()
                    }

                var dividerIndex by remember { mutableIntStateOf(0) }
                LaunchedEffect(dividerIndex) {
                    carouselDriveModes = carouselDriveModes.mapIndexed { i, item ->
                        if (item is DraggableDMItem.DriveMode) {
                            if (i < dividerIndex) {
                                item.copy(showPos = true)
                            } else {
                                item.copy(showPos = false)
                            }
                        } else item
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = lazyListState
                ) {
                    itemsIndexed(
                        items = carouselDriveModes,
                        key = { _, item ->
                            when (item) {
                                DraggableDMItem.Divider -> -1
                                is DraggableDMItem.DriveMode -> item.item.id
                            }
                        }
                    ) { index, item ->

                        when (item) {
                            DraggableDMItem.Divider -> {
                                ReorderableItem(
                                    state = reorderableLazyListState,
                                    key = -1
                                ) { isDragging ->
                                    LaunchedEffect(index) {
                                        dividerIndex = index
                                    }

                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .background(AppTheme.colors.surfaceMenu), // alt AppTheme.colors.surfaceSettingsLayer1
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Spacer(Modifier.width(24.dp))

                                        Icon(
                                            imageVector = Icons.Filled.KeyboardArrowUp,
                                            contentDescription = null,
                                            tint = AppTheme.colors.contentPrimary,
                                            modifier = Modifier
                                                .size(26.dp)
                                                .offset(y = 1.dp)
                                        )

                                        Spacer(Modifier.width(6.dp))

                                        Text(
                                            modifier = Modifier
                                                .padding(end = 24.dp)
                                                .padding(vertical = 16.dp),
                                            text = stringResource(if (index == 0) R.string.drag_up else R.string.active_modes),
                                            style = AppTheme.typography.sourceType.copy(fontSize = 11.sp),
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            color = AppTheme.colors.contentPrimary
                                        )
                                    }
                                }
                            }

                            is DraggableDMItem.DriveMode -> ReorderableItem(
                                state = reorderableLazyListState,
                                key = item.item.id
                            ) { isDragging ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(AppTheme.colors.surfaceBackground)
                                        .padding(vertical = 12.dp)
                                        .padding(end = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(Modifier.width(24.dp))

                                    Column(Modifier.weight(1f)) {
                                        Text(
                                            text = if (item.showPos) {
                                                "${(index + 1)}. ${item.item.displayName}"
                                            } else item.item.displayName,
                                            style = AppTheme.typography.cardTitle,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            color = AppTheme.colors.contentPrimary
                                        )

                                        Spacer(Modifier.height(4.dp))

                                        Text(
                                            text = stringResource(item.item.description),
                                            style = AppTheme.typography.idTitle,
                                            color = AppTheme.colors.contentPrimary.copy(.5f)
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .draggableHandle()
                                            .padding(vertical = 14.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Spacer(Modifier.width(36.dp))

                                        Icon(
                                            painter = painterResource(R.drawable.ic_drag_handle),
                                            contentDescription = null,
                                            tint = AppTheme.colors.contentPrimary.copy(.5f),
                                            modifier = Modifier
                                                .size(36.dp)
                                        )

                                        Spacer(Modifier.width(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }

            KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> {
                val fieldTypography = AppTheme.typography.stubTitle
                val keyboardController = LocalSoftwareKeyboardController.current
                val border = RoundedCornerShape(12.dp)

                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 26.dp)
                ) {

                    BasicTextField(
                        value = numberValue,
                        onValueChange = { numberValue = it },
                        cursorBrush = SolidColor(AppTheme.colors.contentAccent),
                        textStyle = fieldTypography.copy(color = AppTheme.colors.contentPrimary),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 20.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (numberValue.text.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.enter_number),
                                        style = fieldTypography.copy(
                                            color = AppTheme.colors.contentPrimary.copy(.4f)
                                        )
                                    )
                                }
                                innerTextField()
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Phone,
                            autoCorrectEnabled = false
                        ),
                        keyboardActions = KeyboardActions(onSearch = {
                            defaultKeyboardAction(ImeAction.Done)
                            keyboardController?.hide()
                        }),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(border)
                            .border(
                                shape = border,
                                width = 2.dp,
                                color = AppTheme.colors.surfaceMenu
                            )
                            .background(AppTheme.colors.surfaceSettingsLayer1.copy(.3f)),
                    )
                }
            }

            KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> {
                LaunchedEffect(Unit) {
                    val dmList = DISPLAY_LAMP_MODES.map {
                        DraggableLampItem.LampMode(
                            index = 0,
                            item = it,
                            showPos = false
                        )
                    }
                    carouselLightModes = listOf(DraggableLampItem.Divider) + dmList
                }

                val lazyListState = rememberLazyListState()
                val reorderableLazyListState =
                    rememberReorderableLazyListState(lazyListState) { from, to ->
                        val fromIdx = from.index
                        val toIdx = to.index

                        if (fromIdx !in carouselLightModes.indices || toIdx !in carouselLightModes.indices || fromIdx == toIdx) return@rememberReorderableLazyListState

                        carouselLightModes = carouselLightModes.toMutableList().apply {
                            val moved = removeAt(fromIdx)
                            val insertAt = if (toIdx > fromIdx) toIdx else toIdx
                            add(insertAt, moved)
                        }.toList()
                    }

                var dividerIndex by remember { mutableIntStateOf(0) }
                LaunchedEffect(dividerIndex) {
                    carouselLightModes = carouselLightModes.mapIndexed { i, item ->
                        if (item is DraggableLampItem.LampMode) {
                            if (i < dividerIndex) {
                                item.copy(showPos = true)
                            } else {
                                item.copy(showPos = false)
                            }
                        } else item
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = lazyListState
                ) {
                    itemsIndexed(
                        items = carouselLightModes,
                        key = { _, item ->
                            when (item) {
                                DraggableLampItem.Divider -> -1
                                is DraggableLampItem.LampMode -> item.item.id
                            }
                        }
                    ) { index, item ->

                        when (item) {
                            DraggableLampItem.Divider -> {
                                ReorderableItem(
                                    state = reorderableLazyListState,
                                    key = -1
                                ) { isDragging ->
                                    LaunchedEffect(index) {
                                        dividerIndex = index
                                    }

                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .background(AppTheme.colors.surfaceMenu),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Spacer(Modifier.width(24.dp))

                                        Icon(
                                            imageVector = Icons.Filled.KeyboardArrowUp,
                                            contentDescription = null,
                                            tint = AppTheme.colors.contentPrimary,
                                            modifier = Modifier
                                                .size(26.dp)
                                                .offset(y = 1.dp)
                                        )

                                        Spacer(Modifier.width(6.dp))

                                        Text(
                                            modifier = Modifier
                                                .padding(end = 24.dp)
                                                .padding(vertical = 16.dp),
                                            text = stringResource(if (index == 0) R.string.drag_up else R.string.active_modes),
                                            style = AppTheme.typography.sourceType.copy(fontSize = 11.sp),
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            color = AppTheme.colors.contentPrimary
                                        )
                                    }
                                }
                            }

                            is DraggableLampItem.LampMode -> ReorderableItem(
                                state = reorderableLazyListState,
                                key = item.item.id
                            ) { isDragging ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(AppTheme.colors.surfaceBackground)
                                        .padding(vertical = 12.dp)
                                        .padding(end = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(Modifier.width(24.dp))

                                    Column(Modifier.weight(1f)) {
                                        Text(
                                            text = if (item.showPos) {
                                                "${(index + 1)}. ${stringResource(item.item.displayTitle)}"
                                            } else stringResource(item.item.displayTitle),
                                            style = AppTheme.typography.cardTitle,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            color = AppTheme.colors.contentPrimary
                                        )

                                        Spacer(Modifier.height(4.dp))

                                        Text(
                                            text = stringResource(item.item.description),
                                            style = AppTheme.typography.idTitle,
                                            color = AppTheme.colors.contentPrimary.copy(.5f)
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .draggableHandle()
                                            .padding(vertical = 14.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Spacer(Modifier.width(36.dp))

                                        Icon(
                                            painter = painterResource(R.drawable.ic_drag_handle),
                                            contentDescription = null,
                                            tint = AppTheme.colors.contentPrimary.copy(.5f),
                                            modifier = Modifier
                                                .size(36.dp)
                                        )

                                        Spacer(Modifier.width(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(.1f))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(onClick = {
                        when (step) {
                            KeyBindingDialogStep.SET_KEY_BIND -> onDismiss()
                            KeyBindingDialogStep.SET_ACTION -> step =
                                KeyBindingDialogStep.SET_KEY_BIND

                            KeyBindingDialogStep.SET_APP -> step = KeyBindingDialogStep.SET_ACTION
                            KeyBindingDialogStep.SET_LINK -> step = KeyBindingDialogStep.SET_ACTION
                            KeyBindingDialogStep.DRIVE_MODE_WARNING -> step =
                                KeyBindingDialogStep.SET_ACTION

                            KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> step =
                                KeyBindingDialogStep.SET_ACTION

                            KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> step =
                                KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD

                            KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> step =
                                KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD

                            KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> step =
                                KeyBindingDialogStep.SET_ACTION

                            KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> step =
                                KeyBindingDialogStep.SET_ACTION
                        }
                    }
                    )
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                text = stringResource(
                    when (step) {
                        KeyBindingDialogStep.SET_KEY_BIND -> android.R.string.cancel
                        KeyBindingDialogStep.SET_ACTION -> R.string.back
                        KeyBindingDialogStep.SET_APP -> R.string.back
                        KeyBindingDialogStep.SET_LINK -> R.string.back
                        KeyBindingDialogStep.DRIVE_MODE_WARNING -> R.string.back
                        KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> R.string.back
                        KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> R.string.back
                        KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> R.string.back
                        KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> R.string.back
                        KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> R.string.back
                    }
                ).uppercase(),
                style = AppTheme.typography.dialogButton,
                color = AppTheme.colors.contentAccent
            )
            val enableOk by remember {
                derivedStateOf {
                    when (step) {
                        KeyBindingDialogStep.SET_KEY_BIND -> bind != null
                        KeyBindingDialogStep.SET_ACTION -> true
                        KeyBindingDialogStep.SET_APP -> apps?.any { it.isSelected } == true
                        KeyBindingDialogStep.SET_LINK -> link != null
                        KeyBindingDialogStep.DRIVE_MODE_WARNING -> true
                        KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> false
                        KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> dmToggleSelected != null
                        KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> carouselDriveModes.indexOfFirst { it is DraggableDMItem.Divider } > 1
                        KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> numberValue.text.length > 2
                        KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> carouselLightModes.indexOfFirst { it is DraggableLampItem.Divider } > 0
                    }
                }
            }
            if (step !in listOf(
                    KeyBindingDialogStep.SET_ACTION,
                    KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD
                )
            ) {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable(enabled = enableOk) {
                            when (step) {
                                KeyBindingDialogStep.SET_KEY_BIND -> {
                                    step = KeyBindingDialogStep.SET_ACTION
                                }

                                KeyBindingDialogStep.SET_ACTION -> {
                                    step = KeyBindingDialogStep.SET_APP
                                }

                                KeyBindingDialogStep.SET_APP -> {
                                    scope.launch(Dispatchers.IO) {
                                        try {
                                            val name =
                                                bind?.bind?.let { keyBindStorage.getBindName(it) }
                                                    ?: ""

                                            keyBindStorage.saveBinds(
                                                name, KeyBindConfig(
                                                    action = KeyBindAction.LAUNCH_APP,
                                                    value = apps?.find { it.isSelected }?.packageName
                                                        ?: ""
                                                )
                                            )
                                            onDismiss()
                                        } catch (_: Exception) {
                                        }
                                    }
                                }

                                KeyBindingDialogStep.SET_LINK -> {
                                    scope.launch(Dispatchers.IO) {
                                        try {
                                            val name =
                                                bind?.bind?.let { keyBindStorage.getBindName(it) }
                                                    ?: ""

                                            keyBindStorage.saveBinds(
                                                name, KeyBindConfig(
                                                    action = KeyBindAction.LAUNCH_LINK,
                                                    value = link?.intentUri ?: ""
                                                )
                                            )
                                            onDismiss()
                                        } catch (_: Exception) {
                                        }
                                    }
                                }

                                KeyBindingDialogStep.DRIVE_MODE_WARNING -> {
                                    step = KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD
                                }

                                KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> Unit

                                KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> {
                                    scope.launch(Dispatchers.IO) {
                                        try {
                                            val name =
                                                bind?.bind?.let { keyBindStorage.getBindName(it) }
                                                    ?: ""

                                            dmToggleSelected?.let { tdm ->
                                                keyBindStorage.saveBinds(
                                                    name, KeyBindConfig(
                                                        action = KeyBindAction.TOGGLE_DM,
                                                        value = "${tdm.id}"
                                                    )
                                                )
                                            }
                                            onDismiss()
                                        } catch (_: Exception) {
                                        }
                                    }
                                }

                                KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> {
                                    scope.launch(Dispatchers.IO) {
                                        try {
                                            val name =
                                                bind?.bind?.let { keyBindStorage.getBindName(it) }
                                                    ?: ""

                                            val carousel = carouselDriveModes
                                                .takeWhile { it !is DraggableDMItem.Divider }
                                                .filterIsInstance<DraggableDMItem.DriveMode>()
                                                .map { it.item.id }

                                            if (carousel.isNotEmpty()) {
                                                keyBindStorage.saveBinds(
                                                    name, KeyBindConfig(
                                                        action = KeyBindAction.CAROUSEL_DM,
                                                        value = carousel.joinToString("|")
                                                    )
                                                )
                                            }
                                            onDismiss()
                                        } catch (_: Exception) {
                                        }
                                    }
                                }

                                KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> {
                                    scope.launch(Dispatchers.IO) {
                                        val number = numberValue.text.onlyDigitsAndLeadingPlus()

                                        // guard: check format
                                        if (number.trim().isEmpty()) {
                                            context.inMainToast(context.getString(R.string.enter_number))
                                            return@launch
                                        }

                                        val name = bind?.bind
                                            ?.let { keyBindStorage.getBindName(it) }
                                            ?: ""

                                        keyBindStorage.saveBinds(
                                            name, KeyBindConfig(
                                                action = KeyBindAction.PHONE_CALL,
                                                value = number
                                            )
                                        )
                                        onDismiss()
                                    }
                                }

                                KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> {
                                    scope.launch(Dispatchers.IO) {
                                        try {
                                            val name =
                                                bind?.bind?.let { keyBindStorage.getBindName(it) }
                                                    ?: ""

                                            val carousel = carouselLightModes
                                                .takeWhile { it !is DraggableLampItem.Divider }
                                                .filterIsInstance<DraggableLampItem.LampMode>()
                                                .map { it.item.id }

                                            if (carousel.isNotEmpty()) {
                                                keyBindStorage.saveBinds(
                                                    name, KeyBindConfig(
                                                        action = KeyBindAction.CAROUSEL_LAMP,
                                                        value = carousel.joinToString("|")
                                                    )
                                                )
                                            }
                                            onDismiss()
                                        } catch (_: Exception) {
                                        }
                                    }
                                }
                            }
                        }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    text = stringResource(
                        when (step) {
                            KeyBindingDialogStep.SET_KEY_BIND -> R.string.next
                            KeyBindingDialogStep.SET_ACTION -> R.string.next
                            KeyBindingDialogStep.SET_APP -> android.R.string.ok
                            KeyBindingDialogStep.SET_LINK -> android.R.string.ok
                            KeyBindingDialogStep.DRIVE_MODE_WARNING -> android.R.string.ok
                            KeyBindingDialogStep.SET_DRIVE_MODE_CHOOSE_METHOD -> R.string.next
                            KeyBindingDialogStep.SET_TOGGLE_DRIVE_MODE -> android.R.string.ok
                            KeyBindingDialogStep.SET_CAROUSEL_DRIVE_MODE -> android.R.string.ok
                            KeyBindingDialogStep.SET_CALL_PHONE_NUMBER -> android.R.string.ok
                            KeyBindingDialogStep.SET_CAROUSEL_CAR_LAMP -> android.R.string.ok
                        }
                    ).uppercase(),
                    style = AppTheme.typography.dialogButton,
                    color = if (enableOk) {
                        AppTheme.colors.contentAccent
                    } else AppTheme.colors.contentPrimary.copy(.3f)
                )
            }
        }
    }
}
