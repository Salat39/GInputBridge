package com.salat.gbinder.features.adbTerminal

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.salat.gbinder.R
import com.salat.gbinder.adb.data.entity.AdbCommandResult
import com.salat.gbinder.components.shareTextAsLogFile
import com.salat.gbinder.ui.TopShadow
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

// The menu holds more than it shows - the rest is reached by scrolling
private const val MAX_SUGGESTIONS = 30
private const val VISIBLE_SUGGESTIONS = 7
private val SUGGESTION_ROW_HEIGHT = 44.dp

// The field and the run button must share one height
private val INPUT_ROW_HEIGHT = 56.dp

// The log is a console surface, so it keeps its own colours in both themes: the theme content
// colour is almost black in the light theme and would disappear on this background.
// The value is the deepest surface of the dark palette, not pure black - against the page
// background it reads as a recessed console instead of a hard hole
private val CONSOLE_BACKGROUND = Color(0xFF121212)
private val CONSOLE_FOREGROUND = Color(0xFFE5E5E5)
private val CONSOLE_FADE_HEIGHT = 24.dp

// One console size for the whole log - the command must not tower over its own output
private val LOG_FONT_SIZE = 13.sp
private val LOG_LINE_HEIGHT = 18.sp

private val INPUT_FONT_SIZE = 17.sp
private const val MIN_SUGGESTION_INPUT = 1

private enum class RowKind { COMMAND, STATUS, OUTPUT, NOTE, GAP }

@Immutable
private data class TerminalRow(
    val key: String,
    val text: String,
    val kind: RowKind
)

@Composable
fun RenderAdbTerminalScreen(
    uiScaleState: Float? = null,
    onClose: () -> Unit
) {
    val viewModel: AdbTerminalViewModel = hiltViewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val blocks by viewModel.blocks.collectAsStateWithLifecycle()
    val running by viewModel.running.collectAsStateWithLifecycle()
    val recent by viewModel.recent.collectAsStateWithLifecycle()
    val deviceCommands by viewModel.deviceCommands.collectAsStateWithLifecycle()
    val device by viewModel.device.collectAsStateWithLifecycle()

    var input by remember { mutableStateOf(TextFieldValue()) }
    var showSuggestions by remember { mutableStateOf(false) }
    // A tap in the empty field, or its arrow, opens every command found on the device
    var browsing by remember { mutableStateOf(false) }

    fun setInput(text: String, keepMenu: Boolean) {
        input = TextFieldValue(text, TextRange(text.length))
        showSuggestions = keepMenu
        browsing = false
    }

    // Keeps the menu open, so pm gives list, list gives packages, packages gives the flags
    fun applySuggestion(text: String) = setInput(text, keepMenu = true)

    fun substitute(command: String) = setInput(command, keepMenu = false)

    val rows = remember(blocks) { buildRows(context, blocks) }

    val suggestions = remember(input.text, recent, deviceCommands, device, showSuggestions, browsing) {
        when {
            browsing -> browseSuggestions(deviceCommands)
            // Prefixes like adb shell are dropped before matching, the same way the executed
            // command drops them - otherwise nothing matches while the user types one
            showSuggestions -> buildSuggestions(normalizeInput(input.text), recent, deviceCommands, device)
            else -> emptyList()
        }
    }

    val menuOpen = suggestions.isNotEmpty()

    var fieldBounds by remember { mutableStateOf(Rect.Zero) }
    var workAreaOrigin by remember { mutableStateOf(Offset.Zero) }

    LaunchedEffect(Unit) { viewModel.harvestDeviceCommands() }

    BackHandler {
        if (menuOpen) {
            showSuggestions = false
            browsing = false
        } else {
            onClose()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.ime.exclude(WindowInsets.navigationBars))
    ) {
        RenderToolbar(
            onShare = {
                scope.launch {
                    val text = withContext(Dispatchers.Default) {
                        buildShareText(context, blocks)
                    }
                    if (text.isBlank()) return@launch
                    context.shareTextAsLogFile(text, "terminal")
                }
            },
            onClear = viewModel::clearOutput,
            onClose = onClose
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(AppTheme.colors.lampBackground.copy(.3f))
                .onGloballyPositioned { workAreaOrigin = it.boundsInRoot().topLeft }
        ) {
            TopShadow()

            Column(Modifier.fillMaxSize()) {
                Spacer(Modifier.height(16.dp))

                RenderInputRow(
                    input = input,
                    running = running,
                    menuOpen = menuOpen,
                    onFieldPositioned = { fieldBounds = it },
                    onFieldTap = {
                        if (input.text.isEmpty()) browsing = true else showSuggestions = true
                    },
                    onInputChange = {
                        input = it
                        showSuggestions = true
                        browsing = false
                    },
                    onClearInput = {
                        input = TextFieldValue()
                        showSuggestions = false
                    },
                    onBrowse = { browsing = !browsing },
                    onRun = {
                        viewModel.execute(input.text)
                        input = TextFieldValue()
                        browsing = false
                    },
                    onStop = viewModel::stop
                )

                Spacer(Modifier.height(12.dp))

                RenderHistory(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    rows = rows,
                    blocksCount = blocks.size,
                    onSubstitute = ::substitute
                )

                Spacer(Modifier.height(24.dp))
            }

            // Floats over the history - the log area keeps its size while the menu is open
            if (menuOpen) {
                RenderSuggestions(
                    modifier = Modifier.align(Alignment.TopStart),
                    anchor = fieldBounds.translate(-workAreaOrigin),
                    suggestions = suggestions,
                    onSuggestion = ::applySuggestion
                )
            }
        }
    }
}

