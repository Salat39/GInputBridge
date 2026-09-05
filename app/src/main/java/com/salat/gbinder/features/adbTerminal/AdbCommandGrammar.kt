package com.salat.gbinder.features.adbTerminal

import androidx.compose.runtime.Immutable

@Immutable
internal data class CommandToken(val text: String, val hint: String = "")

/** Where the positional argument of a command comes from, when it is not a fixed word. */
internal enum class ArgumentSource { PACKAGE, PERMISSION, SERVICE, DUMPSYS_SERVICE }

/** Lists read from the device, so the menu offers what this head unit really has. */
@Immutable
data class DeviceLists(
    val packages: List<String> = emptyList(),
    val permissions: List<String> = emptyList(),
    val services: List<String> = emptyList(),
    val dumpsysServices: List<String> = emptyList()
)

/**
 * One command prefix and what may follow it.
 *
 * [words] are positional sub commands, [options] are flags, [arguments] names the device list
 * that fills each positional slot in order - pm grant takes a package and then a permission.
 * Flags follow the reference pages of the Android platform tools.
 */
@Immutable
internal data class CommandSpec(
    val prefix: String,
    val hint: String = "",
    val words: List<CommandToken> = emptyList(),
    val options: List<CommandToken> = emptyList(),
    val arguments: List<ArgumentSource> = emptyList()
)

private val USER_OPTION = CommandToken("--user", "user id, 0 is the primary user")

