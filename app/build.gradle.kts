plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.baselineprofile)
    id(libs.plugins.googleServices.get().pluginId)
    id(libs.plugins.firebase.crashlytics.get().pluginId)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.salat.gbinder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.salat.gbinder"
//        applicationId = "com.ecarx.hardkeytest"
        minSdk = 26
        targetSdk = 35
        versionCode = 1440
        versionName = "4.0"

        setProperty("archivesBaseName", "$versionName[$versionCode]GInputBridge")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    flavorDimensions += "env"

    productFlavors {
        create("prod") { dimension = "env" }
        create("emu") {
            dimension = "env"
            versionNameSuffix = "-emu"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    // Automatic signing and assembly
    // 1) Copy and rename the file "_secure.signing.gradle" to "secure.signing.gradle"
    // 2) You can copy it to any location and specify the path to it in the "gradle.properties" file
    // 3) Specify the necessary values to sign all builds of the application
    // 4) Run the command in the terminal "./gradlew prepareRelease"
    // 5) Wait and pick up all builds from "app/build/outputs/apk/" and "app/build/outputs/bundle/"
    // https://www.timroes.de/handling-signing-configs-with-gradle
    if (project.hasProperty("secure.signing") && project.file(project.property("secure.signing") as String).exists()) {
        apply(project.property("secure.signing"))
    }
}

androidComponents {
    onVariants(
        selector()
            .withBuildType("nonMinifiedRelease")
            .withFlavor("env" to "prod")
    ) { v ->
        // use the project's DependencyHandler to add deps to specific configurations
        val dh = project.dependencies

        fun proj(path: String) = dh.project(mapOf("path" to path))

        // put classes into the variant's compile classpath
        dh.add(v.compileConfiguration.name,  proj(":adaptapi"))
        dh.add(v.compileConfiguration.name,  proj(":ecarx_car"))
        dh.add(v.compileConfiguration.name,  proj(":ecarx_fw"))

        // and into the runtime classpath
        dh.add(v.runtimeConfiguration.name,  proj(":adaptapi"))
        dh.add(v.runtimeConfiguration.name,  proj(":ecarx_car"))
        dh.add(v.runtimeConfiguration.name,  proj(":ecarx_fw"))
    }
}

dependencies {
    implementation(project(":core:coroutines"))
    implementation(project(":core:car"))
    implementation(project(":core:stateKeeper"))
    implementation(project(":core:remoteConfig"))
    implementation(project(":core:filedownloader"))

    implementation(project(":com_geely"))

    // For release build – compileOnly (used at compile time, not included in the final APK)
//    add("releaseCompileOnly", project(":adaptapi"))
//    add("releaseCompileOnly", project(":ecarx_car"))
//    add("releaseCompileOnly", project(":ecarx_fw"))
    add("releaseImplementation", project(":adaptapi"))
    add("releaseImplementation", project(":ecarx_car"))
    add("releaseImplementation", project(":ecarx_fw"))

    add("emuImplementation", project(":adaptapi"))
    add("emuImplementation", project(":ecarx_car"))
    add("emuImplementation", project(":ecarx_fw"))

    // For debug build – implementation (fully included)
    add("debugImplementation", project(":adaptapi"))
    add("debugImplementation", project(":ecarx_car"))
    add("debugImplementation", project(":ecarx_fw"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.media)
    implementation(libs.material)
    implementation(libs.coil.compose)
    // implementation(libs.reorderable)
    implementation(libs.adb.shell)
    implementation(libs.timber)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.firestore)
    // implementation(libs.firebase.config)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.compose)

    "baselineProfile"(project(":baselineprofile"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

afterEvaluate {
    tasks.matching { it.name == "kspNonMinifiedReleaseKotlin" }.all {
        dependsOn("kspReleaseKotlin")
    }
}

// -----------------------------------------------------
// Create release BP and assemble release
// -----------------------------------------------------

// Step 1: Create release profiles
tasks.register("prepareRelease") {
    dependsOn(":app:generateProdReleaseBaselineProfile")
    finalizedBy("assembleReleaseBuild")
}

tasks.register("assembleReleaseBuild").get()
    .dependsOn(":app:assembleProdRelease")