@Composable
private fun RenderToolbar(
    onShare: () -> Unit,
    onClear: () -> Unit,
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // No size modifier on IconButton - its own 48dp box keeps the ripple round and centred
        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = AppTheme.colors.contentPrimary,
                contentDescription = stringResource(R.string.back)
            )
        }

        Spacer(Modifier.width(12.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.adb_terminal),
            style = AppTheme.typography.stubTitle,
            color = AppTheme.colors.contentPrimary
        )

        IconButton(onClick = onShare) {
            Icon(
                // The share glyph fills its viewport more than the others
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Share,
                tint = AppTheme.colors.contentPrimary,
                contentDescription = "share"
            )
        }

        IconButton(onClick = onClear) {
            Icon(
                modifier = Modifier.size(21.dp),
                painter = painterResource(R.drawable.ic_delete),
                tint = AppTheme.colors.contentPrimary,
                contentDescription = stringResource(R.string.clear)
            )
        }
    }
}

@Composable
private fun RenderHistory(
    modifier: Modifier,
    rows: List<TerminalRow>,
    blocksCount: Int,
    onSubstitute: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(CONSOLE_BACKGROUND)
    ) {
        if (rows.isEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp),
                text = stringResource(R.string.adb_terminal_empty),
                style = AppTheme.typography.screenTitle,
                textAlign = TextAlign.Center,
                color = CONSOLE_FOREGROUND.copy(.22f)
            )
            return@Box
        }

        val listState = rememberLazyListState()

        val logStyle = AppTheme.typography.dialogSubtitle.copy(
            fontFamily = FontFamily.Monospace,
            fontSize = LOG_FONT_SIZE,
            lineHeight = LOG_LINE_HEIGHT,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )

        // The newest block sits at the top, so a new command returns the view to its output
        LaunchedEffect(blocksCount) { listState.scrollToItem(0) }

        SelectionContainer(Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                item(key = -1) { Spacer(Modifier.height(16.dp)) }

                items(items = rows, key = { it.key }) { row ->
                    when (row.kind) {
                        RowKind.GAP -> Spacer(Modifier.height(16.dp))

                        // The whole line is the button - a tap puts the command back
                        // in the field. Order matters: outer margin, then the shape the
                        // ripple is bound to, then the padding inside it
                        RowKind.COMMAND -> Text(
                            modifier = Modifier
                                .padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 2.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onSubstitute(row.text) }
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            text = "$ ${row.text}",
                            style = logStyle,
                            color = AppTheme.colors.contentLightAccent
                        )

                        RowKind.STATUS -> Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 2.dp),
                            text = row.text,
                            style = logStyle,
                            color = AppTheme.colors.warning
                        )

                        RowKind.NOTE -> Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 2.dp),
                            text = row.text,
                            style = logStyle,
                            color = CONSOLE_FOREGROUND.copy(.4f)
                        )

                        RowKind.OUTPUT -> Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp),
                            text = row.text,
                            style = logStyle,
                            color = CONSOLE_FOREGROUND
                        )
                    }
                }

                item(key = -2) { Spacer(Modifier.height(16.dp)) }
            }
        }

        // Lines leave the console through a fade instead of a hard cut, only where
        // there is more text beyond that edge
        ConsoleEdgeFade(
            modifier = Modifier.align(Alignment.TopCenter),
            visible = listState.canScrollBackward,
            invert = false
        )
        ConsoleEdgeFade(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = listState.canScrollForward,
            invert = true
        )
    }
}

