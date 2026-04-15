# GInputBridge

**GInputBridge** (Git repository `gbinder`, application id `com.salat.gbinder`) is an Android app for automotive head units. It combines a **custom launcher** (grid of apps, recents, theming), **hardware key and media bridging**, **system overlays** (drive mode, task manager, and related UI), and **vendor integration** (Geely / EcarX stacks) behind a single product.

## Support the project

If this app helps you, you can support further development, maintenance, and new features.

[![Donate](https://img.shields.io/badge/Donate-CloudTips-orange?style=for-the-badge)](https://pay.cloudtips.ru/p/19d38600)

### Crypto
- **BTC:** `bc1q37z3d7avhsq3ehpsjm2wldj86ajsnsd6gqnkzm`
- **ETH:** `0x69C73C422FEBBf12F47C29C51501Ad659fcdf74A`

Thanks for supporting the project.

## What’s in the app

**Launcher**

- A dedicated launcher surface: **`LauncherOverlayService`** draws a full-screen **Compose** overlay (`TYPE_APPLICATION_OVERLAY`) with “My apps”, **All apps**, optional **recents**, launcher settings, and theme options (including light/dark scheduling).
- **`LauncherEntryActivity`** (with an **`AppLauncher`** activity-alias) exposes a second entry point so the app can act as a **home-style launcher** alongside **`MainActivity`**, which remains the main settings / configuration entry (`MAIN`/`LAUNCHER`).
- Launcher data (tiles, layout, favorites) is backed by **DataStore** (`LauncherPrefs`, `LauncherStorageRepository`) and **Coil** for icons; utilities cover icon backup, file URIs, and time-based behavior.

**Input, media, and system glue**

- Central logic in **`App`** wires key state, media sessions, and global flows (`GlobalState`, `StateKeeper`).
- **`MediaNotificationListenerService`** and media helpers integrate with playback and notifications; key binding UIs and entities (`KeyBindConfig`, `KeyState`, etc.) map physical controls to actions.

**Overlays and auxiliary UI**

- Foreground services such as **`DriveModeOverlayService`**, **`TaskManagerOverlayService`**, **`LampModeOverlayService`**, plus dialogs like **`DashboardDialog`** — task switching, drive UI, and lamp-style overlays as needed on the HU.

**Configuration and tooling**

- **`MainActivity`** hosts the primary Compose UI for deep settings: key bindings, launcher-related options, import flows (e.g. **`.gibb`** file association on `MainActivity`), and feature toggles.
- **`features/configurator`** (`RenderConfigurator` and related) drives advanced configuration screens.
- **`adb`** integration (e.g. **`AdbRepository`**, `adb-shell` dependency) supports device-side automation and debugging workflows.

**Startup and platform hooks**

- **`BootAccessibilityService`**, **`BootContentProvider`**, **`BackgroundTaskReceiver`**, and related components align with boot and background execution on the target system.

**Other entry points**

- **`PhoneCallActivity`** and related flows for call-oriented UI when required on the platform.

**Data and services**

- **AndroidX DataStore** for preferences; **Firebase** (Analytics, Crashlytics, Firestore) as wired in `build.gradle.kts`.
- **`core:remoteConfig`**, **`core:stateKeeper`**, **`core:filedownloader`**, **`core:car`** for shared behavior across the app and head-unit context.

## Tech stack

- Kotlin, Coroutines, Flow  
- Jetpack Compose (Material 3), Navigation Compose  
- Hilt (DI), KSP  
- AndroidX DataStore  
- Coil, Timber  
- Firebase (Analytics, Crashlytics, Firestore)  
- Baseline Profile (`baselineprofile` module, `prepareRelease` pipeline)

## Module layout

**Vendor modules (`com_geely`, `adaptapi`, `ecarx_car`, `ecarx_fw`)** — **shadow classes** that mirror APIs shipped inside the **vendor’s system packages** on the head unit. They exist so the app can **compile and link** against the same class names and signatures as on the device, without bundling the real platform JARs. Runtime behavior still comes from the **system image**; classpath wiring for these modules is in **`app/build.gradle.kts`** (per build type / flavor).

## Requirements

- **JDK 17**
- **Android SDK**: `compileSdk` / `targetSdk` **35**, `minSdk` **26** (see `app/build.gradle.kts`)
- **Gradle Wrapper** (project uses Gradle **8.11.1** — see `gradle/wrapper/gradle-wrapper.properties`)
- **Android Studio** (or IntelliJ) with the Android plugin

## Build and run

From the repository root:

```bash
./gradlew :app:assembleProdDebug
```

On Windows:

```powershell
.\gradlew :app:assembleProdDebug
```

Install the APK from `app/build/outputs/apk/` via Android Studio **Run** or `adb install`.

## Release signing

Signing is **not** committed with real secrets. The project loads an optional Gradle script so keystores stay outside VCS.

1. **Template**  
   Copy **`app/_secure.signing.gradle`** to **`secure.signing.gradle`** next to the root `build.gradle` (or any path you prefer).

2. **Path**  
   Root **`gradle.properties`** already contains:
   - `secure.signing=secure.signing.gradle`  
   Point this property to your file if you place it elsewhere.

3. **Fill in keystores**  
   The template defines **`signingConfigs`** for **`release`** and **`nonMinifiedRelease`**: set `storeFile`, `storePassword`, `keyAlias`, and `keyPassword` for each.  
   Gradle **applies** this script only when `secure.signing` is set **and** the file exists (`app/build.gradle.kts`).

4. **Build types**  
   `release` builds use R8 shrink/obfuscation; signing from the script attaches to the appropriate release variants once configured.

Without a valid `secure.signing.gradle`, release signing steps are skipped; use debug builds for local work.

## Release pipeline (`prepareRelease`)

The root task **`prepareRelease`** (defined in `app/build.gradle.kts`):

1. Depends on **`:app:generateProdReleaseBaselineProfile`** (Baseline Profile for the **prod** flavor).
2. Then runs **`assembleReleaseBuild`**, which assembles **`:app:assembleProdRelease`**.

Invoke:

```bash
./gradlew prepareRelease
```

Artifacts: **APK** under `app/build/outputs/apk/`, **AAB** under `app/build/outputs/bundle/` (exact names depend on flavors and build type).

## Permissions and environment

The manifest requests, among others: **`INTERNET`**, **`ACCESS_NETWORK_STATE`**, **`QUERY_ALL_PACKAGES`**, **`FOREGROUND_SERVICE`** / **`FOREGROUND_SERVICE_MEDIA_PLAYBACK`**, **`SYSTEM_ALERT_WINDOW`**, **`WAKE_LOCK`**, **`READ_EXTERNAL_STORAGE`** (capped for older APIs), plus **Geely / OneOS** vendor permissions where integrated.

The app registers **accessibility** and **notification listener** services and uses **overlay** windows. Behavior is aimed at **automotive head units** with appropriate privileges; a stock phone or emulator may not expose the same APIs or policies.

## Naming

- **GInputBridge** — product name (`rootProject.name` / archives base name).  
- **`gbinder`** — repository / short name for the same codebase.
