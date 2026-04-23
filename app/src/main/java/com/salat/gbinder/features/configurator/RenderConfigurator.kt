package com.salat.gbinder.features.configurator

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.salat.gbinder.MainViewModel
import com.salat.gbinder.R
import com.salat.gbinder.car.domain.entity.IdType
import com.salat.gbinder.car.domain.entity.PropertyStatus
import com.salat.gbinder.car.domain.util.getAllProperty
import com.salat.gbinder.car.domain.util.glyCarAreaIdMap
import com.salat.gbinder.components.cleanupShareTempFiles
import com.salat.gbinder.components.inMainToast
import com.salat.gbinder.components.openMacroInMacroDroid
import com.salat.gbinder.components.toast
import com.salat.gbinder.datastore.FavoriteStorageRepository
import com.salat.gbinder.entity.DisplayPropertyItem
import com.salat.gbinder.entity.NoteStatus
import com.salat.gbinder.entity.PropertyListEmptyType
import com.salat.gbinder.ui.ConfirmDialog
import com.salat.gbinder.ui.FullScreenDialog
import com.salat.gbinder.ui.TopShadow
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.KvResult
import com.salat.gbinder.util.createHighlightedText
import com.salat.gbinder.util.getLatestFirestoreValue
import com.salat.gbinder.util.saveFirestoreValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.math.abs
import kotlin.random.Random

private const val POLLING_RATE = 1000L
private const val BOTTOM_OFFSET = 380

@Composable
fun RenderConfigurator(
    viewModel: MainViewModel,
    uiScaleState: Float? = null,
    onlyFavorite: Boolean = false,
    favoriteStorage: FavoriteStorageRepository,
    onClose: () -> Unit
) {
    var allZones: Map<Int, String> by remember { mutableStateOf(emptyMap()) }
    var allPropertyList: List<DisplayPropertyItem> by remember { mutableStateOf(emptyList()) }
    var filteredPropertyList: List<DisplayPropertyItem> by remember { mutableStateOf(emptyList()) }
    var pickedProperty: DisplayPropertyItem? by remember { mutableStateOf(null) }

    var searchTextValue: TextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val fieldFocusRequester = remember { FocusRequester() }

    val keyboardController = LocalSoftwareKeyboardController.current

    val prepared by remember { derivedStateOf { allPropertyList.isNotEmpty() } }

    BackHandler(enabled = searchTextValue.text.isNotEmpty()) {
        searchTextValue = TextFieldValue("")
    }

    val favorites by favoriteStorage.getFavoritesFlow().collectAsState(initial = emptyList())

    LaunchedEffect(favorites) {
        withContext(Dispatchers.Default) {
            allZones = glyCarAreaIdMap()
            val allProp = getAllProperty()
            allPropertyList = allProp.map { it ->
                DisplayPropertyItem(
                    alias = AnnotatedString(it.alias),
                    type = it.type,
                    key = AnnotatedString(if (it.alias.contains(it.key)) it.value.toString() else "${it.value} (${it.key})"),
                    value = it.value,
                    description = it.description?.let { desc -> AnnotatedString(desc) },
                    possibleValues = it.possibleValues
                )
            }

            val showList = if (onlyFavorite) {
                allPropertyList.filter {
                    "${it.value}_${it.alias}" in favorites
                }
            } else allPropertyList

            filteredPropertyList = showList
        }
    }

    LaunchedEffect(searchTextValue, filteredPropertyList) {
        withContext(Dispatchers.Default) {
            filteredPropertyList = if (searchTextValue.text.isEmpty()) {
                allPropertyList
            } else {
                val word = searchTextValue.text.lowercase()
                allPropertyList
                    .filter {
                        it.alias.text.lowercase().contains(word) ||
                                it.key.text.lowercase().contains(word) ||
                                it.description?.text?.lowercase()?.contains(word) == true
                    }
                    .map {
                        it.copy(
                            alias = createHighlightedText(it.alias.text, word),
                            key = createHighlightedText(it.key.text, word),
                            description = it.description?.let { desc ->
                                createHighlightedText(desc.text, word)
                            }
                        )
                    }
            }.filter {
                if (onlyFavorite) {
                    "${it.value}_${it.alias}" in favorites
                } else true
            }
        }
    }

    Column(Modifier.fillMaxSize()) {

        // Toolbar
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(56.dp)
                    .padding(start = 2.dp),
                onClick = remember { { onClose() } }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = AppTheme.colors.contentPrimary,
                    contentDescription = stringResource(R.string.back)
                )
            }

            Spacer(Modifier.width(16.dp))

            if (prepared) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    val fieldTypography = AppTheme.typography.stubTitle
                    BasicTextField(
                        value = searchTextValue,
                        onValueChange = { searchTextValue = it },
                        cursorBrush = SolidColor(AppTheme.colors.contentAccent),
                        textStyle = fieldTypography.copy(color = AppTheme.colors.contentPrimary),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier,
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (searchTextValue.text.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.search),
                                        style = fieldTypography.copy(
                                            color = AppTheme.colors.contentPrimary.copy(.4f)
                                        )
                                    )
                                }
                                innerTextField()
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search,
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Text,
                            autoCorrectEnabled = false
                        ),
                        keyboardActions = KeyboardActions(onSearch = {
                            defaultKeyboardAction(ImeAction.Search)
                            keyboardController?.hide()
                        }),
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .offset(y = .2.dp)
                            .focusRequester(fieldFocusRequester),
                    )
                }
            }

            Spacer(Modifier.width(36.dp))

            val showClear by remember(searchTextValue) {
                derivedStateOf { searchTextValue.text.isNotEmpty() }
            }
            AnimatedVisibility(
                visible = showClear,
                enter = fadeIn(tween(200)),
                exit = fadeOut(tween(200)),
            ) {
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(end = 2.dp),
                    onClick = remember {
                        {
                            searchTextValue = TextFieldValue("")
                            fieldFocusRequester.requestFocus()
                            keyboardController?.show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        tint = AppTheme.colors.contentPrimary,
                        contentDescription = null
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(AppTheme.colors.surfaceSettingsLayer1)
        ) {
            TopShadow()

            val emptyTitle: PropertyListEmptyType? by remember {
                derivedStateOf {
                    if (filteredPropertyList.isEmpty()) {
                        if (searchTextValue.text.isNotEmpty()) {
                            PropertyListEmptyType.NO_FOUND
                        } else if (onlyFavorite) {
                            PropertyListEmptyType.ADD_FAVORITE
                        } else null
                    } else null
                }
            }

            when (emptyTitle) {
                PropertyListEmptyType.NO_FOUND -> {
                    Text(
                        text = stringResource(R.string.nothing_found),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 36.dp),
                        color = AppTheme.colors.contentPrimary.copy(.4f),
                        style = AppTheme.typography.dialogSubtitle,
                        textAlign = TextAlign.Center
                    )
                }

                PropertyListEmptyType.ADD_FAVORITE -> {
                    Text(
                        text = stringResource(R.string.favorites_display),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 36.dp),
                        color = AppTheme.colors.contentPrimary.copy(.4f),
                        style = AppTheme.typography.dialogSubtitle,
                        textAlign = TextAlign.Center
                    )
                }

                else -> Unit
            }

            CompositionLocalProvider(LocalOverscrollFactory provides null) {
                LazyColumn {
                    item(key = -1) {
                        Spacer(Modifier.height(.5.dp))
                    }

                    items(
                        items = filteredPropertyList,
                        key = { item -> item.alias.text + item.value }
                    ) { item ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    pickedProperty = item
                                }
                                .padding(horizontal = 20.dp, vertical = 16.dp)
                        ) {


                            Text(
                                text = item.alias,
                                color = AppTheme.colors.contentPrimary,
                                style = AppTheme.typography.cardTitle
                            )

                            item.description?.let { description ->
                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = description,
                                    color = AppTheme.colors.contentPrimary.copy(.7f),
                                    style = AppTheme.typography.dialogSubtitle
                                )

                                Spacer(modifier = Modifier.height(2.dp))
                            }

                            Spacer(Modifier.height(6.dp))

                            Row {
                                Text(
                                    text = buildAnnotatedString {
                                        append("ID: ")
                                        append(item.key)
                                    },
                                    color = AppTheme.colors.contentPrimary.copy(.4f),
                                    style = AppTheme.typography.dialogSubtitle
                                )

                                Spacer(Modifier.width(12.dp))
                                Text(
                                    text = "${stringResource(R.string.type)}:",
                                    color = AppTheme.colors.contentPrimary.copy(.4f),
                                    style = AppTheme.typography.dialogSubtitle
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(
                                            when (item.type) {
                                                1 -> AppTheme.colors.addSplitBottom
                                                2 -> AppTheme.colors.addSplitTop
                                                3 -> AppTheme.colors.deleteButton
                                                else -> AppTheme.colors.surfaceSettings
                                            }
                                        )
                                        .padding(horizontal = 6.dp, vertical = 2.dp),
                                    text = when (item.type) {
                                        1 -> stringResource(R.string.info)
                                        2 -> stringResource(R.string.function)
                                        3 -> stringResource(R.string.single_sensor)
                                        else -> stringResource(R.string.unknown)
                                    },
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.idTitle
                                )
                            }
                        }

                        if (filteredPropertyList.last().value != item.value) {
                            Spacer(
                                Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(AppTheme.colors.surfaceMenuDivider)
                            )
                        }
                    }

                    item(key = -2) {
                        Spacer(Modifier.height(BOTTOM_OFFSET.dp))
                    }
                }
            }
        }
    }

    pickedProperty?.let {
        EditPropertyDialog(
            property = it,
            allZones = allZones,
            viewModel = viewModel,
            favoriteStorage = favoriteStorage,
            uiScaleState = uiScaleState,
            onDismiss = { pickedProperty = null }
        )
    }
}