@Composable
private fun ConsoleEdgeFade(modifier: Modifier, visible: Boolean, invert: Boolean) {
    val alpha by animateFloatAsState(if (visible) 1f else 0f, label = "consoleEdgeFade")
    val colors = listOf(CONSOLE_BACKGROUND, Color.Transparent)
    Box(
        modifier
            .fillMaxWidth()
            .height(CONSOLE_FADE_HEIGHT)
            .alpha(alpha)
            .background(Brush.verticalGradient(if (invert) colors.asReversed() else colors))
    )
}

private fun Modifier.drawVerticalScrollbar(state: ScrollState, color: Color): Modifier =
    drawWithContent {
        drawContent()
        if (state.maxValue <= 0) return@drawWithContent

        val inset = 6.dp.toPx()
        val thumbWidth = 3.dp.toPx()
        val trackHeight = size.height - inset * 2
        val thumbHeight = (trackHeight * size.height / (size.height + state.maxValue))
            .coerceAtLeast(16.dp.toPx())
        val thumbTop = inset + (trackHeight - thumbHeight) * state.value / state.maxValue

        drawRoundRect(
            color = color,
            topLeft = Offset(size.width - thumbWidth - 4.dp.toPx(), thumbTop),
            size = Size(thumbWidth, thumbHeight),
            cornerRadius = CornerRadius(thumbWidth / 2)
        )
    }