internal val ADB_COMMAND_GRAMMAR: List<CommandSpec> = listOf(

    // ---------------------------------------------------------------- pm
    CommandSpec(
        prefix = "pm",
        hint = "package manager",
        words = listOf(
            CommandToken("list", "list packages, permissions, users"),
            CommandToken("path", "apk path of a package"),
            CommandToken("dump", "full package record"),
            CommandToken("dump-package", "package record only, android 12 and newer"),
            CommandToken("install", "install an apk"),
            CommandToken("uninstall", "remove a package"),
            CommandToken("clear", "erase package data"),
            CommandToken("enable", "enable a package"),
            CommandToken("disable", "disable a package"),
            CommandToken("disable-user", "disable for one user"),
            CommandToken("hide", "hide a package"),
            CommandToken("unhide", "show a hidden package"),
            CommandToken("suspend", "suspend a package"),
            CommandToken("unsuspend", "resume a package"),
            CommandToken("grant", "grant a permission"),
            CommandToken("revoke", "revoke a permission"),
            CommandToken("default-state", "reset to the shipped state"),
            CommandToken("trim-caches", "free cache space"),
            CommandToken("get-max-users", "user slots of the device"),
            CommandToken("get-max-running-users", "how many users may run at once"),
            CommandToken("install-existing", "install a package already on the device"),
            CommandToken("install-create", "open a staged install session"),
            CommandToken("install-write", "write a file into a session"),
            CommandToken("install-commit", "commit a session"),
            CommandToken("install-abandon", "drop a session"),
            CommandToken("install-remove", "remove a split from a session"),
            CommandToken("uninstall-system-updates", "roll system apps back to factory"),
            CommandToken("compile", "run the dex compiler on a package"),
            CommandToken("force-dex-opt", "compile even if it looks current"),
            CommandToken("bg-dexopt-job", "run the background compile job"),
            CommandToken("dump-profiles", "write the profile of a package"),
            CommandToken("snapshot-profile", "copy the profile of a package"),
            CommandToken("reconcile-secondary-dex-files", "check secondary dex files"),
            CommandToken("resolve-activity", "activity that handles an intent"),
            CommandToken("query-activities", "activities that handle an intent"),
            CommandToken("query-services", "services that handle an intent"),
            CommandToken("query-receivers", "receivers that handle an intent"),
            CommandToken("set-installer", "change the installer of a package"),
            CommandToken("set-home-activity", "set the default launcher"),
            CommandToken("get-install-location", "current default install location"),
            CommandToken("set-install-location", "0 auto, 1 internal, 2 external"),
            CommandToken("move-package", "move a package to another volume"),
            CommandToken("move-primary-storage", "move the primary storage"),
            CommandToken("create-user", "add a device user"),
            CommandToken("remove-user", "delete a device user"),
            CommandToken("set-user-restriction", "restrict what a user may do"),
            CommandToken("reset-permissions", "revoke the runtime permissions"),
            CommandToken("set-permission-enforced", "enforce a permission or not"),
            CommandToken("get-privapp-permissions", "privileged permissions of a package"),
            CommandToken("get-oem-permissions", "oem permissions of a package"),
            CommandToken("get-moduleinfo", "module packages of the system"),
            CommandToken("has-feature", "does the device declare a feature"),
            CommandToken("set-app-link", "how a package handles its links"),
            CommandToken("get-app-link", "current link handling of a package"),
            CommandToken("set-harmful-app-warning", "warn before a package opens"),
            CommandToken("get-harmful-app-warning", "current warning of a package"),
            CommandToken("disable-until-used", "disable until something needs it"),
            CommandToken("log-visibility", "log package visibility checks"),
            CommandToken("install-add-session", "add a session to a multi package one"),
            CommandToken("get-instantapp-resolver", "component that resolves instant apps"),
            CommandToken("get-privapp-deny-permissions", "denied privileged permissions")
        )
    ),
    CommandSpec(
        prefix = "pm list",
        hint = "list what the package manager knows",
        words = listOf(
            CommandToken("packages", "installed packages"),
            CommandToken("permissions", "known permissions"),
            CommandToken("permission-groups", "permission groups"),
            CommandToken("features", "hardware features"),
            CommandToken("libraries", "shared libraries"),
            CommandToken("instrumentation", "test runners"),
            CommandToken("users", "device users")
        )
    ),
    CommandSpec(
        prefix = "pm list packages",
        hint = "installed packages",
        options = listOf(
            CommandToken("-f", "show the apk file of each package"),
            CommandToken("-d", "only disabled packages"),
            CommandToken("-e", "only enabled packages"),
            CommandToken("-s", "only system packages"),
            CommandToken("-3", "only third party packages"),
            CommandToken("-i", "show the installer"),
            CommandToken("-u", "also include uninstalled packages"),
            CommandToken("-a", "every known package"),
            CommandToken("-U", "show the uid of each package"),
            CommandToken("--uid", "only the package of this uid"),
            CommandToken("--show-versioncode", "add the version code"),
            CommandToken("--factory-only", "factory packages, android 12 and newer"),
            CommandToken("--apex-only", "only apex modules"),
            USER_OPTION
        )
    ),
    CommandSpec(
        prefix = "pm list permissions",
        hint = "known permissions",
        options = listOf(
            CommandToken("-g", "group by permission group"),
            CommandToken("-f", "show the full record"),
            CommandToken("-s", "short summary"),
            CommandToken("-d", "only dangerous permissions"),
            CommandToken("-u", "only permissions users see")
        )
    ),
    CommandSpec("pm path", hint = "apk path of a package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm dump", hint = "full package record", arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec(
        "pm dump-package",
        hint = "package manager record only, less noise",
        arguments = listOf(ArgumentSource.PACKAGE)
    ),
    CommandSpec(
        prefix = "pm clear",
        hint = "erase package data",
        options = listOf(USER_OPTION, CommandToken("--cache-only", "drop cache only, android 12 and newer")),
        arguments = listOf(ArgumentSource.PACKAGE)
    ),
    CommandSpec("pm enable", hint = "enable a package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm disable", hint = "disable a package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm disable-user", hint = "disable a package for one user", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm hide", hint = "hide a package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm unhide", hint = "show a hidden package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm suspend", hint = "suspend a package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm unsuspend", hint = "resume a package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("pm default-state", hint = "reset a package to the shipped state", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec(
        "pm grant",
        hint = "grant a permission",
        options = listOf(USER_OPTION),
        arguments = listOf(ArgumentSource.PACKAGE, ArgumentSource.PERMISSION)
    ),
    CommandSpec(
        "pm revoke",
        hint = "revoke a permission",
        options = listOf(USER_OPTION),
        arguments = listOf(ArgumentSource.PACKAGE, ArgumentSource.PERMISSION)
    ),
    CommandSpec(
        prefix = "pm uninstall",
        hint = "remove a package",
        options = listOf(
            CommandToken("-k", "keep data and cache directories"),
            CommandToken("--versionCode", "only this version code"),
            USER_OPTION
        ),
        arguments = listOf(ArgumentSource.PACKAGE)
    ),
    CommandSpec(
        prefix = "pm install",
        hint = "install an apk",
        options = listOf(
            CommandToken("-r", "reinstall, this is already the default"),
            CommandToken("-R", "fail if the package is already installed"),
            CommandToken("-t", "allow test packages"),
            CommandToken("-d", "allow a version downgrade"),
            CommandToken("-g", "grant all runtime permissions"),
            CommandToken("-f", "install on internal storage"),
            CommandToken("-i", "name the installer package"),
            CommandToken("--bypass-low-target-sdk-block", "old target sdk, android 14 and newer"),
            CommandToken("--install-location", "0 auto, 1 internal, 2 external"),
            CommandToken("--install-reason", "why the install happens, 0 to 4"),
            CommandToken("--originating-uri", "where the apk came from"),
            CommandToken("--referrer", "referrer uri of the install"),
            CommandToken("--pkg", "expected package name"),
            CommandToken("--abi", "force one abi"),
            CommandToken("--full", "install as a full app, not instant"),
            CommandToken("--instant", "install as an instant app"),
            CommandToken("--dont-kill", "keep the app running while it updates"),
            CommandToken("--enable-rollback", "allow a rollback of this install"),
            CommandToken("--staged", "stage the install for the next boot"),
            CommandToken("--apex", "the file is an apex module"),
            CommandToken("--force-uuid", "install on this storage volume"),
            CommandToken("--wait", "wait for the install to finish"),
            CommandToken("--force-sdk", "ignore the target sdk check"),
            CommandToken("--preload", "install as a preloaded app"),
            CommandToken("-S", "size of the apk that follows on stdin"),
            CommandToken("-p", "install one split into an existing package"),
            USER_OPTION
        )
    ),

    // ---------------------------------------------------------------- am
    CommandSpec(
        prefix = "am",
        hint = "activity manager",
        words = listOf(
            CommandToken("start", "start an activity"),
            CommandToken("start-service", "start a service"),
            CommandToken("start-foreground-service", "start a foreground service"),
            CommandToken("stopservice", "alias of stop-service"),
            CommandToken("force-stop", "stop everything of a package"),
            CommandToken("kill", "kill safe to kill processes"),
            CommandToken("kill-all", "kill all background processes"),
            CommandToken("broadcast", "send a broadcast"),
            CommandToken("instrument", "run an instrumentation"),
            CommandToken("stop-app", "keeps alarms and jobs, android 14 and newer"),
            CommandToken("stack", "task stack control"),
            CommandToken("task", "task control"),
            CommandToken("crash", "force a crash"),
            CommandToken("dumpheap", "write a heap dump"),
            CommandToken("get-config", "current device configuration"),
            CommandToken("set-debug-app", "wait for a debugger"),
            CommandToken("clear-debug-app", "clear the debug app"),
            CommandToken("start-activity", "canonical name of am start"),
            CommandToken("stop-service", "stop a service"),
            CommandToken("attach-agent", "attach a jvmti agent to a process"),
            CommandToken("profile", "start or stop profiling a process"),
            CommandToken("monitor", "watch for crashes and anrs"),
            CommandToken("hang", "hang the system for a test"),
            CommandToken("restart", "restart the whole system"),
            CommandToken("idle-maintenance", "run the idle maintenance now"),
            CommandToken("screen-compat", "screen compatibility of a package"),
            CommandToken("package-importance", "importance value of a package"),
            CommandToken("send-trim-memory", "ask a process to release memory"),
            CommandToken("make-uid-idle", "mark a uid as idle"),
            CommandToken("get-uid-state", "current state of a uid"),
            CommandToken("get-inactive", "is a package inactive"),
            CommandToken("set-inactive", "mark a package inactive"),
            CommandToken("get-standby-bucket", "standby bucket of a package"),
            CommandToken("set-standby-bucket", "move a package to a bucket"),
            CommandToken("get-current-user", "id of the foreground user"),
            CommandToken("start-user", "start a device user"),
            CommandToken("stop-user", "stop a device user"),
            CommandToken("switch-user", "switch to a device user"),
            CommandToken("unlock-user", "unlock a device user"),
            CommandToken("is-user-stopped", "is a user stopped"),
            CommandToken("supports-multiwindow", "does the device allow multi window"),
            CommandToken("set-watch-heap", "warn when a process heap grows"),
            CommandToken("clear-watch-heap", "drop the heap watch"),
            CommandToken("clear-exit-info", "clear the process exit history"),
            CommandToken("trace-ipc", "record binder traffic"),
            CommandToken("track-associations", "record app associations"),
            CommandToken("untrack-associations", "stop recording them"),
            CommandToken("update-appinfo", "reload the application info"),
            CommandToken("watch-uids", "follow uid state changes"),
            CommandToken("to-uri", "print an intent as a uri"),
            CommandToken("to-intent-uri", "print it as an intent uri"),
            CommandToken("to-app-uri", "print it as an app uri"),
            CommandToken("bug-report", "request a bug report"),
            CommandToken("compat", "app compatibility switches"),
            CommandToken("display", "move a task to another display"),
            CommandToken("get-started-user-state", "state of a started user"),
            CommandToken("suppress-resize-config-changes", "skip config changes on resize")
        )
    ),
    CommandSpec(
        prefix = "am start",
        hint = "start an activity",
        options = listOf(
            CommandToken("-n", "component, package/activity"),
            CommandToken("-a", "intent action"),
            CommandToken("-d", "intent data uri"),
            CommandToken("-t", "mime type"),
            CommandToken("-c", "intent category"),
            CommandToken("-f", "intent flags as an integer"),
            CommandToken("-W", "wait for the launch to finish"),
            CommandToken("-D", "start with a debugger"),
            CommandToken("-S", "force stop before the start"),
            CommandToken("--es", "string extra, key value"),
            CommandToken("--ez", "boolean extra, key value"),
            CommandToken("--ei", "integer extra, key value"),
            CommandToken("--el", "long extra, key value"),
            CommandToken("--ef", "float extra, key value"),
            CommandToken("--ed", "double extra, newer android"),
            CommandToken("--eu", "uri extra, key value"),
            CommandToken("--ecn", "component name extra, key value"),
            CommandToken("--esa", "string array extra, comma separated"),
            CommandToken("--esn", "null string extra, key only"),
            CommandToken("-R", "repeat the launch N times"),
            CommandToken("-N", "start with the native debugger"),
            CommandToken("--track-allocation", "track allocations"),
            CommandToken("--start-profiler", "write a profiling file"),
            CommandToken("--sampling", "profile sampling interval in ms"),
            CommandToken("--streaming", "stream the profiler output"),
            CommandToken("--attach-agent", "attach a jvmti agent"),
            CommandToken("--attach-agent-bind", "attach an agent and bind it"),
            CommandToken("-P", "profile until the app is idle"),
            CommandToken("--activity-clear-top", "FLAG_ACTIVITY_CLEAR_TOP"),
            CommandToken("--activity-single-top", "FLAG_ACTIVITY_SINGLE_TOP"),
            CommandToken("--activity-clear-task", "FLAG_ACTIVITY_CLEAR_TASK"),
            CommandToken("--activity-multiple-task", "FLAG_ACTIVITY_MULTIPLE_TASK"),
            CommandToken("--activityType", "activity type id"),
            CommandToken("--windowingMode", "windowing mode id"),
            CommandToken("--display", "display id"),
            USER_OPTION
        )
    ),
    CommandSpec("am force-stop", hint = "stop everything of a package", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec("am kill", hint = "kill safe to kill processes", options = listOf(USER_OPTION), arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec(
        prefix = "am broadcast",
        hint = "send a broadcast",
        options = listOf(
            CommandToken("-a", "intent action"),
            CommandToken("-n", "explicit component"),
            CommandToken("-p", "limit to a package"),
            CommandToken("-f", "intent flags as an integer"),
            CommandToken("--es", "string extra, key value"),
            CommandToken("--ez", "boolean extra, key value"),
            CommandToken("--ei", "integer extra, key value"),
            CommandToken("--receiver-foreground", "send as a foreground broadcast"),
            CommandToken("--allow-background-activity-starts", "let the receiver start an activity"),
            CommandToken("--receiver-permission", "only receivers holding this permission"),
            USER_OPTION
        )
    ),
    CommandSpec(
        "am stop-app",
        hint = "stop a package but keep its alarms and jobs",
        arguments = listOf(ArgumentSource.PACKAGE)
    ),
    CommandSpec(
        prefix = "am stack",
        hint = "task stack control",
        words = listOf(
            CommandToken("list", "list the task stacks"),
            CommandToken("info", "stack of a windowing mode and activity type"),
            CommandToken("remove", "remove a stack by id"),
            CommandToken("move-task", "move a task to a stack")
        )
    ),
    CommandSpec(
        prefix = "am task",
        hint = "task control",
        words = listOf(
            CommandToken("lock", "pin a task, lock stop releases it"),
            CommandToken("resize", "task id and the new bounds"),
            CommandToken("resizeable", "change the resize mode"),
            CommandToken("focus", "move the focus, newer android")
        )
    ),

    // ---------------------------------------------------------------- dumpsys
    CommandSpec(
        prefix = "dumpsys",
        hint = "dump a system service",
        words = listOf(
            CommandToken("activity", "activity manager"),
            CommandToken("package", "package manager"),
            CommandToken("window", "window manager"),
            CommandToken("battery", "battery service"),
            CommandToken("power", "power manager"),
            CommandToken("meminfo", "memory per process"),
            CommandToken("cpuinfo", "cpu load"),
            CommandToken("gfxinfo", "frame timings"),
            CommandToken("input", "input devices"),
            CommandToken("audio", "audio service"),
            CommandToken("media_session", "media sessions"),
            CommandToken("wifi", "wifi service"),
            CommandToken("connectivity", "network state"),
            CommandToken("notification", "notification service"),
            CommandToken("alarm", "alarm manager"),
            CommandToken("deviceidle", "doze state"),
            CommandToken("display", "display manager"),
            CommandToken("jobscheduler", "scheduled jobs"),
            CommandToken("usagestats", "app usage"),
            CommandToken("procstats", "process statistics"),
            CommandToken("SurfaceFlinger", "composition state")
        ),
        options = listOf(
            CommandToken("-l", "list every service"),
            CommandToken("-t", "timeout in seconds"),
            CommandToken("-T", "timeout in milliseconds"),
            CommandToken("--skip", "skip these services, comma separated"),
            CommandToken("--pid", "print the process id of the service"),
            CommandToken("--thread", "thread usage, android 12 and newer"),
            CommandToken("--clients", "service clients, android 12 and newer"),
            CommandToken("--priority", "CRITICAL, HIGH or NORMAL"),
            CommandToken("--proto", "protobuf output where supported"),
            CommandToken("--help", "usage of dumpsys itself")
        ),
        arguments = listOf(ArgumentSource.DUMPSYS_SERVICE)
    ),
    CommandSpec(
        prefix = "dumpsys activity",
        hint = "activity manager state",
        words = listOf(
            CommandToken("activities", "activity records and tasks"),
            CommandToken("services", "running services"),
            CommandToken("providers", "content providers"),
            CommandToken("provider", "client side state of one provider"),
            CommandToken("service", "client side state of one service"),
            CommandToken("broadcasts", "broadcast queues"),
            CommandToken("intents", "pending intents"),
            CommandToken("permissions", "uri permission grants"),
            CommandToken("associations", "tracked app associations"),
            CommandToken("allowed-associations", "package association limits"),
            CommandToken("processes", "running processes"),
            CommandToken("recents", "recent tasks"),
            CommandToken("top", "top activity"),
            CommandToken("lmk", "low memory killer statistics"),
            CommandToken("binder-proxies", "binder object statistics"),
            CommandToken("starter", "recent activity starts, newer android"),
            CommandToken("oom", "processes by out of memory adjustment"),
            CommandToken("lru", "processes in least recently used order"),
            CommandToken("exit-info", "why processes died"),
            CommandToken("package", "activity manager state of one package"),
            CommandToken("settings", "activity manager settings"),
            CommandToken("broadcast-stats", "broadcast statistics"),
            CommandToken("all", "everything")
        ),
        options = listOf(
            CommandToken("-a", "all available server state"),
            CommandToken("-c", "include client state"),
            CommandToken("-p", "limit the output to one package"),
            CommandToken("--proto", "protobuf output"),
            CommandToken("--autofill", "autofill state only"),
            CommandToken("--checkin", "checkin format, resets the data"),
            CommandToken("--C", "checkin format, keeps the data"),
            CommandToken("-s", "short form of the history")
        )
    ),
    CommandSpec(
        prefix = "dumpsys window",
        hint = "window manager state",
        words = listOf(
            CommandToken("windows", "window records"),
            CommandToken("visible", "visible windows only"),
            CommandToken("displays", "display state"),
            CommandToken("policy", "window policy"),
            CommandToken("animator", "animation state"),
            CommandToken("tokens", "window tokens"),
            CommandToken("sessions", "window sessions"),
            CommandToken("surfaces", "window surfaces"),
            CommandToken("lastanr", "the last recorded anr"),
            CommandToken("trace", "window tracing state"),
            CommandToken("visible-apps", "visible app windows only"),
            CommandToken("all", "everything")
        ),
        options = listOf(
            CommandToken("-a", "all available server state"),
            CommandToken("--proto", "protobuf output")
        )
    ),
    CommandSpec(
        prefix = "dumpsys battery",
        hint = "battery service state",
        words = listOf(
            CommandToken("set", "ac usb wireless status level temp present"),
            CommandToken("reset", "return to the real values"),
            CommandToken("unplug", "report the charger as removed")
        ),
        options = listOf(CommandToken("-f", "send a battery change broadcast"))
    ),
    CommandSpec(
        prefix = "dumpsys package",
        hint = "package manager record",
        options = listOf(
            CommandToken("-f", "print the file of each package"),
            CommandToken("--full", "every detail of the record"),
            CommandToken("--all-components", "every component of the package"),
            CommandToken("--checkin", "checkin format"),
            CommandToken("--proto", "protobuf output")
        ),
        words = listOf(
            CommandToken("check-permission", "does a package hold a permission"),
            CommandToken("permission", "state of one permission"),
            CommandToken("preferred-xml", "preferred activities as xml")
        ),
        arguments = listOf(ArgumentSource.PACKAGE)
    ),
    CommandSpec(
        prefix = "dumpsys meminfo",
        hint = "memory of a process",
        options = listOf(
            CommandToken("-a", "all available state"),
            CommandToken("-d", "dalvik detail"),
            CommandToken("-c", "checkin format"),
            CommandToken("-s", "short summary"),
            CommandToken("-S", "sort by size"),
            CommandToken("--oom", "group by out of memory adjustment"),
            CommandToken("--local", "only the local process"),
            CommandToken("--package", "sum the processes of a package"),
            CommandToken("--checkin", "checkin format"),
            CommandToken("--proto", "protobuf output")
        ),
        arguments = listOf(ArgumentSource.PACKAGE)
    ),
    CommandSpec("dumpsys gfxinfo", hint = "frame timings of a process", arguments = listOf(ArgumentSource.PACKAGE)),

    // ---------------------------------------------------------------- settings
    CommandSpec(
        prefix = "settings",
        hint = "read and write system settings",
        words = listOf(
            CommandToken("get", "read one key"),
            CommandToken("put", "write one key"),
            CommandToken("delete", "remove one key"),
            CommandToken("list", "list a namespace"),
            CommandToken("reset", "reset a namespace")
        ),
        options = listOf(USER_OPTION)
    ),
    CommandSpec("settings get", hint = "read one key", words = SETTINGS_NAMESPACES, options = listOf(USER_OPTION)),
    CommandSpec("settings put", hint = "write one key", words = SETTINGS_NAMESPACES, options = listOf(USER_OPTION)),
    CommandSpec("settings delete", hint = "remove one key", words = SETTINGS_NAMESPACES, options = listOf(USER_OPTION)),
    CommandSpec("settings list", hint = "list a namespace", words = SETTINGS_NAMESPACES, options = listOf(USER_OPTION)),
    CommandSpec(
        prefix = "settings reset",
        hint = "reset secure or global to defaults",
        // The system namespace has no reset - only secure and global do
        words = listOf(
            CommandToken("secure", "settings apps may read but not write"),
            CommandToken("global", "settings shared by all users"),
            CommandToken("untrusted_defaults", "reset keys set by untrusted apps"),
            CommandToken("untrusted_clear", "clear keys set by untrusted apps"),
            CommandToken("trusted_defaults", "reset every key to its default")
        ),
        options = listOf(USER_OPTION)
    ),

    // ---------------------------------------------------------------- input
    CommandSpec(
        prefix = "input",
        hint = "send input events",
        words = listOf(
            CommandToken("text", "type a string"),
            CommandToken("keyevent", "send a key code"),
            CommandToken("tap", "tap at x y"),
            CommandToken("swipe", "swipe x1 y1 x2 y2"),
            CommandToken("draganddrop", "drag x1 y1 x2 y2"),
            CommandToken("press", "press the trackball"),
            CommandToken("roll", "roll the trackball"),
            CommandToken("motionevent", "raw motion event"),
            CommandToken("scroll", "scroll by x and y, android 12 and newer"),
            CommandToken("keycombination", "several keys at once, android 12 and newer"),
            CommandToken("touchscreen", "source for the next sub command"),
            CommandToken("keyboard", "source for the next sub command"),
            CommandToken("mouse", "source for the next sub command"),
            CommandToken("touchpad", "source for the next sub command"),
            CommandToken("trackball", "source for the next sub command"),
            CommandToken("dpad", "source for the next sub command"),
            CommandToken("gamepad", "source for the next sub command"),
            CommandToken("joystick", "source for the next sub command"),
            CommandToken("stylus", "source for the next sub command"),
            CommandToken("touchnavigation", "source for the next sub command")
        ),
        options = listOf(CommandToken("-d", "display id"))
    ),
    CommandSpec(
        prefix = "input keyevent",
        hint = "send a key code",
        options = listOf(
            CommandToken("--longpress", "send it as a long press"),
            CommandToken("--doubletap", "send it twice, android 12 and newer"),
            CommandToken("--duration", "press time in ms, android 12 and newer"),
            CommandToken("--delay", "delay between keys, android 12 and newer"),
            CommandToken("--async", "do not wait, android 12 and newer")
        ),
        words = listOf(
            CommandToken("KEYCODE_HOME", "home key"),
            CommandToken("KEYCODE_BACK", "back key"),
            CommandToken("KEYCODE_MENU", "menu key"),
            CommandToken("KEYCODE_APP_SWITCH", "recent apps key"),
            CommandToken("KEYCODE_POWER", "power key"),
            CommandToken("KEYCODE_ENTER", "enter key"),
            CommandToken("KEYCODE_DEL", "backspace key"),
            CommandToken("KEYCODE_TAB", "tab key"),
            CommandToken("KEYCODE_SEARCH", "search key"),
            CommandToken("KEYCODE_VOLUME_UP", "volume up"),
            CommandToken("KEYCODE_VOLUME_DOWN", "volume down"),
            CommandToken("KEYCODE_VOLUME_MUTE", "mute the volume"),
            CommandToken("KEYCODE_DPAD_UP", "d-pad up"),
            CommandToken("KEYCODE_DPAD_DOWN", "d-pad down"),
            CommandToken("KEYCODE_DPAD_LEFT", "d-pad left"),
            CommandToken("KEYCODE_DPAD_RIGHT", "d-pad right"),
            CommandToken("KEYCODE_DPAD_CENTER", "d-pad select"),
            CommandToken("KEYCODE_MEDIA_PLAY_PAUSE", "play or pause"),
            CommandToken("KEYCODE_MEDIA_NEXT", "next track"),
            CommandToken("KEYCODE_MEDIA_PREVIOUS", "previous track")
        )
    ),

    // ---------------------------------------------------------------- other tools
    CommandSpec(
        prefix = "logcat",
        hint = "system log",
        options = listOf(
            CommandToken("-c", "clear the buffers"),
            CommandToken("-d", "dump and exit"),
            CommandToken("-t", "last N lines, then exit"),
            CommandToken("-v", "output format, for example threadtime"),
            CommandToken("-b", "buffer, main radio events crash all"),
            CommandToken("-s", "silence all, then add filters"),
            CommandToken("-e", "only lines matching a regex"),
            CommandToken("-m", "stop after N lines"),
            CommandToken("-f", "write to a file"),
            CommandToken("-r", "rotate the file every N kilobytes"),
            CommandToken("-n", "keep N rotated files"),
            CommandToken("-g", "print the buffer size"),
            CommandToken("-G", "set the buffer size"),
            CommandToken("-B", "binary output"),
            CommandToken("-S", "print statistics"),
            CommandToken("-T", "print the last N lines and keep following"),
            CommandToken("-D", "print a divider between buffers"),
            CommandToken("-L", "the log of the previous boot"),
            CommandToken("-p", "print the prune rules"),
            CommandToken("-P", "set the prune rules"),
            CommandToken("--clear", "clear the buffers, long form"),
            CommandToken("--file", "write to a file, long form"),
            CommandToken("--format", "output format, long form"),
            CommandToken("--buffer", "buffer to read, long form"),
            CommandToken("--buffer-size", "buffer size, long form"),
            CommandToken("--regex", "only lines matching a regex"),
            CommandToken("--max-count", "stop after N lines"),
            CommandToken("--rotate-count", "how many rotated files to keep"),
            CommandToken("--rotate-kbytes", "rotate every N kilobytes"),
            CommandToken("--prune", "read or write the prune rules"),
            CommandToken("--statistics", "print buffer statistics"),
            CommandToken("--binary", "binary output"),
            CommandToken("--last", "the log of the previous boot"),
            CommandToken("--dividers", "print a divider between buffers"),
            CommandToken("--wrap", "wait for the buffer to fill"),
            CommandToken("--print", "print to stdout with a file"),
            CommandToken("--id", "only this log id"),
            CommandToken("--pid", "only this pid")
        )
    ),
    CommandSpec(
        prefix = "monkey",
        hint = "random event stress test",
        options = listOf(
            CommandToken("-p", "limit to a package"),
            CommandToken("-v", "more verbose output"),
            CommandToken("-s", "random seed"),
            CommandToken("--throttle", "delay between events in ms"),
            CommandToken("--ignore-crashes", "keep going after a crash"),
            CommandToken("--ignore-timeouts", "keep going after an anr"),
            CommandToken("--ignore-security-exceptions", "keep going after a permission error"),
            CommandToken("--monitor-native-crashes", "watch for native crashes"),
            CommandToken("--kill-process-after-error", "stop the app after an error"),
            CommandToken("-c", "only activities of this category"),
            CommandToken("-f", "read the events from a script file"),
            CommandToken("--port", "listen for a monkey client"),
            CommandToken("--setup", "run a setup script first"),
            CommandToken("--hprof", "write a heap dump around the run"),
            CommandToken("--bugreport", "write a bug report on an error"),
            CommandToken("--periodic-bugreport", "write one every so often"),
            CommandToken("--wait-dbg", "wait for a debugger"),
            CommandToken("--dbg-no-events", "set up but send no events"),
            CommandToken("--ignore-native-crashes", "keep going after a native crash"),
            CommandToken("--match-description", "only crashes matching a text"),
            CommandToken("--device-sleep-time", "sleep between events"),
            CommandToken("--randomize-throttle", "make the delay random"),
            CommandToken("--pkg-whitelist-file", "packages that may run"),
            CommandToken("--pkg-blacklist-file", "packages that may not run"),
            CommandToken("--pct-touch", "share of touch events"),
            CommandToken("--pct-motion", "share of drag events"),
            CommandToken("--pct-trackball", "share of trackball events"),
            CommandToken("--pct-nav", "share of basic navigation events"),
            CommandToken("--pct-majornav", "share of major navigation events"),
            CommandToken("--pct-syskeys", "share of system key events"),
            CommandToken("--pct-appswitch", "share of activity launches"),
            CommandToken("--pct-flip", "share of keyboard flips"),
            CommandToken("--pct-pinchzoom", "share of pinch zoom events"),
            CommandToken("--pct-permission", "share of permission dialogs"),
            CommandToken("--pct-anyevent", "share of any other event"),
            CommandToken("--script-log", "write a script log"),
            CommandToken("--randomize-script", "shuffle the script events"),
            CommandToken("--profile-wait", "wait between profile samples"),
            CommandToken("--permission-target-system", "target system apps for permissions")
        )
    ),
    CommandSpec("monkey -p", hint = "stress test one package", arguments = listOf(ArgumentSource.PACKAGE)),
    CommandSpec(
        prefix = "svc",
        hint = "power, data, wifi and usb control",
        words = listOf(
            CommandToken("power", "power manager control"),
            CommandToken("usb", "usb mode control"),
            CommandToken("nfc", "nfc control"),
            CommandToken("system-server", "system server control"),
            CommandToken("data", "mobile data control"),
            CommandToken("wifi", "wifi control"),
            CommandToken("bluetooth", "bluetooth control")
        )
    ),
    CommandSpec(
        prefix = "svc power",
        hint = "power manager control",
        words = listOf(
            CommandToken("stayon", "true false usb ac wireless"),
            CommandToken("reboot", "reboot with a reason"),
            CommandToken("shutdown", "shut the device down"),
            CommandToken("forcesuspend", "suspend now, ignoring wakelocks")
        )
    ),
    CommandSpec(
        prefix = "svc power stayon",
        hint = "keep the screen on while charging",
        words = listOf(
            CommandToken("true", "always stay on"),
            CommandToken("false", "normal screen timeout"),
            CommandToken("usb", "stay on with usb power"),
            CommandToken("ac", "stay on with ac power"),
            CommandToken("wireless", "stay on with wireless charging")
        )
    ),
    CommandSpec(
        prefix = "svc data",
        hint = "mobile data control",
        words = listOf(
            CommandToken("enable", "turn mobile data on"),
            CommandToken("disable", "turn mobile data off")
        )
    ),
    CommandSpec(
        prefix = "svc wifi",
        hint = "wifi control",
        words = listOf(
            CommandToken("enable", "turn wifi on"),
            CommandToken("disable", "turn wifi off")
        )
    ),
    CommandSpec(
        prefix = "svc nfc",
        hint = "nfc control",
        words = listOf(
            CommandToken("enable", "turn nfc on"),
            CommandToken("disable", "turn nfc off")
        )
    ),
    CommandSpec(
        prefix = "svc bluetooth",
        hint = "bluetooth control",
        words = listOf(
            CommandToken("enable", "turn bluetooth on"),
            CommandToken("disable", "turn bluetooth off")
        )
    ),
    CommandSpec(
        prefix = "svc usb",
        hint = "usb mode control",
        words = listOf(
            CommandToken("getFunctions", "current usb functions"),
            CommandToken("setFunctions", "mtp, ptp, rndis, midi or none"),
            CommandToken("setScreenUnlockedFunctions", "functions while the screen is unlocked"),
            CommandToken("resetUsbGadget", "restart the usb gadget")
        )
    ),
    CommandSpec(
        prefix = "cmd package",
        hint = "package manager service",
        words = listOf(
            CommandToken("list", "list packages and more"),
            CommandToken("path", "apk path of a package"),
            CommandToken("install", "install an apk"),
            CommandToken("uninstall", "remove a package"),
            CommandToken("resolve-activity", "activity that handles an intent"),
            CommandToken("query-activities", "activities that handle an intent"),
            CommandToken("dump", "full package record")
        )
    ),
    CommandSpec(
        prefix = "cmd",
        hint = "call a system service",
        words = listOf(
            CommandToken("package", "package manager service"),
            CommandToken("activity", "activity manager service"),
            CommandToken("notification", "notification service"),
            CommandToken("statusbar", "status bar service"),
            CommandToken("media_session", "media session service"),
            CommandToken("appops", "app operations"),
            CommandToken("device_config", "device config flags"),
            CommandToken("overlay", "runtime resource overlays"),
            CommandToken("jobscheduler", "job scheduler service")
        ),
        options = listOf(
            CommandToken("-l", "list every service"),
            CommandToken("-w", "wait for the service to start")
        ),
        arguments = listOf(ArgumentSource.SERVICE)
    ),
    CommandSpec(
        prefix = "content",
        hint = "content provider client",
        words = listOf(
            CommandToken("query", "read rows"),
            CommandToken("insert", "add a row"),
            CommandToken("update", "change rows"),
            CommandToken("delete", "remove rows"),
            CommandToken("call", "call a provider method"),
            CommandToken("read", "read a file from the provider"),
            CommandToken("write", "write a file to the provider"),
            CommandToken("gettype", "mime type of a uri")
        ),
        options = listOf(
            CommandToken("--uri", "content uri"),
            CommandToken("--where", "sql where clause"),
            CommandToken("--bind", "column:type:value, type is b s i l f d n"),
            CommandToken("--method", "provider method for call"),
            CommandToken("--arg", "argument for call"),
            CommandToken("--extra", "extra for call, key:type:value"),
            CommandToken("--projection", "columns to read"),
            CommandToken("--sort", "sql order by clause"),
            USER_OPTION
        )
    ),
    CommandSpec(
        prefix = "wm",
        hint = "window manager control",
        words = listOf(
            CommandToken("size", "display size, reset restores it"),
            CommandToken("density", "display density, reset restores it"),
            CommandToken("scaling", "force scaling mode, off or auto"),
            CommandToken("dismiss-keyguard", "unlock the screen"),
            CommandToken("set-user-rotation", "free or lock the rotation"),
            CommandToken("set-fix-to-user-rotation", "enabled or disabled"),
            CommandToken("folded-area", "simulate a folded area"),
            CommandToken("dump-visible-window-views", "view tree of visible windows"),
            CommandToken("logging", "start stop enable disable"),
            CommandToken("tracing", "start or stop window tracing"),
            CommandToken("set-ignore-orientation-request", "android 12 and newer"),
            CommandToken("set-display-windowing-mode", "android 12 and newer")
        ),
        options = listOf(CommandToken("-d", "display id"))
    ),
    // ---------------------------------------------------------------- device_config
    CommandSpec(
        prefix = "device_config",
        hint = "read and write device config flags",
        words = listOf(
            CommandToken("list_namespaces", "namespaces, android 12 and newer"),
            CommandToken("list", "flags of a namespace"),
            CommandToken("get", "read one flag, namespace and key"),
            CommandToken("put", "write one flag, namespace key value"),
            CommandToken("delete", "remove one flag, namespace and key"),
            CommandToken("reset", "reset a mode, then a namespace"),
            CommandToken("override", "local override, android 13 and newer"),
            CommandToken("clear_override", "drop an override, android 13 and newer"),
            CommandToken("set_sync_disabled_for_tests", "sync mode, android 12 and newer"),
            CommandToken("get_sync_disabled_for_tests", "sync mode, android 12 and newer")
        )
    ),
    CommandSpec(
        prefix = "cmd device_config",
        hint = "device config service",
        words = listOf(
            CommandToken("list_namespaces", "namespaces, android 12 and newer"),
            CommandToken("list", "flags of a namespace"),
            CommandToken("get", "read one flag, namespace and key"),
            CommandToken("put", "write one flag, namespace key value"),
            CommandToken("delete", "remove one flag, namespace and key"),
            CommandToken("reset", "reset a mode, then a namespace"),
            CommandToken("override", "local override, android 13 and newer"),
            CommandToken("clear_override", "drop an override, android 13 and newer"),
            CommandToken("set_sync_disabled_for_tests", "sync mode, android 12 and newer"),
            CommandToken("get_sync_disabled_for_tests", "sync mode, android 12 and newer")
        )
    ),
    CommandSpec(
        prefix = "device_config reset",
        hint = "reset a mode, then a namespace",
        words = listOf(
            CommandToken("untrusted_defaults", "reset flags set by untrusted apps"),
            CommandToken("untrusted_clear", "clear flags set by untrusted apps"),
            CommandToken("trusted_defaults", "reset every flag to its default")
        )
    ),
    CommandSpec(
        prefix = "device_config set_sync_disabled_for_tests",
        hint = "stop the config from syncing",
        words = listOf(
            CommandToken("none", "syncing is on"),
            CommandToken("persistent", "off until it is turned back on"),
            CommandToken("until_reboot", "off until the next boot")
        )
    ),

    // ---------------------------------------------------------------- appops
    CommandSpec(
        prefix = "appops",
        hint = "app operation permissions",
        words = listOf(
            CommandToken("get", "modes of a package"),
            CommandToken("set", "package, operation and mode"),
            CommandToken("query-op", "packages in a mode for an operation"),
            CommandToken("reset", "back to the default modes"),
            CommandToken("start", "start an operation"),
            CommandToken("stop", "stop an operation"),
            CommandToken("write-settings", "flush to disk"),
            CommandToken("read-settings", "reload from disk")
        ),
        options = listOf(
            USER_OPTION,
            CommandToken("--uid", "act on a uid instead of a package"),
            CommandToken("--attribution", "attribution tag")
        )
    ),
    CommandSpec(
        prefix = "cmd appops",
        hint = "app operations service",
        words = listOf(
            CommandToken("get", "modes of a package"),
            CommandToken("set", "package, operation and mode"),
            CommandToken("query-op", "packages in a mode for an operation"),
            CommandToken("reset", "back to the default modes"),
            CommandToken("start", "start an operation"),
            CommandToken("stop", "stop an operation"),
            CommandToken("write-settings", "flush to disk"),
            CommandToken("read-settings", "reload from disk")
        ),
        options = listOf(
            USER_OPTION,
            CommandToken("--uid", "act on a uid instead of a package"),
            CommandToken("--attribution", "attribution tag")
        )
    ),
    CommandSpec(
        prefix = "appops set",
        hint = "package, operation and mode",
        options = listOf(
            USER_OPTION,
            CommandToken("--uid", "act on a uid instead of a package"),
            CommandToken("--attribution", "attribution tag")
        ),
        arguments = listOf(ArgumentSource.PACKAGE)
    ),
    CommandSpec(
        prefix = "appops get",
        hint = "modes of a package",
        options = listOf(
            USER_OPTION,
            CommandToken("--uid", "act on a uid instead of a package"),
            CommandToken("--attribution", "attribution tag")
        ),
        arguments = listOf(ArgumentSource.PACKAGE)
    ),

    // ---------------------------------------------------------------- other services
    CommandSpec(
        prefix = "cmd overlay",
        hint = "runtime resource overlays",
        words = listOf(
            CommandToken("list", "overlays and their state"),
            CommandToken("enable", "turn an overlay on"),
            CommandToken("disable", "turn an overlay off"),
            CommandToken("enable-exclusive", "turn one on and the rest off"),
            CommandToken("set-priority", "order of an overlay"),
            CommandToken("lookup", "resource value an overlay gives"),
            CommandToken("fabricate", "create an overlay at runtime"),
            CommandToken("destroy", "remove a fabricated overlay")
        ),
        options = listOf(
            USER_OPTION,
            CommandToken("--category", "only overlays of a category"),
            CommandToken("--verbose", "more detail in the listing")
        )
    ),
    CommandSpec(
        prefix = "cmd statusbar",
        hint = "status bar service",
        words = listOf(
            CommandToken("expand-notifications", "open the notification shade"),
            CommandToken("expand-settings", "open quick settings"),
            CommandToken("collapse", "close the shade"),
            CommandToken("add-tile", "add a quick settings tile"),
            CommandToken("remove-tile", "remove a quick settings tile"),
            CommandToken("click-tile", "click a quick settings tile"),
            CommandToken("send-disable-flag", "disable parts of the status bar"),
            CommandToken("tracing", "status bar tracing")
        )
    ),
    CommandSpec(
        prefix = "cmd notification",
        hint = "notification service",
        words = listOf(
            CommandToken("list", "posted notifications"),
            CommandToken("get", "one notification by key"),
            CommandToken("post", "post a notification"),
            CommandToken("set_dnd", "off, priority, alarms or none"),
            CommandToken("allow_listener", "let a component listen"),
            CommandToken("disallow_listener", "stop a component listening"),
            CommandToken("allow_assistant", "set the notification assistant"),
            CommandToken("suspend_package", "hide the notifications of a package"),
            CommandToken("unsuspend_package", "show them again")
        ),
        options = listOf(CommandToken("--for", "the package the action applies to"))
    ),
    CommandSpec(
        prefix = "cmd jobscheduler",
        hint = "scheduled jobs",
        words = listOf(
            CommandToken("run", "run a job now"),
            CommandToken("timeout", "time a job out"),
            CommandToken("cancel", "cancel a job"),
            CommandToken("get-job-state", "state of one job"),
            CommandToken("monitor-battery", "watch the battery condition"),
            CommandToken("get-battery-charging", "charging state the scheduler sees"),
            CommandToken("get-storage-not-low", "storage condition the scheduler sees"),
            CommandToken("trigger-dock-state", "pretend the dock state changed")
        ),
        options = listOf(
            USER_OPTION,
            CommandToken("-f", "run even if the constraints are not met"),
            CommandToken("--force", "run even if the constraints are not met"),
            CommandToken("-s", "the job satisfies its constraints"),
            CommandToken("--satisfied", "the job satisfies its constraints"),
            CommandToken("-u", "user of the job")
        )
    ),
    CommandSpec(
        prefix = "cmd media_session",
        hint = "media session service",
        words = listOf(
            CommandToken("list-sessions", "active media sessions"),
            CommandToken("dispatch", "send a media key"),
            CommandToken("volume", "volume of a session"),
            CommandToken("monitor", "follow session changes")
        ),
        options = listOf(
            CommandToken("--show", "show the volume interface"),
            CommandToken("--get", "read the volume"),
            CommandToken("--set", "write the volume"),
            CommandToken("--adj", "raise or lower the volume"),
            CommandToken("--stream", "the audio stream to act on")
        )
    ),
    CommandSpec(
        prefix = "cmd wifi",
        hint = "wifi service, more than svc wifi gives",
        words = listOf(
            CommandToken("status", "connection state"),
            CommandToken("set-wifi-enabled", "enabled or disabled"),
            CommandToken("start-scan", "start a scan"),
            CommandToken("list-scan-results", "results of the last scan"),
            CommandToken("connect-network", "join a network"),
            CommandToken("list-networks", "saved networks"),
            CommandToken("forget-network", "remove a saved network")
        ),
        options = listOf(
            CommandToken("-b", "bssid of the network"),
            CommandToken("-d", "the network is hidden"),
            CommandToken("-m", "the network is metered"),
            CommandToken("-s", "use a static ip"),
            CommandToken("-u", "the network is not metered")
        )
    ),
    CommandSpec(
        prefix = "cmd bluetooth_manager",
        hint = "bluetooth service, android 12 and newer",
        words = listOf(
            CommandToken("enable", "turn bluetooth on"),
            CommandToken("disable", "turn bluetooth off"),
            CommandToken("wait-for-state", "block until a state is reached")
        )
    ),
    CommandSpec(
        prefix = "cmd uimode",
        hint = "ui mode service",
        words = listOf(
            CommandToken("night", "yes, no, auto or custom"),
            CommandToken("time", "start or end of the custom schedule")
        )
    ),
    CommandSpec(
        prefix = "cmd power",
        hint = "power service",
        words = listOf(
            CommandToken("set-mode", "power mode id"),
            CommandToken("set-adaptive-power-saver-enabled", "true or false"),
            CommandToken("set-fixed-performance-mode-enabled", "true or false")
        )
    ),
    CommandSpec(
        prefix = "cmd role",
        hint = "role service",
        words = listOf(
            CommandToken("add-role-holder", "give a role to a package"),
            CommandToken("remove-role-holder", "take a role from a package"),
            CommandToken("clear-role-holders", "drop every holder of a role")
        ),
        options = listOf(USER_OPTION)
    ),
    CommandSpec(
        prefix = "cmd shortcut",
        hint = "shortcut service",
        words = listOf(
            CommandToken("reset-throttling", "clear the rate limit"),
            CommandToken("reset-all-throttling", "clear it for every user"),
            CommandToken("clear-shortcuts", "remove the shortcuts of a package"),
            CommandToken("get-default-launcher", "current default launcher"),
            CommandToken("clear-default-launcher", "drop the cached launcher"),
            CommandToken("override-config", "test configuration until reboot"),
            CommandToken("reset-config", "undo override-config"),
            CommandToken("unload-user", "drop a user from memory")
        ),
        options = listOf(USER_OPTION)
    ),

    // ---------------------------------------------------------------- standalone tools
    CommandSpec(
        prefix = "bmgr",
        hint = "backup manager",
        words = listOf(
            CommandToken("backup", "queue a package for backup"),
            CommandToken("backupnow", "back up now, --all or a package"),
            CommandToken("restore", "restore a token or a package"),
            CommandToken("list", "transports or sets"),
            CommandToken("transport", "select a transport"),
            CommandToken("enable", "true or false"),
            CommandToken("enabled", "is backup on"),
            CommandToken("run", "run the queued backups"),
            CommandToken("wipe", "erase the backup of a package"),
            CommandToken("fullbackup", "full backup of a package")
        )
    ),
    CommandSpec(
        prefix = "ime",
        hint = "input method control",
        words = listOf(
            CommandToken("list", "input methods"),
            CommandToken("enable", "enable one by id"),
            CommandToken("disable", "disable one by id"),
            CommandToken("set", "make one current"),
            CommandToken("reset", "back to the default")
        ),
        options = listOf(
            CommandToken("-a", "include those that are not enabled"),
            CommandToken("-s", "ids only"),
            USER_OPTION
        )
    ),
    CommandSpec(
        prefix = "locksettings",
        hint = "screen lock control",
        words = listOf(
            CommandToken("set-pin", "set a pin"),
            CommandToken("set-password", "set a password"),
            CommandToken("set-pattern", "set a pattern"),
            CommandToken("clear", "remove the lock, needs the old one"),
            CommandToken("verify", "check the current lock"),
            CommandToken("set-disabled", "true or false"),
            CommandToken("get-disabled", "is the lock disabled"),
            CommandToken("sp", "synthetic password state"),
            CommandToken("remove-cache", "drop the cached unified challenge")
        ),
        options = listOf(USER_OPTION, CommandToken("--old", "the current pin or password"))
    ),
    CommandSpec(
        prefix = "sm",
        hint = "storage manager",
        words = listOf(
            CommandToken("list-disks", "storage disks"),
            CommandToken("list-volumes", "storage volumes"),
            CommandToken("mount", "mount a volume"),
            CommandToken("unmount", "unmount a volume"),
            CommandToken("format", "format a volume"),
            CommandToken("partition", "partition a disk"),
            CommandToken("has-adoptable", "can storage be adopted"),
            CommandToken("get-primary-storage-uuid", "uuid of the primary storage"),
            CommandToken("set-force-adoptable", "on, off or default")
        )
    ),
    CommandSpec(
        prefix = "service",
        hint = "raw binder service access",
        words = listOf(
            CommandToken("list", "registered services"),
            CommandToken("check", "is a service running"),
            CommandToken("call", "call a transaction by number")
        ),
        arguments = listOf(ArgumentSource.SERVICE)
    ),
    CommandSpec(
        prefix = "am instrument",
        hint = "run an instrumentation",
        options = listOf(
            CommandToken("-w", "wait for it to finish"),
            CommandToken("-r", "raw output"),
            CommandToken("-e", "key and value argument"),
            CommandToken("-p", "write a profiling file"),
            CommandToken("-m", "protobuf output"),
            CommandToken("--no-window-animation", "turn animations off"),
            CommandToken("--abi", "run for one abi"),
            CommandToken("--no-hidden-api-checks", "allow hidden api use"),
            CommandToken("--no-isolated-storage", "opt out of scoped storage"),
            CommandToken("--no-test-api-access", "block the test api"),
            CommandToken("-f", "write the result to a file"),
            USER_OPTION
        )
    ),
    CommandSpec(
        prefix = "screencap",
        hint = "write a screenshot to a file",
        options = listOf(
            CommandToken("-p", "write a png"),
            CommandToken("-d", "display id"),
            CommandToken("--display-id", "display id, long form")
        )
    ),
    CommandSpec(
        prefix = "screenrecord",
        hint = "record the screen to a file",
        options = listOf(
            CommandToken("--size", "width x height"),
            CommandToken("--bit-rate", "bits per second"),
            CommandToken("--time-limit", "seconds to record"),
            CommandToken("--rotate", "rotate the output, newer android"),
            CommandToken("--bugreport", "add a frame counter"),
            CommandToken("--display-id", "record another display"),
            CommandToken("--verbose", "more output")
        )
    ),
    CommandSpec(
        prefix = "bugreportz",
        hint = "write a bug report zip",
        options = listOf(
            CommandToken("-p", "show progress"),
            CommandToken("-s", "stream the report, newer android"),
            CommandToken("-v", "version of the protocol")
        )
    ),
    CommandSpec(
        prefix = "dpm",
        hint = "device policy manager",
        options = listOf(USER_OPTION, CommandToken("--name", "label of the owner")),
        words = listOf(
            CommandToken("set-active-admin", "make a component an admin"),
            CommandToken("set-device-owner", "make a component the device owner"),
            CommandToken("set-profile-owner", "make a component a profile owner"),
            CommandToken("remove-active-admin", "drop an admin"),
            CommandToken("clear-freeze-period-record", "clear the update freeze record")
        ),
    ),
    CommandSpec(
        prefix = "telecom",
        hint = "telecom service",
        words = listOf(
            CommandToken("get-default-dialer", "current default dialer"),
            CommandToken("get-system-dialer", "dialer that ships with the system"),
            CommandToken("set-phone-account-enabled", "enable a phone account"),
            CommandToken("set-user-selected-outgoing-phone-account", "pick the outgoing account"),
            CommandToken("get-max-phones", "how many phone slots exist")
        ),
        options = listOf(CommandToken("-e", "act on the emergency account"))
    ),
    CommandSpec(
        prefix = "setprop",
        hint = "write a system property"
    ),
    CommandSpec(
        prefix = "getprop",
        hint = "read system properties",
        options = listOf(
            CommandToken("-T", "print the type of each property"),
            CommandToken("-Z", "print the selinux context of each property")
        )
    ),
    CommandSpec(
        prefix = "ls",
        hint = "list a directory",
        options = listOf(
            CommandToken("-a", "include hidden entries"),
            CommandToken("-A", "hidden entries but not dot and dotdot"),
            CommandToken("-l", "long format"),
            CommandToken("-1", "one entry per line"),
            CommandToken("-C", "columns, sorted down"),
            CommandToken("-x", "columns, sorted across"),
            CommandToken("-m", "comma separated"),
            CommandToken("-R", "walk into directories"),
            CommandToken("-d", "the directory itself"),
            CommandToken("-h", "human readable sizes"),
            CommandToken("-S", "sort by size"),
            CommandToken("-t", "sort by time"),
            CommandToken("-r", "reverse the sort"),
            CommandToken("-u", "use the access time"),
            CommandToken("-c", "use the change time"),
            CommandToken("-f", "no sorting at all"),
            CommandToken("-i", "print the inode"),
            CommandToken("-s", "print the block size"),
            CommandToken("-n", "numeric owner and group"),
            CommandToken("-o", "long format without the group"),
            CommandToken("-g", "long format without the owner"),
            CommandToken("-p", "mark directories with a slash"),
            CommandToken("-F", "mark the type of each entry"),
            CommandToken("-q", "unprintable characters as a question mark"),
            CommandToken("-L", "follow symbolic links"),
            CommandToken("-H", "follow the links given on the line"),
            CommandToken("-Z", "print the selinux context"),
            CommandToken("--color", "colour the output"),
            CommandToken("--full-time", "full timestamp")
        )
    ),
    CommandSpec(
        prefix = "df",
        hint = "free disk space",
        options = listOf(
            CommandToken("-h", "human readable sizes"),
            CommandToken("-H", "human readable, powers of 1000"),
            CommandToken("-k", "sizes in kilobytes"),
            CommandToken("-a", "include pseudo file systems"),
            CommandToken("-i", "inode usage"),
            CommandToken("-P", "posix output format"),
            CommandToken("-t", "only this file system type")
        )
    ),
    CommandSpec(
        prefix = "ps",
        hint = "running processes",
        options = listOf(
            CommandToken("-A", "every process"),
            CommandToken("-e", "every process, same as -A"),
            CommandToken("-a", "processes with a terminal, except leaders"),
            CommandToken("-d", "every process except session leaders"),
            CommandToken("-f", "full format"),
            CommandToken("-l", "long format"),
            CommandToken("-o", "pick the columns"),
            CommandToken("-O", "add columns to the default set"),
            CommandToken("-T", "show threads"),
            CommandToken("-p", "only these process ids"),
            CommandToken("-P", "only these parent ids"),
            CommandToken("-u", "only these users"),
            CommandToken("-U", "only these real users"),
            CommandToken("-g", "only these session ids"),
            CommandToken("-G", "only these real group ids"),
            CommandToken("-t", "only these terminals"),
            CommandToken("-k", "sort by these fields"),
            CommandToken("-n", "numeric output"),
            CommandToken("-w", "wide output, no truncation"),
            CommandToken("-s", "only these session ids"),
            CommandToken("--pid", "only these process ids, long form"),
            CommandToken("--ppid", "only these parent ids, long form"),
            CommandToken("-Z", "print the selinux context")
        )
    ),
    CommandSpec(
        prefix = "top",
        hint = "live process load",
        options = listOf(
            CommandToken("-n", "stop after N refreshes"),
            CommandToken("-d", "delay between refreshes"),
            CommandToken("-m", "show only N processes"),
            CommandToken("-b", "batch mode, no redraw"),
            CommandToken("-H", "show threads"),
            CommandToken("-q", "no header"),
            CommandToken("-k", "sort by these fields"),
            CommandToken("-o", "pick the columns"),
            CommandToken("-O", "add columns to the default set"),
            CommandToken("-p", "only these process ids"),
            CommandToken("-u", "only these users"),
            CommandToken("-s", "sort by this column number"),
            CommandToken("-S", "cumulative time of dead children")
        )
    )
)

private val SETTINGS_NAMESPACES
    get() = listOf(
        CommandToken("system", "per device user interface settings"),
        CommandToken("secure", "settings apps may read but not write"),
        CommandToken("global", "settings shared by all users")
    )

@Immutable
internal data class AdbSuggestion(
    val label: String,
    val hint: String,
    val input: String
)

/**
 * Suggestions for what may follow the command already typed: flags of that command, its sub
 * commands, and package names read from the device. Returns an empty list when the input does
 * not start with a known command, so the caller can fall back to plain command names.
 */
internal fun contextSuggestions(
    input: String,
    device: DeviceLists,
    limit: Int
): List<AdbSuggestion> {
    val spec = ADB_COMMAND_GRAMMAR
        .filter { input.startsWith(it.prefix + " ") }
        .maxByOrNull { it.prefix.length } ?: return emptyList()

    val token = input.substringAfterLast(' ')
    val head = input.dropLast(token.length)

    val settled = input.removePrefix(spec.prefix).trim().split(' ')
        .filter { it.isNotEmpty() }
        .let { if (token.isEmpty()) it else it.dropLast(1) }

    // Which positional slot is being typed - flags in between do not take a slot
    val slot = settled.count { !it.startsWith("-") }
    val typingFlag = token.startsWith("-")

    val fromDevice = if (typingFlag) emptyList() else when (spec.arguments.getOrNull(slot)) {
        ArgumentSource.PACKAGE -> device.packages
        ArgumentSource.PERMISSION -> device.permissions
        ArgumentSource.SERVICE -> device.services
        ArgumentSource.DUMPSYS_SERVICE -> device.dumpsysServices
        null -> emptyList()
    }

    // What goes in the next slot is more useful than a flag, so it comes first
    val candidates = sequence {
        if (!typingFlag && slot == 0) yieldAll(spec.words)
        yieldAll(fromDevice.map { CommandToken(it) })
        yieldAll(spec.options)
    }

    val used = settled.toSet()

    return candidates
        .filter {
            // A flag already in the line must not be offered twice
            it.text.startsWith(token, ignoreCase = true) && it.text != token && it.text !in used
        }
        .distinctBy { it.text }
        .take(limit)
        .map { AdbSuggestion(label = it.text, hint = it.hint, input = head + it.text + " ") }
        .toList()
}

private val GRAMMAR_HINTS: Map<String, String> =
    ADB_COMMAND_GRAMMAR.filter { it.hint.isNotEmpty() }.associate { it.prefix to it.hint }

// Every command the grammar knows is offered by name, so its flags become reachable
internal val GRAMMAR_COMMANDS: List<CommandToken> =
    ADB_COMMAND_GRAMMAR.map { CommandToken(it.prefix, it.hint) }

internal fun commandHint(command: String): String =
    GRAMMAR_HINTS[command]
        ?: DICTIONARY_HINTS[command]
        ?: DEVICE_COMMAND_HINTS[command.substringBefore(' ')]
        ?: ""

// Short notes for the binaries read from the device - the rest stay without a hint
internal val DEVICE_COMMAND_HINTS: Map<String, String> = mapOf(
    "sh" to "shell",
    "toybox" to "the box that holds most shell tools",
    "toolbox" to "legacy box of shell tools",
    "getprop" to "read system properties",
    "setprop" to "write a system property",
    "am" to "activity manager",
    "pm" to "package manager",
    "wm" to "window manager control",
    "svc" to "power, data, wifi and usb control",
    "cmd" to "call a system service",
    "service" to "list and call binder services",
    "dumpsys" to "dump a system service",
    "logcat" to "system log",
    "log" to "write a line to the system log",
    "settings" to "read and write system settings",
    "input" to "send input events",
    "monkey" to "random event stress test",
    "content" to "content provider client",
    "appops" to "per app operation permissions",
    "dpm" to "device policy manager",
    "bmgr" to "backup manager",
    "bu" to "backup and restore over adb",
    "ime" to "input method control",
    "locksettings" to "lock screen settings",
    "telecom" to "telecom service control",
    "sm" to "storage manager",
    "vdc" to "volume daemon client",
    "ndc" to "network daemon client",
    "hid" to "emulate a hid device",
    "uiautomator" to "ui automation tests",
    "requestsync" to "request a sync of an account",
    "screencap" to "write a screenshot to a file",
    "screenrecord" to "record the screen to a file",
    "bugreport" to "collect a full bug report",
    "bugreportz" to "bug report as a zip file",
    "dumpstate" to "collect device state for a bug report",
    "incident" to "incident report",
    "atrace" to "system trace",
    "perfetto" to "system trace with perfetto",
    "simpleperf" to "cpu profiler",
    "simpleperf_app_runner" to "run simpleperf for a debuggable app",
    "lshal" to "list hal services",
    "getevent" to "read raw input events",
    "sendevent" to "write a raw input event",
    "tinyplay" to "play a wav file to an audio device",
    "tinycap" to "record from an audio device",
    "tinymix" to "audio mixer controls",
    "tinypcminfo" to "audio pcm device info",
    "reboot" to "restart the device",
    "stop" to "stop the android framework",
    "start" to "start the android framework",
    "run-as" to "run a command as a debuggable app",
    "sqlite3" to "sqlite database shell",
    "app_process" to "start a java class in the android runtime",
    "app_process64" to "start a java class in the android runtime",
    "dex2oat" to "compile dex to native code",
    "dexdump" to "dump a dex file",
    "dexlist" to "list methods of a dex file",
    "oatdump" to "dump an oat file",
    "profman" to "profile manager of the runtime",
    "idmap2" to "resource overlay id maps",
    "snapshotctl" to "control system update snapshots",
    "gsi_tool" to "generic system image control",
    "lpdump" to "dump logical partitions",
    "e2fsck" to "check an ext4 file system",
    "resize2fs" to "resize an ext4 file system",
    "mke2fs" to "create an ext4 file system",
    "tune2fs" to "change ext4 file system settings",
    "fsck.f2fs" to "check an f2fs file system",
    "make_f2fs" to "create an f2fs file system",
    "sload_f2fs" to "load files into an f2fs image",
    "fsck_msdos" to "check a fat file system",
    "newfs_msdos" to "create a fat file system",
    "sgdisk" to "gpt partition table tool",
    "applypatch" to "apply a system update patch",
    "uncrypt" to "prepare an update package for recovery",
    "cat" to "print a file",
    "tac" to "print a file last line first",
    "head" to "first lines of a file",
    "tail" to "last lines of a file",
    "more" to "page through a file",
    "less" to "page through a file",
    "grep" to "filter lines by a pattern",
    "egrep" to "filter lines by an extended pattern",
    "fgrep" to "filter lines by a fixed string",
    "sed" to "stream editor",
    "awk" to "pattern scanning and processing",
    "cut" to "cut columns from lines",
    "paste" to "join lines side by side",
    "sort" to "sort lines",
    "uniq" to "drop repeated lines",
    "wc" to "count lines, words and bytes",
    "tr" to "replace characters",
    "rev" to "reverse each line",
    "nl" to "number lines",
    "fmt" to "reformat paragraphs",
    "expand" to "tabs to spaces",
    "split" to "split a file into pieces",
    "comm" to "compare two sorted files",
    "cmp" to "compare two files byte by byte",
    "diff" to "compare two files line by line",
    "patch" to "apply a diff",
    "strings" to "printable strings in a binary",
    "od" to "octal dump of a file",
    "xxd" to "hex dump of a file",
    "hexdump" to "hex dump of a file",
    "base64" to "base64 encode or decode",
    "uuencode" to "uuencode a file",
    "uudecode" to "decode a uuencoded file",
    "md5sum" to "md5 checksum",
    "sha1sum" to "sha1 checksum",
    "sha224sum" to "sha224 checksum",
    "sha256sum" to "sha256 checksum",
    "sha384sum" to "sha384 checksum",
    "sha512sum" to "sha512 checksum",
    "cksum" to "crc checksum",
    "iconv" to "convert text encoding",
    "dos2unix" to "crlf to lf line ends",
    "unix2dos" to "lf to crlf line ends",
    "vi" to "text editor",
    "echo" to "print arguments",
    "printf" to "formatted print",
    "yes" to "repeat a line forever",
    "seq" to "print a sequence of numbers",
    "expr" to "evaluate an expression",
    "true" to "exit with success",
    "false" to "exit with failure",
    "test" to "evaluate a condition",
    "ls" to "list a directory",
    "find" to "search the file tree",
    "file" to "guess the type of a file",
    "stat" to "file status and times",
    "du" to "size of a directory",
    "df" to "free disk space",
    "cp" to "copy files",
    "mv" to "move or rename files",
    "rm" to "remove files",
    "rmdir" to "remove an empty directory",
    "mkdir" to "create a directory",
    "ln" to "create a link",
    "unlink" to "remove one file",
    "touch" to "create a file or update its time",
    "truncate" to "set the size of a file",
    "fallocate" to "reserve space for a file",
    "install" to "copy files and set their mode",
    "mktemp" to "create a temporary file",
    "mkfifo" to "create a named pipe",
    "mknod" to "create a device node",
    "makedevs" to "create device nodes from a table",
    "chmod" to "change file permissions",
    "chown" to "change the file owner",
    "chgrp" to "change the file group",
    "chcon" to "change the selinux context of a file",
    "restorecon" to "restore the selinux context of a file",
    "getfattr" to "read extended attributes",
    "setfattr" to "write extended attributes",
    "lsattr" to "list file attributes",
    "chattr" to "change file attributes",
    "readlink" to "target of a symbolic link",
    "realpath" to "absolute path without links",
    "basename" to "file name part of a path",
    "dirname" to "directory part of a path",
    "pwd" to "current directory",
    "tar" to "tape archive",
    "cpio" to "cpio archive",
    "gzip" to "compress with gzip",
    "gunzip" to "expand a gzip file",
    "zcat" to "print a gzip file",
    "bzcat" to "print a bzip2 file",
    "xzcat" to "print an xz file",
    "unzip" to "extract a zip file",
    "zip" to "create a zip file",
    "dd" to "copy blocks between files",
    "sync" to "flush writes to disk",
    "fsync" to "flush one file to disk",
    "mount" to "mounted file systems",
    "umount" to "unmount a file system",
    "mountpoint" to "check that a path is a mount point",
    "losetup" to "loop devices",
    "blkid" to "block device ids and types",
    "blockdev" to "block device settings",
    "swapon" to "enable a swap area",
    "swapoff" to "disable a swap area",
    "mkswap" to "create a swap area",
    "readahead" to "preload a file into the cache",
    "ps" to "running processes",
    "top" to "live process list",
    "iotop" to "live process io",
    "pgrep" to "find processes by name",
    "pkill" to "kill processes by name",
    "pidof" to "pid of a process by name",
    "kill" to "send a signal to a process",
    "killall" to "kill processes by name",
    "pmap" to "memory map of a process",
    "lsof" to "open files of processes",
    "nice" to "run with a changed priority",
    "renice" to "change the priority of a process",
    "ionice" to "run with a changed io priority",
    "iorenice" to "change the io priority of a process",
    "chrt" to "run with a realtime scheduling policy",
    "taskset" to "cpu affinity of a process",
    "nohup" to "run immune to hangup",
    "setsid" to "run in a new session",
    "timeout" to "run with a time limit",
    "time" to "time a command",
    "watch" to "run a command repeatedly",
    "sleep" to "wait for seconds",
    "usleep" to "wait for microseconds",
    "nsenter" to "run in another namespace",
    "unshare" to "run in a new namespace",
    "chroot" to "run with another root directory",
    "runcon" to "run in a selinux context",
    "env" to "run with a changed environment",
    "printenv" to "print environment variables",
    "xargs" to "build commands from input",
    "tee" to "copy input to a file and to output",
    "which" to "path of a command",
    "flock" to "run under a file lock",
    "logwrapper" to "run a command and send its output to logcat",
    "free" to "memory in use",
    "vmstat" to "virtual memory statistics",
    "uptime" to "time since the last boot",
    "date" to "device clock",
    "hwclock" to "hardware clock",
    "cal" to "calendar",
    "uname" to "kernel name and version",
    "hostname" to "device host name",
    "nproc" to "number of cpus",
    "getconf" to "system configuration values",
    "sysctl" to "kernel parameters",
    "dmesg" to "kernel log",
    "lsmod" to "loaded kernel modules",
    "modinfo" to "kernel module info",
    "insmod" to "load a kernel module",
    "rmmod" to "unload a kernel module",
    "modprobe" to "load a kernel module with its dependencies",
    "lspci" to "pci devices",
    "lsusb" to "usb devices",
    "acpi" to "battery and thermal state",
    "devmem" to "read or write physical memory",
    "getenforce" to "selinux mode",
    "setenforce" to "set the selinux mode",
    "load_policy" to "load a selinux policy",
    "id" to "current user and groups",
    "whoami" to "current user name",
    "logname" to "login name",
    "groups" to "groups of the current user",
    "ulimit" to "resource limits",
    "tty" to "name of the terminal",
    "stty" to "terminal settings",
    "clear" to "clear the screen",
    "reset" to "reset the terminal",
    "microcom" to "serial port terminal",
    "ping" to "check that a host answers",
    "ping6" to "check that an ipv6 host answers",
    "traceroute" to "route to a host",
    "traceroute6" to "route to an ipv6 host",
    "ip" to "network interfaces and routes",
    "iptables" to "ipv4 packet filter",
    "ip6tables" to "ipv6 packet filter",
    "iptables-restore" to "load ipv4 filter rules",
    "ip6tables-restore" to "load ipv6 filter rules",
    "iptables-save" to "dump ipv4 filter rules",
    "ip6tables-save" to "dump ipv6 filter rules",
    "tc" to "traffic control",
    "ss" to "socket statistics",
    "netstat" to "network connections",
    "ifconfig" to "network interface settings",
    "route" to "kernel routing table",
    "arp" to "arp cache",
    "nc" to "read and write network connections",
    "netcat" to "read and write network connections",
    "nbd-client" to "network block device client",
    "tunctl" to "tun and tap devices",
    "vconfig" to "vlan interfaces",
    "iw" to "wireless device control",
    "wpa_cli" to "wpa supplicant control",
    "dnsmasq" to "dns and dhcp server",
    "hostapd_cli" to "access point daemon control",
    "curl" to "transfer a url",
    "wget" to "download a url",
    "uuidgen" to "generate a uuid",
    "inotifyd" to "run a command on file events"
)