@Suppress("DEPRECATION")
@Composable
private fun EditPropertyDialog(
    property: DisplayPropertyItem,
    allZones: Map<Int, String>,
    viewModel: MainViewModel,
    favoriteStorage: FavoriteStorageRepository,
    uiScaleState: Float? = null,
    onDismiss: () -> Unit = {}
) = FullScreenDialog(
    modifier = Modifier,
    uiScaleState = uiScaleState,
    onDismissRequest = onDismiss
) {
    val scope = rememberCoroutineScope()

    DisposableEffect(property) {
        viewModel.setOpenedProperty(property)
        onDispose {
            viewModel.setOpenedProperty(null)
        }
    }

    var supportStatus: PropertyStatus? by remember { mutableStateOf(PropertyStatus.NOT_ACTIVE) }
    var currentValues: Map<Int, Pair<Int, Float>> by remember { mutableStateOf(emptyMap()) }
    var supportValues: Map<Int, Set<Int>> by remember { mutableStateOf(emptyMap()) }

    var zoneTextValue: TextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var valueTextValue: TextFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current
    var isFavorite: Boolean by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            isFavorite = favoriteStorage.getFavorites()
                .contains("${property.value}_${property.alias}") == true
        }
    }

    /* var valueUpdater by remember { mutableIntStateOf(0) }
    LaunchedEffect(valueUpdater) {
        withContext(Dispatchers.IO) {
            launch {
                currentValues =
                    viewModel.getPropertyValuesWithType(property.value, property.type)
//                currentValues =
//                    mapOf(Int.MIN_VALUE to (124 to 1f), 131080 to (5162636 to -1254f))
            }
        }
    } */

    val grayColor = AppTheme.colors.contentPrimary.copy(.4f)
    val valuesText by remember(currentValues) {
        derivedStateOf {
            buildAnnotatedString {
                append("${context.getString(R.string.current_values)}: ")

                if (currentValues.isEmpty()) {
                    append(context.getString(R.string.unknown))
                } else {
                    append("\n")
                    currentValues.forEach { (zone, v) ->
                        val (intValue, floatValue) = v

                        // Zone
                        if (zone == Integer.MIN_VALUE) {
                            append("${allZones[zone]} -> ")
                        } else {
                            append(allZones.getOrDefault(zone, context.getString(R.string.unknown)))
                            withStyle(SpanStyle(color = grayColor)) { append(" [ id: ") }
                            append(zone.toString())
                            withStyle(SpanStyle(color = grayColor)) { append(" ] ") }
                            append("-> ")
                        }

                        withStyle(SpanStyle(color = grayColor)) { append("[ int: ") }
                        append(intValue.toString())
                        withStyle(SpanStyle(color = grayColor)) { append(", float: ") }
                        append(floatValue.toString())
                        withStyle(SpanStyle(color = grayColor)) { append(" ]") }
                        if (currentValues.keys.last() != zone) {
                            append("\n")
                        }
                    }
                }
            }
        }
    }

    fun generateGuid() = (-abs(Random.nextLong())).toString()

    fun generateTimestamp() = "${System.currentTimeMillis()}"

    /**
     * SETTERS MD EXPORT
     */
    suspend fun Context.exportSetMDMacro(isInt: Boolean = true) {
        val pattern = readJsonFromRaw(this, R.raw.md_send_intent)

        val hasZone = zoneTextValue.text.isNotEmpty()

        val data = pattern
            .replace("\"%TIMESTAMP%\"", generateTimestamp())
            .replace("\"%GUID%\"", generateGuid())
            .replace("\"%SIGUID%\"", generateGuid())
            .replace("\"%SIGUID2%\"", generateGuid())
            .replace(
                "%INTENT_ACTION%", if (isInt) {
                    "com.salat.gbinder.SET_INT_PROPERTY"
                } else {
                    "com.salat.gbinder.SET_FLOAT_PROPERTY"
                }
            )
            .replace("%EXTRA_NAME_1%", "id")
            .replace("%EXTRA_VALUE_1%", "${property.value}")
            .replace("%EXTRA_NAME_2%", "value")
            .replace("%EXTRA_VALUE_2%", valueTextValue.text)
            .replace("%EXTRA_NAME_3%", if (hasZone) "area" else "")
            .replace(
                "%EXTRA_VALUE_3%", if (hasZone) {
                    zoneTextValue.text
                } else ""
            )
            .replace(
                "%MACRO_NAME%",
                "SET ${property.alias.text.tailAfterLastDot()} = ${valueTextValue.text} ${if (isInt) "INT" else "FLOAT"} VALUE"
            )
        runCatching {
            context.cleanupShareTempFiles()
            context.openMacroInMacroDroid(data)
        }.onFailure { inMainToast("MacroDroid not found") }
    }

    fun exportSetAsInt() = scope.launch(Dispatchers.IO) {
        context.exportSetMDMacro(true)
    }

    fun exportSetAsFloat() = scope.launch(Dispatchers.IO) {
        context.exportSetMDMacro(false)
    }

    /**
     * GETTERS MD EXPORT
     */
    suspend fun Context.exportGetMDMacro(isInt: Boolean = true) {
        val pattern = readJsonFromRaw(this, R.raw.md_get_value_intent)

        val hasZone = zoneTextValue.text.isNotEmpty()

        val extraParams = buildList {
            // id
            add("id")
            // area
            if (property.type != IdType.ID_TYPE_SENSOR && hasZone) {
                add("area")
            }
            // value
            add("value")
        }.joinToString(", ") { "\"${it}\"" }

        val extraValues = buildList {
            // id
            add(property.value.toString())
            // area
            if (property.type != IdType.ID_TYPE_SENSOR && hasZone) {
                add(zoneTextValue.text)
            }
            // value
            add("*")
        }.joinToString(", ") { "\"${it}\"" }

        val extraVars = buildList {
            // id
            add(null)
            // area
            if (property.type != IdType.ID_TYPE_SENSOR && hasZone) {
                add(null)
            }
            // value
            add("result")
        }.joinToString(", ") { if (it == null) "null" else "\"${it}\"" }

        val data = pattern
            .replace("\"%TIMESTAMP%\"", generateTimestamp())
            .replace("\"%GUID%\"", generateGuid())
            .replace(
                "%INTENT_ACTION%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    if (isInt) {
                        "com.salat.gbinder.GET_INT_SENSOR"
                    } else "com.salat.gbinder.GET_FLOAT_SENSOR"
                } else {
                    if (isInt) {
                        "com.salat.gbinder.GET_INT_PROPERTY"
                    } else "com.salat.gbinder.GET_FLOAT_PROPERTY"
                }
            )
            .replace("%EXTRA_NAME_1%", "id")
            .replace("%EXTRA_VALUE_1%", "${property.value}")
            .replace(
                "%EXTRA_NAME_2%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    ""
                } else if (hasZone) "area" else ""
            )
            .replace(
                "%EXTRA_VALUE_2%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    ""
                } else {
                    if (hasZone) zoneTextValue.text else ""
                }
            )
            .replace("\"%SIGUID1%\"", generateGuid())
            .replace(
                "%RECEIVE_ACTION%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    if (isInt) {
                        "com.salat.gbinder.SENSOR_INT_RESULT"
                    } else "com.salat.gbinder.SENSOR_FLOAT_RESULT"
                } else {
                    if (isInt) {
                        "com.salat.gbinder.PROPERTY_INT_RESULT"
                    } else "com.salat.gbinder.PROPERTY_FLOAT_RESULT"
                }
            )
            .replace("\"%RECEIVE_PARAMS%\"", extraParams)
            .replace("\"%RECEIVE_VALUES%\"", extraValues)
            .replace("\"%RECEIVE_VARIABLES%\"", extraVars)
            .replace("\"%SIGUID2%\"", generateGuid())
            .replace("\"%SIGUID3%\"", generateGuid())
            .replace("\"%SIGUID4%\"", generateGuid())
            .replace("\"%SIGUID5%\"", generateGuid())
            .replace(
                "%MACRO_NAME%",
                "GET ${property.alias.text.tailAfterLastDot()} ${if (isInt) "INT" else "FLOAT"} VALUE"
            )
        runCatching {
            context.cleanupShareTempFiles()
            context.openMacroInMacroDroid(data)
        }.onFailure { inMainToast("MacroDroid not found") }
    }

    fun exportGetAsInt() = scope.launch(Dispatchers.IO) {
        context.exportGetMDMacro(true)
    }

    fun exportGetAsFloat() = scope.launch(Dispatchers.IO) {
        context.exportGetMDMacro(false)
    }

    /**
     * LISTENERS MD EXPORT
     */
    suspend fun Context.exportListenerMDMacro(isInt: Boolean = true) {
        val pattern = readJsonFromRaw(this, R.raw.md_listen_value_intent)

        val hasZone = zoneTextValue.text.isNotEmpty()

        val extraParams = buildList {
            // id
            add("id")
            // area
            if (property.type != IdType.ID_TYPE_SENSOR && hasZone) {
                add("area")
            }
            // value
            add("value")
        }.joinToString(", ") { "\"${it}\"" }

        val extraValues = buildList {
            // id
            add(property.value.toString())
            // area
            if (property.type != IdType.ID_TYPE_SENSOR && hasZone) {
                add(zoneTextValue.text)
            }
            // value
            add("*")
        }.joinToString(", ") { "\"${it}\"" }

        val extraVars = buildList {
            // id
            add(null)
            // area
            if (property.type != IdType.ID_TYPE_SENSOR && hasZone) {
                add(null)
            }
            // value
            add("result")
        }.joinToString(", ") { if (it == null) "null" else "\"${it}\"" }

        val data = pattern
            .replace("\"%TIMESTAMP%\"", generateTimestamp())
            .replace("\"%GUID%\"", generateGuid())
            .replace("\"%SIGUID1%\"", generateGuid())
            .replace("\"%SIGUID2%\"", generateGuid())
            .replace("\"%SIGUID3%\"", generateGuid())
            .replace("\"%SIGUID4%\"", generateGuid())
            .replace("\"%SIGUID5%\"", generateGuid())
            .replace("\"%SIGUID6%\"", generateGuid())
            .replace("\"%SIGUID7%\"", generateGuid())
            .replace("\"%SIGUID8%\"", generateGuid())
            .replace("\"%SIGUID9%\"", generateGuid())
            .replace("\"%SIGUID_10%\"", generateGuid())

            .replace(
                "%INTENT_ACTION%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    "com.salat.gbinder.LISTEN_SENSOR_CHANGES"
                } else {
                    "com.salat.gbinder.LISTEN_PROPERTY_CHANGES"
                }
            )
            .replace("%EXTRA_NAME_1%", "id")
            .replace("%EXTRA_VALUE_1%", "${property.value}")
            .replace(
                "%EXTRA_NAME_2%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    ""
                } else if (hasZone) "area" else ""
            )
            .replace(
                "%EXTRA_VALUE_2%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    ""
                } else {
                    if (hasZone) zoneTextValue.text else ""
                }
            )
            .replace(
                "%TRIGGER_ACTION%", if (property.type == IdType.ID_TYPE_SENSOR) {
                    if (isInt) {
                        "com.salat.gbinder.SENSOR_INT_CHANGED"
                    } else "com.salat.gbinder.SENSOR_FLOAT_CHANGED"
                } else {
                    if (isInt) {
                        "com.salat.gbinder.PROPERTY_INT_CHANGED"
                    } else "com.salat.gbinder.PROPERTY_FLOAT_CHANGED"
                }
            )
            .replace("\"%RECEIVE_PARAMS%\"", extraParams)
            .replace("\"%RECEIVE_VALUES%\"", extraValues)
            .replace("\"%RECEIVE_VARIABLES%\"", extraVars)
            .replace(
                "%MACRO_NAME%",
                "LISTEN ${property.alias.text.tailAfterLastDot()} ${if (isInt) "INT" else "FLOAT"} VALUE"
            )
        runCatching {
            context.cleanupShareTempFiles()
            context.openMacroInMacroDroid(data)
        }.onFailure { inMainToast("MacroDroid not found") }
    }

    fun exportListenAsInt() = scope.launch(Dispatchers.IO) {
        context.exportListenerMDMacro(true)
    }

    fun exportListenAsFloat() = scope.launch(Dispatchers.IO) {
        context.exportListenerMDMacro(false)
    }

    // Sensor update by loop if no subscribe
    var updateSensorByLoop by remember { mutableStateOf(true) }
    if (property.type == IdType.ID_TYPE_SENSOR) {
        /* var updateSensorByLoopJob by remember { mutableStateOf<Job?>(null) }
        LaunchedEffect(updateSensorByLoop) {
            if (updateSensorByLoopJob != null) {
                updateSensorByLoopJob?.cancel()
                updateSensorByLoopJob = null
            }

            if (updateSensorByLoop) {
                updateSensorByLoopJob = launch {
                    while (true) {
                        delay(POLLING_RATE)
                        currentValues =
                            viewModel.getPropertyValuesWithType(property.value, property.type)
                    }
                }
            }
        } */

        LaunchedEffect(updateSensorByLoop, property.value, property.type) {
            /* Cancels previous block automatically on key change or dispose */
            if (property.type != IdType.ID_TYPE_SENSOR || !updateSensorByLoop) return@LaunchedEffect

            while (isActive) {
                delay(POLLING_RATE)
                // Heavy work on IO
                val values = withContext(Dispatchers.IO) {
                    viewModel.getPropertyValuesWithType(property.value, property.type)
                }
                // Publish to Compose state on Main
                currentValues = values
            }
        }
    }

    // Function changed listener
    LaunchedEffect(Unit) {
        launch {
            currentValues =
                viewModel.getPropertyValuesWithType(property.value, property.type)
        }
        launch {
            viewModel.configuratorFunctionChangeIntValueFlow.collect {
                currentValues =
                    viewModel.getPropertyValuesWithType(property.value, property.type)
            }
        }
        launch {
            viewModel.configuratorFunctionChangeFloatValueFlow.collect {
                currentValues =
                    viewModel.getPropertyValuesWithType(property.value, property.type)
            }
        }
        launch {
            viewModel.configuratorSensorChangeIntValueFlow.collect {
                currentValues =
                    viewModel.getPropertyValuesWithType(property.value, property.type)
                updateSensorByLoop = false
            }
        }
        launch {
            viewModel.configuratorSensorChangeFloatValueFlow.collect {
                currentValues =
                    viewModel.getPropertyValuesWithType(property.value, property.type)
                updateSensorByLoop = false
            }
        }
    }

    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            launch {
                supportStatus = viewModel.getSupportStatus(property.value, property.type)
            }
            launch {
                supportValues =
                    viewModel.getPropertySupportedValuesWithType(property.value, property.type)
//                supportValues =
//                    mapOf(Int.MIN_VALUE to setOf(14124, 4122, 3125), 537526530 to setOf(125, 6346))
            }
        }
    }

    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        Column(
            Modifier
                .background(AppTheme.colors.surfaceBackground)
                .padding(innerPadding)
        ) {

            // Toolbar
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(start = 2.dp),
                    onClick = remember { { onDismiss() } }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = AppTheme.colors.contentPrimary,
                        contentDescription = stringResource(R.string.back)
                    )
                }

                Spacer(Modifier.weight(1f))

                var showInfoDialog by remember { mutableStateOf(false) }
                if (showInfoDialog) {
                    ConfirmDialog(
                        title = stringResource(R.string.attention),
                        message = stringResource(R.string.function_status_disclaimer),
                        uiScale = uiScaleState,
                        disableNegative = true,
                        negativeAction = false,
                        onCancel = { showInfoDialog = false },
                        onDismiss = { showInfoDialog = false },
                        onClick = { showInfoDialog = false }
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(start = 2.dp),
                    onClick = { showInfoDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        tint = AppTheme.colors.contentPrimary,
                        contentDescription = "info"
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(start = 2.dp),
                    onClick = {
                        if (isFavorite) {
                            scope.launch {
                                favoriteStorage.removeFavorite("${property.value}_${property.alias}")
                                isFavorite = false
                            }
                        } else {
                            scope.launch {
                                favoriteStorage.addFavorite("${property.value}_${property.alias}")
                                isFavorite = true
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        tint = AppTheme.colors.contentPrimary,
                        contentDescription = stringResource(R.string.back)
                    )
                }

                Spacer(Modifier.width(16.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.surfaceSettingsLayer1)
            ) {
                TopShadow()

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(top = 22.dp)
                ) {
                    SelectionContainer {
                        Column {

                            Text(
                                text = property.alias.text,
                                modifier = Modifier.padding(horizontal = 24.dp),
                                color = AppTheme.colors.contentPrimary,
                                style = AppTheme.typography.dialogTitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )

                            property.description?.let { description ->
                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = description.text,
                                    modifier = Modifier.padding(horizontal = 24.dp),
                                    color = AppTheme.colors.contentPrimary.copy(.7f),
                                    style = AppTheme.typography.dialogSubtitle
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.padding(horizontal = 24.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(Modifier.weight(1f)) {
                                    Text(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(
                                                when (property.type) {
                                                    1 -> AppTheme.colors.addSplitBottom
                                                    2 -> AppTheme.colors.addSplitTop
                                                    3 -> AppTheme.colors.deleteButton
                                                    else -> AppTheme.colors.surfaceSettings
                                                }
                                            )
                                            .padding(horizontal = 6.dp, vertical = 2.dp),
                                        text = when (property.type) {
                                            1 -> stringResource(R.string.info)
                                            2 -> stringResource(R.string.function)
                                            3 -> stringResource(R.string.single_sensor)
                                            else -> stringResource(R.string.unknown)
                                        },
                                        color = AppTheme.colors.contentPrimary,
                                        style = AppTheme.typography.idTitle
                                    )
                                }

                                Text(
                                    text = buildAnnotatedString {
                                        append("${stringResource(R.string.status)}: ")
                                        withStyle(
                                            SpanStyle(
                                                color = when (supportStatus) {
                                                    PropertyStatus.ACTIVE -> AppTheme.colors.addSplitTop
                                                    PropertyStatus.NOT_ACTIVE -> AppTheme.colors.warning
                                                    else -> AppTheme.colors.deleteButton
                                                }
                                            )
                                        ) {
                                            append(
                                                when (supportStatus) {
                                                    PropertyStatus.ACTIVE -> stringResource(R.string.active)
                                                    PropertyStatus.NOT_ACTIVE -> stringResource(R.string.inactive)
                                                    PropertyStatus.NOT_AVAILABLE -> stringResource(R.string.not_available)
                                                    PropertyStatus.ERROR -> stringResource(R.string.error)
                                                    null -> ""
                                                }
                                            )
                                        }
                                    },
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .weight(1f),
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.dialogSubtitle
                                )

                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = buildAnnotatedString {
                                        append("ID: ")
                                        append(property.value.toString())
                                    },
                                    textAlign = TextAlign.End,
                                    color = grayColor,
                                    style = AppTheme.typography.dialogSubtitle
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Spacer(
                                Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.White.copy(.1f))
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = valuesText,
                                modifier = Modifier.padding(horizontal = 24.dp),
                                color = AppTheme.colors.contentPrimary,
                                style = AppTheme.typography.dialogSubtitle
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    if (property.type.canEditProperty()) {
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.White.copy(.1f))
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "${stringResource(R.string.set_value)}:",
                            modifier = Modifier.padding(horizontal = 24.dp),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.dialogSubtitle
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalArrangement = Arrangement.spacedBy(24.dp)
                        ) {

                            var isFocusedZone by remember { mutableStateOf(false) }
                            val focusManager = LocalFocusManager.current
                            BasicTextField(
                                value = zoneTextValue,
                                onValueChange = {
                                    zoneTextValue = it
                                },
                                cursorBrush = SolidColor(AppTheme.colors.contentPrimary.copy(alpha = .9f)),
                                textStyle = AppTheme.typography.buttonTitle.copy(
                                    color = AppTheme.colors.contentPrimary
                                ),
                                decorationBox = { innerTextField ->
                                    Column(Modifier.fillMaxWidth()) {
                                        Box(Modifier.fillMaxWidth()) {
                                            innerTextField()
                                            if (zoneTextValue.text.isEmpty()) {
                                                Text(
                                                    text = "Zone",
                                                    style = AppTheme.typography.buttonTitle,
                                                    color = AppTheme.colors.contentPrimary.copy(.5f),
                                                    overflow = TextOverflow.Ellipsis,
                                                    maxLines = 1
                                                )
                                            }
                                        }
                                        Spacer(Modifier.height(3.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(2.dp)
                                                .background(
                                                    if (isFocusedZone) {
                                                        AppTheme.colors.contentAccent
                                                    } else {
                                                        grayColor
                                                    }
                                                )
                                        )
                                        Spacer(Modifier.height(0.dp))
                                    }
                                },
                                keyboardActions = KeyboardActions(onDone = {
                                    defaultKeyboardAction(ImeAction.Done)
                                    scope.launch {
                                        delay(100L)
                                        focusManager.clearFocus()
                                    }
                                }),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Next,
                                    capitalization = KeyboardCapitalization.None,
                                    keyboardType = KeyboardType.Number,
                                    autoCorrectEnabled = false
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .onFocusChanged { focusState ->
                                        isFocusedZone = focusState.isFocused
                                    },
                                maxLines = 1
                            )

                            var isFocusedValue by remember { mutableStateOf(false) }
                            BasicTextField(
                                value = valueTextValue,
                                onValueChange = {
                                    valueTextValue = it
                                },
                                cursorBrush = SolidColor(AppTheme.colors.contentPrimary.copy(alpha = .9f)),
                                textStyle = AppTheme.typography.buttonTitle.copy(
                                    color = AppTheme.colors.contentPrimary
                                ),
                                decorationBox = { innerTextField ->
                                    Column(Modifier.fillMaxWidth()) {
                                        Box(Modifier.fillMaxWidth()) {
                                            innerTextField()
                                            if (valueTextValue.text.isEmpty()) {
                                                Text(
                                                    text = stringResource(R.string.value),
                                                    style = AppTheme.typography.buttonTitle,
                                                    color = AppTheme.colors.contentPrimary.copy(.5f),
                                                    overflow = TextOverflow.Ellipsis,
                                                    maxLines = 1
                                                )
                                            }
                                        }
                                        Spacer(Modifier.height(3.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(2.dp)
                                                .background(
                                                    if (isFocusedValue) {
                                                        AppTheme.colors.contentAccent
                                                    } else {
                                                        grayColor
                                                    }
                                                )
                                        )
                                        Spacer(Modifier.height(0.dp))
                                    }
                                },
                                keyboardActions = KeyboardActions(onDone = {
                                    defaultKeyboardAction(ImeAction.Done)
                                    scope.launch {
                                        delay(100L)
                                        focusManager.clearFocus()
                                    }
                                }),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    capitalization = KeyboardCapitalization.None,
                                    keyboardType = KeyboardType.Number,
                                    autoCorrectEnabled = false
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .onFocusChanged { focusState ->
                                        isFocusedValue = focusState.isFocused
                                    },
                                maxLines = 1
                            )

                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        val activeButton by remember { derivedStateOf { valueTextValue.text.isNotEmpty() } }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(IntrinsicSize.Min)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (activeButton) AppTheme.colors.addSplitTop else AppTheme.colors.surfaceMenu)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .clickable(enabled = activeButton) {
                                            scope.launch(Dispatchers.IO) {
                                                val zone = if (zoneTextValue.text.isEmpty()) {
                                                    Integer.MIN_VALUE
                                                } else zoneTextValue.text.toIntOrZero()
                                                val value = valueTextValue.text.toFloatOrZero()
                                                val result =
                                                    viewModel.setPropertyFloatValue(
                                                        property.value,
                                                        zone,
                                                        value
                                                    )
                                                // zoneTextValue = TextFieldValue("")
                                                valueTextValue = TextFieldValue("")
//                                                delay(100L)
//                                                valueUpdater += 1

                                                Timber.d("Apply fun value: $value with result $result")
                                                withContext(Dispatchers.Main) {
                                                    context.toast(if (result) "SUCCESS" else "FAIL")
                                                }
                                            }
                                        }
                                        .weight(1f)
                                        .padding(
                                            horizontal = 4.dp,
                                            vertical = 14.dp
                                        ),
                                    text = stringResource(R.string.set_float),
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.buttonTitle,
                                    textAlign = TextAlign.Center,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )

                                Spacer(
                                    Modifier
                                        .fillMaxHeight()
                                        .width(1.dp)
                                        .padding(vertical = 10.dp)
                                        .background(AppTheme.colors.contentPrimary.copy(.2f))
                                )

                                Text(
                                    modifier = Modifier
                                        .clickable(
                                            enabled = activeButton,
                                            onClick = ::exportSetAsFloat
                                        )
                                        .padding(
                                            horizontal = 24.dp,
                                            vertical = 14.dp
                                        ),
                                    text = stringResource(R.string.in_md),
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.buttonTitle,
                                    textAlign = TextAlign.Center,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
//                                Row(
//                                    modifier = Modifier
//                                        .clickable(
//                                            enabled = activeButton,
//                                            onClick = ::exportAsFloat
//                                        )
//                                        .padding(horizontal = 28.dp)
//                                        .fillMaxHeight(),
//                                    verticalAlignment = Alignment.CenterVertically
//                                ) {
//
//                                    Icon(
//                                        imageVector = Icons.Filled.Share,
//                                        tint = AppTheme.colors.contentPrimary,
//                                        contentDescription = stringResource(R.string.back)
//                                    )
//                                }
                            }

                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(IntrinsicSize.Min)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (activeButton) AppTheme.colors.addSplitTop else AppTheme.colors.surfaceMenu)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .clickable(enabled = activeButton) {
                                            scope.launch(Dispatchers.IO) {
                                                val zone = if (zoneTextValue.text.isEmpty()) {
                                                    Integer.MIN_VALUE
                                                } else zoneTextValue.text.toIntOrZero()
                                                val value = valueTextValue.text.toIntOrZero()
                                                val result =
                                                    viewModel.setPropertyIntValue(
                                                        property.value,
                                                        zone,
                                                        value
                                                    )
                                                // zoneTextValue = TextFieldValue("")
                                                valueTextValue = TextFieldValue("")
//                                                delay(100L)
//                                                valueUpdater += 1

                                                Timber.d("Apply fun value: $value with result $result")
                                                withContext(Dispatchers.Main) {
                                                    context.toast(if (result) "SUCCESS" else "FAIL")
                                                }
                                            }
                                        }
                                        .weight(1f)
                                        .padding(
                                            horizontal = 4.dp,
                                            vertical = 14.dp
                                        ),
                                    text = stringResource(R.string.set_int),
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.buttonTitle,
                                    textAlign = TextAlign.Center,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )

                                Spacer(
                                    Modifier
                                        .fillMaxHeight()
                                        .width(1.dp)
                                        .padding(vertical = 10.dp)
                                        .background(AppTheme.colors.contentPrimary.copy(.2f))
                                )

                                Text(
                                    modifier = Modifier
                                        .clickable(
                                            enabled = activeButton,
                                            onClick = ::exportSetAsInt
                                        )
                                        .padding(
                                            horizontal = 24.dp,
                                            vertical = 14.dp
                                        ),
                                    text = stringResource(R.string.in_md),
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.buttonTitle,
                                    textAlign = TextAlign.Center,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )

//                                Row(
//                                    modifier = Modifier
//                                        .clickable(enabled = activeButton, onClick = ::exportAsInt)
//                                        .padding(horizontal = 28.dp)
//                                        .fillMaxHeight(),
//                                    verticalAlignment = Alignment.CenterVertically
//                                ) {
//
//                                    Icon(
//                                        imageVector = Icons.Filled.Share,
//                                        tint = AppTheme.colors.contentPrimary,
//                                        contentDescription = stringResource(R.string.back)
//                                    )
//                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White.copy(.1f))
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    SupportValuesText(
                        allZones = allZones,
                        supportValues = supportValues,
                        onValueClick = { zone, value ->
                            zoneTextValue = if (zone == Integer.MIN_VALUE) {
                                TextFieldValue("")
                            } else {
                                TextFieldValue(
                                    text = zone.toString(),
                                    selection = TextRange(zone.toString().length)
                                )
                            }
                            valueTextValue = TextFieldValue(
                                text = value.toString(),
                                selection = TextRange(value.toString().length)
                            )
                        }
                    )

                    // Assumed Values
                    if (property.possibleValues.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.White.copy(.1f))
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "${stringResource(R.string.assumed_values)}:",
                            modifier = Modifier.padding(horizontal = 24.dp),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.dialogSubtitle
                        )

                        val baseStyle = TextStyle(
                            color = AppTheme.colors.contentPrimary,
                            fontSize = AppTheme.typography.dialogSubtitle.fontSize,
                            lineHeight = AppTheme.typography.dialogSubtitle.lineHeight
                        )
                        val chipBg = AppTheme.colors.autoStart
                        val chipText = Color.White
                        val inlinePad = Modifier.padding(vertical = 2.dp)

                        property.possibleValues.forEach { (name, id) ->
                            Spacer(modifier = Modifier.height(8.dp))
                            FlowRow(
                                modifier = Modifier.padding(horizontal = 24.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                // /* Left part */
                                Text(text = name, style = baseStyle, modifier = inlinePad)
                                Text(text = "->", style = baseStyle, modifier = inlinePad)

                                // /* Clickable chip for the ID */
                                Text(
                                    text = id.toString(),
                                    style = baseStyle.copy(
                                        fontWeight = FontWeight.Medium,
                                        color = chipText
                                    ),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(chipBg)
                                        .clickable {
                                            // /* Apply value on click */
                                            val s = id.toString()
                                            valueTextValue = TextFieldValue(
                                                text = s,
                                                selection = TextRange(s.length)
                                            )
                                        }
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    if (property.type in listOf(IdType.ID_TYPE_SENSOR, IdType.ID_TYPE_FUNCTION)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.White.copy(.1f))
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "MacroDroid:",
                            modifier = Modifier.padding(horizontal = 24.dp),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.dialogSubtitle
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        RenderAddButton(stringResource(R.string.create_int_macro), ::exportGetAsInt)

                        Spacer(modifier = Modifier.height(16.dp))

                        RenderAddButton(
                            stringResource(R.string.create_float_macro),
                            ::exportGetAsFloat
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        RenderAddButton(
                            stringResource(R.string.create_int_listener),
                            ::exportListenAsInt
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        RenderAddButton(
                            stringResource(R.string.create_float_listener),
                            ::exportListenAsFloat
                        )

                        if (property.type == IdType.ID_TYPE_SENSOR) {
                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "* ${stringResource(R.string.sensor_listeners_disclaimer)}",
                                modifier = Modifier.padding(horizontal = 24.dp),
                                color = AppTheme.colors.contentPrimary.copy(.7f),
                                style = AppTheme.typography.idTitle
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White.copy(.1f))
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "${stringResource(R.string.public_notes)}:",
                        modifier = Modifier.padding(horizontal = 24.dp),
                        color = AppTheme.colors.contentPrimary,
                        style = AppTheme.typography.dialogSubtitle
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // update firestore logic
                    val noteKey = remember { "${property.value}_${property.type}" }
                    var noteUpdateRequest by remember { mutableIntStateOf(0) }
                    var noteStatus by remember { mutableStateOf(NoteStatus.LOADING) }
                    var noteFieldText by remember { mutableStateOf(TextFieldValue("")) }
                    var loadedNote by remember { mutableStateOf("") }
                    val canSaveNote by remember { derivedStateOf { loadedNote != noteFieldText.text } }
                    var inNoteSaving by remember { mutableStateOf(false) }

                    LaunchedEffect(noteUpdateRequest) {
                        noteStatus = NoteStatus.LOADING

                        when (val r = getLatestFirestoreValue(context, noteKey, serverOnly = true)) {
                            is KvResult.Success -> {
                                val text = r.value
                                // val offline = r.fromCache && !r.hasPendingWrites
                                noteFieldText = noteFieldText.copy(text = text)
                                loadedNote = text
                                noteStatus = NoteStatus.READY
                            }

                            is KvResult.NotFound -> {
                                noteFieldText = noteFieldText.copy(text = "")
                                loadedNote = ""
                                noteStatus = NoteStatus.READY
                            }

                            is KvResult.Failure -> {
                                noteStatus = NoteStatus.ERROR
                            }
                        }
                    }

                    suspend fun saveNote() {
                        when (saveFirestoreValue(context, noteKey, noteFieldText.text)) {
                            is KvResult.Success -> {
                                loadedNote = noteFieldText.text
                                inNoteSaving = false
                                context.toast(context.getString(R.string.changes_saved))
                            }

                            is KvResult.Failure -> {
                                inNoteSaving = false
                                context.toast(context.getString(R.string.save_failed))
                            }

                            else -> Unit
                        }
                    }

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(360.dp)
                            .padding(horizontal = 20.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(
                                Color.White.copy(
                                    if (noteStatus == NoteStatus.READY) .05f else .03f
                                )
                            )
                    ) {
                        when (noteStatus) {
                            NoteStatus.LOADING -> CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(48.dp),
                                color = AppTheme.colors.contentAccent
                            )

                            NoteStatus.READY -> BasicTextField(
                                value = noteFieldText,
                                onValueChange = { noteFieldText = it },
                                cursorBrush = SolidColor(
                                    AppTheme.colors.contentPrimary.copy(
                                        alpha = .9f
                                    )
                                ),
                                textStyle = AppTheme.typography.dialogSubtitle.copy(
                                    color = AppTheme.colors.contentPrimary
                                ),
                                decorationBox = { innerTextField ->
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .padding(horizontal = 16.dp, vertical = 14.dp)
                                    ) {
                                        if (noteFieldText.text.isEmpty()) {
                                            Text(
                                                modifier = Modifier
                                                    .then(
                                                        if (canSaveNote) {
                                                            Modifier.padding(end = 56.dp)
                                                        } else Modifier
                                                    ),
                                                text = stringResource(R.string.public_notes_desc),
                                                color = AppTheme.colors.contentPrimary.copy(.6f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        innerTextField()
                                    }
                                },
                                keyboardActions = KeyboardActions(onDone = {
                                    defaultKeyboardAction(ImeAction.Default)
                                }),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Default,
                                    capitalization = KeyboardCapitalization.None,
                                    keyboardType = KeyboardType.Text,
                                    autoCorrectEnabled = false
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )

                            NoteStatus.ERROR -> Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth()
                                    .padding(horizontal = 46.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.data_fetch_failed),
                                    color = AppTheme.colors.contentPrimary.copy(.6f),
                                    style = AppTheme.typography.dialogSubtitle
                                )
                                Spacer(Modifier.height(16.dp))

                                Box(
                                    modifier = Modifier
                                        .padding(top = 16.dp, end = 16.dp)
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(AppTheme.colors.contentAccent.copy(.8f))
                                        .clickable {
                                            noteUpdateRequest++
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Refresh,
                                        tint = AppTheme.colors.contentPrimary,
                                        contentDescription = "refresh",
                                        modifier = Modifier.padding(2.dp)
                                    )
                                }
                            }
                        }

                        // Save button
                        if (canSaveNote) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 16.dp, end = 16.dp)
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(AppTheme.colors.contentAccent.copy(.6f))
                                    .clickable {
                                        if (inNoteSaving) return@clickable
                                        inNoteSaving = true
                                        scope.launch { saveNote() }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (inNoteSaving) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .size(19.dp),
                                        strokeWidth = 2.dp,
                                        color = AppTheme.colors.contentPrimary
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Filled.Done,
                                        tint = AppTheme.colors.contentPrimary,
                                        contentDescription = "clear",
                                        modifier = Modifier
                                            .padding(6.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "* ${stringResource(R.string.public_notes_info)}",
                        modifier = Modifier.padding(horizontal = 24.dp),
                        color = AppTheme.colors.contentPrimary.copy(.7f),
                        style = AppTheme.typography.idTitle
                    )

                    Spacer(modifier = Modifier.height(BOTTOM_OFFSET.dp))
                }
            }
        }
    }
}

@Composable
private fun RenderAddButton(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .background(Color.White.copy(.08f)),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(22.dp),
            imageVector = Icons.Filled.AddCircle,
            tint = AppTheme.colors.contentPrimary,
            contentDescription = "export"
        )

        Text(
            modifier = Modifier
                .padding(vertical = 14.dp),
            text = title,
            color = AppTheme.colors.contentPrimary,
            style = AppTheme.typography.sourceType,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Suppress("DEPRECATION")
@Composable
private fun SupportValuesText(
    allZones: Map<Int, String>,
    supportValues: Map<Int, Set<Int>>,
    modifier: Modifier = Modifier,
    onValueClick: (zone: Int, value: Int) -> Unit
) {
    val grayColor = AppTheme.colors.contentPrimary.copy(alpha = 0.4f)
    val chipBg = AppTheme.colors.autoStart
    val chipText = Color.White

    // Use exactly your text style
    val baseStyle = TextStyle(
        color = AppTheme.colors.contentPrimary,
        fontSize = AppTheme.typography.dialogSubtitle.fontSize,
        lineHeight = AppTheme.typography.dialogSubtitle.lineHeight
    )

    // Small vertical pad applied to all inline items so heights align nicely with chips
    val inlinePad = Modifier.padding(vertical = 2.dp)

    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        // Header line (as in original)
        Text(
            text = "${stringResource(R.string.system_values)}:",
            style = baseStyle
        )

        if (supportValues.isEmpty()) {
            Spacer(Modifier.height(4.dp))
            Text(text = stringResource(R.string.unknown), style = baseStyle.copy(color = grayColor))
            return@Column
        }

        Spacer(Modifier.height(4.dp))

        // Render each zone on its own "line"
        val zones = supportValues.entries.toList() // keep incoming order
        zones.forEachIndexed { zIdx, (zone, valuesSet) ->

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Left part: "<zone> ->"
                if (zone == Int.MIN_VALUE) {
                    Text(
                        text = (allZones[zone] ?: stringResource(R.string.unknown)),
                        style = baseStyle,
                        modifier = inlinePad
                    )
                    Text(text = "->", style = baseStyle, modifier = inlinePad)
                } else {
                    Text(
                        text = allZones.getOrDefault(zone, stringResource(R.string.unknown)),
                        style = baseStyle,
                        modifier = inlinePad
                    )
                    Text(
                        text = "[ id:",
                        style = baseStyle.copy(color = grayColor),
                        modifier = inlinePad
                    )
                    Text(text = zone.toString(), style = baseStyle, modifier = inlinePad)
                    Text(
                        text = "]",
                        style = baseStyle.copy(color = grayColor),
                        modifier = inlinePad
                    )
                    Text(text = "->", style = baseStyle, modifier = inlinePad)
                }

                // Values: "[ v1, v2, ... ]"
                if (valuesSet.isEmpty()) {
                    Text(
                        text = "[",
                        style = baseStyle.copy(color = grayColor),
                        modifier = inlinePad
                    )
                    Text(
                        text = stringResource(R.string.unknown),
                        style = baseStyle.copy(color = grayColor),
                        modifier = inlinePad
                    )
                    Text(
                        text = "]",
                        style = baseStyle.copy(color = grayColor),
                        modifier = inlinePad
                    )
                } else {
                    Text(
                        text = "[",
                        style = baseStyle.copy(color = grayColor),
                        modifier = inlinePad
                    )

                    val values = valuesSet.toList().sorted()
                    values.forEachIndexed { idx, v ->
                        // Entire chip is clickable and baseline-aligned
                        Text(
                            text = v.toString(),
                            style = baseStyle.copy(
                                fontWeight = FontWeight.Medium,
                                color = chipText
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(chipBg)
                                .clickable { onValueClick(zone, v) }
                                .padding(horizontal = 8.dp, vertical = 2.dp) // chip paddings
                        )

                        if (idx < values.lastIndex) {
                            Text(
                                text = ",",
                                style = baseStyle.copy(color = grayColor),
                                modifier = inlinePad
                            )
                        }
                    }

                    Text(
                        text = "]",
                        style = baseStyle.copy(color = grayColor),
                        modifier = inlinePad
                    )
                }
            }

            // Blank line between zones (like original "\n")
            if (zIdx < zones.lastIndex) {
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

private fun String.toFloatOrZero(): Float =
    this
        .trim()
        .replace(',', '.')
        .toFloatOrNull()
        ?: 0f

private fun String.toIntOrZero(): Int {
    val s = trim().replace(',', '.')
    val intPart = s.substringBefore('.')
    return intPart.toIntOrNull() ?: 0
}

private fun Int.canEditProperty() = when (this) {
    IdType.ID_TYPE_FUNCTION -> true
    else -> false
}

private fun readJsonFromRaw(context: Context, @RawRes resId: Int = R.raw.md_send_intent): String =
    context.resources.openRawResource(resId).bufferedReader().use { it.readText() }

private fun String.tailAfterLastDot(): String =
    this.substringAfterLast('.', this)