@Composable
private fun RenderSuggestions(
    modifier: Modifier,
    anchor: Rect,
    suggestions: List<AdbSuggestion>,
    onSuggestion: (String) -> Unit
) {
    val shape = RoundedCornerShape(10.dp)
    val density = LocalDensity.current
    val scrollState = rememberScrollState()

    // A picked suggestion gives a new list - it must open from its first row, with no animation
    LaunchedEffect(suggestions) { scrollState.scrollTo(0) }

    Column(
        modifier = modifier
            // Hangs under the input field with the same left edge and width
            .offset {
                IntOffset(anchor.left.roundToInt(), (anchor.bottom + 4.dp.toPx()).roundToInt())
            }
            .width(with(density) { anchor.width.toDp() })
            .shadow(elevation = 24.dp, shape = shape)
            // Opaque so the output does not show through, and still a theme tone, not black.
            // One step above the page background is enough to float: the console card under
            // the menu is darker than the page, so the layers stay apart without going bright
            .background(AppTheme.colors.surfaceBackground, shape)
            .background(AppTheme.colors.contentPrimary.copy(.08f), shape)
            // Stops taps from reaching the log list under the menu
            .clickableNoRipple { }
            .heightIn(max = SUGGESTION_ROW_HEIGHT * VISIBLE_SUGGESTIONS)
            // Before verticalScroll, so the thumb is drawn in viewport coordinates
            .drawVerticalScrollbar(scrollState, AppTheme.colors.contentPrimary.copy(.3f))
            .verticalScroll(scrollState)
    ) {
        suggestions.forEach { suggestion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SUGGESTION_ROW_HEIGHT)
                    .clickable { onSuggestion(suggestion.input) }
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = suggestion.label,
                    style = AppTheme.typography.dialogListTitle,
                    color = AppTheme.colors.contentPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (suggestion.hint.isNotEmpty()) {
                    Spacer(Modifier.width(12.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = suggestion.hint,
                        style = AppTheme.typography.dialogSubtitle,
                        color = AppTheme.colors.contentPrimary.copy(.4f),
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun RenderInputRow(
    input: TextFieldValue,
    running: Boolean,
    menuOpen: Boolean,
    onFieldPositioned: (Rect) -> Unit,
    onFieldTap: () -> Unit,
    onInputChange: (TextFieldValue) -> Unit,
    onClearInput: () -> Unit,
    onBrowse: () -> Unit,
    onRun: () -> Unit,
    onStop: () -> Unit
) {
    Box(Modifier.padding(horizontal = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val fieldStyle = AppTheme.typography.dialogListTitle
            val interactionSource = remember { MutableInteractionSource() }
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) onFieldTap()
                }
            }
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .onGloballyPositioned { onFieldPositioned(it.boundsInRoot()) }
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppTheme.colors.contentPrimary.copy(.05f)),
                value = input,
                onValueChange = onInputChange,
                interactionSource = interactionSource,
                cursorBrush = SolidColor(AppTheme.colors.contentAccent),
                textStyle = fieldStyle.copy(
                    color = AppTheme.colors.contentPrimary,
                    fontFamily = FontFamily.Monospace,
                    fontSize = INPUT_FONT_SIZE
                ),
                decorationBox = { innerTextField ->
                    Row(
                        // Fixed height - the clear button must not resize the field
                        modifier = Modifier
                            .height(INPUT_ROW_HEIGHT)
                            .padding(start = 14.dp, end = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (input.text.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.adb_terminal_hint),
                                    style = fieldStyle.copy(
                                        color = AppTheme.colors.contentPrimary.copy(.4f),
                                        fontSize = INPUT_FONT_SIZE
                                    )
                                )
                            }
                            innerTextField()
                        }

                        val empty = input.text.isEmpty()
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .clickable(onClick = if (empty) onBrowse else onClearInput),
                            contentAlignment = Alignment.Center
                        ) {
                            if (empty) {
                                val chevronRotation by animateFloatAsState(
                                    if (menuOpen) 180f else 0f,
                                    label = "browseChevron"
                                )
                                // Same stroke and size as the clear icon, so the two swap in place
                                Icon(
                                    modifier = Modifier
                                        .size(17.dp)
                                        .rotate(chevronRotation),
                                    painter = painterResource(R.drawable.ic_chevron_down),
                                    tint = AppTheme.colors.contentPrimary.copy(.45f),
                                    contentDescription = "browse commands"
                                )
                            } else {
                                Icon(
                                    modifier = Modifier.size(17.dp),
                                    imageVector = Icons.Filled.Close,
                                    tint = AppTheme.colors.contentPrimary.copy(.45f),
                                    contentDescription = "clear input"
                                )
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Go,
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Text,
                    autoCorrectEnabled = false
                ),
                keyboardActions = KeyboardActions(onGo = { if (!running) onRun() }),
                singleLine = true
            )

            Spacer(Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .size(INPUT_ROW_HEIGHT)
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppTheme.colors.contentAccent)
                    .clickable { if (running) onStop() else onRun() },
                contentAlignment = Alignment.Center
            ) {
                // The label still names the action for accessibility
                val action = stringResource(
                    if (running) R.string.adb_terminal_stop else R.string.adb_terminal_execute
                )
                if (running) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp)
                            .semantics { contentDescription = action },
                        color = Color.White,
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_enter),
                        tint = AppTheme.colors.contentPrimary,
                        contentDescription = action
                    )
                }
            }
        }
    }
}

