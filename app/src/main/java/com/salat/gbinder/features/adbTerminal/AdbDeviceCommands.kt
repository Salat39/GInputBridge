package com.salat.gbinder.features.adbTerminal

private const val MAX_DEVICE_COMMANDS = 2000
private const val MAX_COMMAND_LENGTH = 40

private val WHITESPACE = Regex("""\s+""")

internal val DEVICE_BINARY_DIRS = listOf("/system/bin", "/vendor/bin")

// The -1 flag is needed because the shell can run under a pty, where ls prints columns
internal fun listBinariesCommand(dir: String) = "ls -1 $dir"

// Splits on any whitespace - it keeps the column output of ls readable too
internal fun parseBinaryNames(output: String): List<String> =
    output.split(WHITESPACE)
        .filter { name ->
            name.isNotEmpty() && name.length <= MAX_COMMAND_LENGTH &&
                    name.all { it.isLetterOrDigit() || it == '_' || it == '-' || it == '.' }
        }
        .take(MAX_DEVICE_COMMANDS)

internal const val LIST_PACKAGES_COMMAND = "pm list packages"

// Every line of pm list packages looks like package:com.example
internal fun parsePackageNames(output: String): List<String> =
    output.lineSequence()
        .map { it.trim().removePrefix("package:") }
        .filter { name -> name.isNotEmpty() && name.none { it.isWhitespace() } && '.' in name }
        .distinct()
        .take(MAX_DEVICE_COMMANDS)
        .sorted()
        .toList()

internal const val LIST_PERMISSIONS_COMMAND = "pm list permissions"

// Lines look like permission:android.permission.CAMERA, headings have no such prefix
internal fun parsePermissionNames(output: String): List<String> =
    output.lineSequence()
        .map { it.trim() }
        .filter { it.startsWith("permission:") }
        .map { it.removePrefix("permission:") }
        .filter { it.isNotEmpty() }
        .distinct()
        .take(MAX_DEVICE_COMMANDS)
        .sorted()
        .toList()

internal const val LIST_SERVICES_COMMAND = "cmd -l"
internal const val LIST_DUMPSYS_SERVICES_COMMAND = "dumpsys -l"

// Both list one service per line, sometimes under a heading line with spaces in it
internal fun parseServiceNames(output: String): List<String> =
    output.lineSequence()
        .map { it.trim() }
        .filter { name ->
            name.isNotEmpty() && name.none { it.isWhitespace() } && !name.endsWith(":")
        }
        .distinct()
        .take(MAX_DEVICE_COMMANDS)
        .sorted()
        .toList()

internal const val ROOT_DIR = "/"

private val SHELL_OPERATORS = listOf("&&", "||", ";", "|")

internal fun isChangeDirectory(command: String): Boolean =
    (command == "cd" || command.startsWith("cd ")) && SHELL_OPERATORS.none { it in command }

/**
 * The adb transport opens a new shell for every command, so a plain cd is lost as soon as it
 * returns. The terminal keeps the directory itself and enters it before each command, which
 * makes relative paths work the same way on both transports.
 */
internal fun shellCommand(command: String, workingDir: String): String {
    val enter = "cd ${singleQuoted(workingDir)} && "
    return when {
        // Bare cd has no home directory to fall back on here
        command == "cd" -> "${enter}cd $ROOT_DIR && pwd"
        isChangeDirectory(command) -> "$enter${command.trimEnd()} && pwd"
        else -> enter + command
    }
}

private fun singleQuoted(value: String) = "'" + value.replace("'", "'\\''") + "'"
