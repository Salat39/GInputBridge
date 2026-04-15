@file:Suppress("UnstableApiUsage")

plugins {
    id(libs.plugins.androidTest.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.salat.baselineprofile"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    defaultConfig {
        minSdk = 28
        targetSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        missingDimensionStrategy("env", "emu")
    }

    targetProjectPath = ":app"

    buildTypes {
        maybeCreate("release")
    }

    testOptions {
        managedDevices {
            localDevices {
                // images from c:\Users\%USER_NAME%\AppData\Local\Android\Sdk\system-images\

                create("pixel3axlapi28") {
                    // Use device profiles you typically see in Android Studio.
                    device = "Pixel 3a XL"
                    // Use only API levels 27 and higher.
                    apiLevel = 28
                    // To include Google services, use "google".
                    systemImageSource = "aosp"
                }

                create("pixel4api29") {
                    device = "Pixel 4"
                    apiLevel = 29
                    systemImageSource = "google"
                    require64Bit = false
                }

                create("mediumTablet30") {
                    device = "Medium Tablet"
                    apiLevel = 30
                    systemImageSource = "google"
//                    require64Bit = true
                }

                create("pixelcapi30") {
                    device = "Pixel C"
                    apiLevel = 30
                    systemImageSource = "google"
//                    require64Bit = true
                }

                create("pixel4aapi31") {
                    device = "Pixel 4a"
                    apiLevel = 31
                    systemImageSource = "google"
                    require64Bit = true
                }

                create("pixel2api32") {
                    device = "Pixel 2"
                    apiLevel = 32
                    systemImageSource = "google"
                    require64Bit = true
                }

                create("pixel6api33") {
                    device = "Pixel 6"
                    apiLevel = 33
                    systemImageSource = "aosp"
                }

                create("pixel6proapi34") {
                    device = "Pixel 6 Pro"
                    apiLevel = 34
                    systemImageSource = "google"
                    require64Bit = true
                }

                create("pixel8api35") {
                    device = "Pixel 8"
                    apiLevel = 35
                    systemImageSource = "google_apis_playstore"
                    require64Bit = true
                }

                create("pixel8proapi36") {
                    device = "Pixel 8 Pro"
                    apiLevel = 36
                    systemImageSource = "google_apis_playstore"
                    require64Bit = true
                }
            }
        }
    }
}

// This is the configuration block for the Baseline Profile plugin.
// You can specify to run the generators on a managed devices or connected devices.
baselineProfile {
    useConnectedDevices = false
//    managedDevices += "pixel3axlapi28"
//    managedDevices += "pixel4api29"
    managedDevices += "mediumTablet30"
//    managedDevices += "pixelcapi30"
//    managedDevices += "pixel4aapi31"
//    managedDevices += "pixel2api32"
//    managedDevices += "pixel6api33"
//    managedDevices += "pixel6proapi34"
//    managedDevices += "pixel8api35"
//    managedDevices += "pixel8proapi36"
}

dependencies {
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.uiautomator)
    implementation(libs.androidx.benchmark.macro.junit4)
}