private fun browseSuggestions(deviceCommands: List<String>): List<AdbSuggestion> =
    deviceCommands.map {
        AdbSuggestion(label = it, hint = commandHint(it), input = suggestionInput(it))
    }

private fun buildSuggestions(
    input: String,
    recent: List<String>,
    deviceCommands: List<String>,
    device: DeviceLists
): List<AdbSuggestion> {
    // What may follow the command already typed wins over another command name
    val context = contextSuggestions(input, device, MAX_SUGGESTIONS)
    if (context.isNotEmpty()) return context

    val query = input.trim()
    if (query.length < MIN_SUGGESTION_INPUT) return emptyList()

    val known = (GRAMMAR_COMMANDS.asSequence() + ADB_COMMAND_DICTIONARY.asSequence())
        .filter { it.text.contains(query, ignoreCase = true) && it.text != query }
    val named = (recent.asSequence() + deviceCommands.asSequence())
        .filter { it.contains(query, ignoreCase = true) && it != query }
        .map { CommandToken(it, commandHint(it)) }

    return (known + named)
        .distinctBy { it.text }
        .sortedWith(
            // A command that starts with the query beats one that only contains it, and the
            // shortest match wins - svc must come before svc wifi, whatever the source
            compareBy(
                { !it.text.startsWith(query, ignoreCase = true) },
                { it.text.length }
            )
        )
        .take(MAX_SUGGESTIONS)
        .map {
            AdbSuggestion(label = it.text, hint = it.hint, input = suggestionInput(it.text))
        }
        .toList()
}



private fun buildRows(
    context: Context,
    blocks: List<AdbTerminalBlock>
): List<TerminalRow> {
    val rows = ArrayList<TerminalRow>()
    // Newest block first - the fresh output stays in view above the keyboard
    blocks.asReversed().forEachIndexed { index, block ->
        if (index > 0) rows += TerminalRow("${block.id}:g", "", RowKind.GAP)
        rows += blockRows(context, block)
    }
    return rows
}

private fun blockRows(context: Context, block: AdbTerminalBlock): List<TerminalRow> {
    val rows = ArrayList<TerminalRow>()
    rows += TerminalRow("${block.id}:c", block.command, RowKind.COMMAND)

    statusTextFor(context, block)?.let {
        rows += TerminalRow("${block.id}:s", it, RowKind.STATUS)
    }

    // A transport failure has no exit code - its message is the whole answer
    val outputKind = if (block.exitCode == AdbCommandResult.EXIT_TRANSPORT_ERROR) {
        RowKind.STATUS
    } else {
        RowKind.OUTPUT
    }
    block.lines.forEachIndexed { line, text ->
        rows += TerminalRow("${block.id}:o$line", text, outputKind)
    }

    if (block.totalLines > block.lines.size) {
        rows += TerminalRow(
            "${block.id}:t",
            context.getString(
                R.string.adb_terminal_truncated,
                block.lines.size,
                block.totalLines
            ),
            RowKind.NOTE
        )
    }
    return rows
}

private fun statusTextFor(context: Context, block: AdbTerminalBlock): String? = when (block.exitCode) {
    0 -> null
    AdbCommandResult.EXIT_TIMEOUT -> "● " + context.getString(R.string.adb_terminal_timeout)
    AdbCommandResult.EXIT_CANCELLED -> "● " + context.getString(R.string.adb_terminal_cancelled)
    AdbCommandResult.EXIT_TRANSPORT_ERROR -> null
    else -> "● exit ${block.exitCode}"
}

// The shared log stays in the order the commands ran, oldest first
private fun buildShareText(context: Context, blocks: List<AdbTerminalBlock>): String {
    return blocks.joinToString(separator = "\n\n") { block ->
        blockRows(context, block).joinToString(separator = "\n") { row ->
            if (row.kind == RowKind.COMMAND) "$ ${row.text}" else row.text
        }
    }
}

