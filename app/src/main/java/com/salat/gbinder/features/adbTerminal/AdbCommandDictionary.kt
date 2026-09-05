package com.salat.gbinder.features.adbTerminal

internal val ADB_COMMAND_DICTIONARY = listOf(
    CommandToken("getprop", "read system properties"),
    CommandToken("getprop ro.build.id", "build id of the firmware"),
    CommandToken("getprop ro.product.model", "model name of the device"),
    CommandToken("pm list packages", "installed packages"),
    CommandToken("pm list packages -d", "only disabled packages"),
    CommandToken("pm list packages -3", "only third party packages"),
    CommandToken("pm path <pkg>", "apk path of a package"),
    CommandToken("pm enable <pkg>", "enable a package"),
    CommandToken("pm disable-user --user 0 <pkg>", "disable a package for the primary user"),
    CommandToken("pm clear <pkg>", "erase package data"),
    CommandToken("am start --user 0 -n <pkg>/<act>", "start an activity by component"),
    CommandToken("am force-stop --user 0 <pkg>", "stop everything of a package"),
    CommandToken("am stack remove <id>", "remove a task stack by id"),
    CommandToken("monkey -p <pkg> 1", "send one random event to a package"),
    CommandToken("dumpsys activity activities", "activity records and tasks"),
    CommandToken("dumpsys window windows", "window records"),
    CommandToken("dumpsys package <pkg>", "package manager record"),
    CommandToken("dumpsys battery", "battery service state"),
    CommandToken("dumpsys deviceidle", "doze state"),
    CommandToken("settings get system <key>", "read one system key"),
    CommandToken("settings put system <key> <value>", "write one system key"),
    CommandToken("settings list system", "list the system namespace"),
    CommandToken("input keyevent KEYCODE_HOME", "send the home key"),
    CommandToken("input keyevent KEYCODE_BACK", "send the back key"),
    CommandToken("content query --uri <uri>", "read rows of a content provider"),
    CommandToken("ls /sdcard", "list the shared storage"),
    CommandToken("df -h", "free disk space")
)

internal val DICTIONARY_HINTS: Map<String, String> =
    ADB_COMMAND_DICTIONARY.associate { it.text to it.hint }
private val COMMAND_PREFIXES = listOf("adb ", "shell ", "$ ", "# ")

// Cuts only the leading prompt and client prefixes - quotes and pipes stay as typed
internal fun normalizeCommand(input: String): String = stripPrefixes(input).trim()

// Same rule for the suggestion menu, but the trailing space survives: it is what tells the
// grammar that the command is finished and the next token is being typed
internal fun normalizeInput(input: String): String = stripPrefixes(input)

private fun stripPrefixes(input: String): String {
    var value = input.trimStart()
    while (true) {
        val prefix = COMMAND_PREFIXES.firstOrNull { value.startsWith(it) } ?: break
        value = value.removePrefix(prefix).trimStart()
    }
    return value
}

// A suggestion is a starting point - drop its placeholders and leave the caret after a space
internal fun suggestionInput(command: String): String =
    command.substringBefore('<').trimEnd() + " "
