package com.salat.baselineprofile

import android.os.SystemClock
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val PACKAGE_NAME = "com.salat.gbinder"
private const val TOGGLE_LAUNCHER_BROADCAST =
    "am broadcast -a $PACKAGE_NAME.TOGGLE_LAUNCHER -n $PACKAGE_NAME/.BackgroundTaskReceiver"
private const val UI_TIMEOUT_MS = 10_000L
private const val POLL_INTERVAL_MS = 250L

// Non-activity window of the package - the launcher overlay itself
private val OVERLAY_WINDOW = Regex("Window\\{[0-9a-f]+ u0 ${Regex.escape(PACKAGE_NAME)}\\}")

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generateStartup() {
        rule.collect(
            packageName = PACKAGE_NAME,
            includeInStartupProfile = true
        ) {
            pressHome()
            startActivityAndWait()
        }
    }

    @Test
    fun generateLauncherOverlay() {
        rule.collect(packageName = PACKAGE_NAME) {
            device.executeShellCommand("appops set $packageName SYSTEM_ALERT_WINDOW allow")
            warmUpProcess()
            openLauncherOverlay()
            toggleLockTwice()
            openAddAppsAndBack()
            openSettingsAndBack()
            browseAllApps()
            closeLauncherOverlay()
        }
    }
}

// The toggle flow has no replay - a cold process drops a toggle sent before the App collectors subscribe
private fun MacrobenchmarkScope.warmUpProcess() {
    pressHome()
    startActivityAndWait()
    pressHome()
}

private fun MacrobenchmarkScope.overlay(): BySelector = By.pkg(packageName)

private fun MacrobenchmarkScope.toggleLauncher() {
    device.executeShellCommand(TOGGLE_LAUNCHER_BROADCAST)
}

private fun MacrobenchmarkScope.isOverlayWindowShown(): Boolean =
    OVERLAY_WINDOW.containsMatchIn(device.executeShellCommand("dumpsys window windows"))

private fun MacrobenchmarkScope.waitOverlayWindow(shown: Boolean): Boolean {
    val deadline = SystemClock.uptimeMillis() + UI_TIMEOUT_MS
    while (SystemClock.uptimeMillis() < deadline) {
        if (isOverlayWindowShown() == shown) return true
        SystemClock.sleep(POLL_INTERVAL_MS)
    }
    return false
}

private fun MacrobenchmarkScope.openLauncherOverlay() {
    toggleLauncher()
    check(waitOverlayWindow(shown = true)) { "Launcher overlay window did not appear" }
    device.wait(Until.hasObject(overlay().desc("close")), UI_TIMEOUT_MS)
}

private fun MacrobenchmarkScope.closeLauncherOverlay() {
    val closeButton = device.findObject(overlay().desc("close"))
    if (closeButton != null) closeButton.click() else toggleLauncher()
    check(waitOverlayWindow(shown = false)) { "Launcher overlay window did not close" }
}

private fun MacrobenchmarkScope.toggleLockTwice() {
    repeat(2) {
        device.findObject(overlay().desc("unlock"))?.click()
        device.waitForIdle()
    }
}

// Back is safe only inside a sub screen or a menu - on the main screen it closes the overlay
private fun MacrobenchmarkScope.backFrom(selector: BySelector) {
    if (device.wait(Until.hasObject(selector), UI_TIMEOUT_MS)) {
        device.pressBack()
        device.wait(Until.hasObject(overlay().desc("close")), UI_TIMEOUT_MS)
    }
}

private fun MacrobenchmarkScope.openAddAppsAndBack() {
    device.findObject(overlay().desc("add"))?.click() ?: return
    device.wait(Until.findObject(overlay().text("Apps")), UI_TIMEOUT_MS)?.click() ?: return
    backFrom(overlay().desc("Back"))
}

private fun MacrobenchmarkScope.openSettingsAndBack() {
    device.findObject(overlay().desc("settings"))?.click() ?: return
    backFrom(overlay().desc("Back"))
}

private fun MacrobenchmarkScope.browseAllApps() {
    device.findObject(overlay().text("All"))?.click() ?: return
    device.waitForIdle()
    scrollable()?.scroll(Direction.DOWN, 1f)
    scrollable()?.scroll(Direction.UP, 1f)

    // Long press on the Settings app icon opens the app context menu
    device.findObject(overlay().desc("Settings"))?.longClick()
    backFrom(overlay().desc("menu icon"))

    // Horizontal swipes drive the tabs pager
    scrollable()?.scroll(Direction.LEFT, .8f)
    device.waitForIdle()
    scrollable()?.scroll(Direction.RIGHT, .8f)
    device.waitForIdle()
}

private fun MacrobenchmarkScope.scrollable(): UiObject2? =
    device.wait(Until.findObject(overlay().scrollable(true)), UI_TIMEOUT_MS)
