pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GInputBridge"
include(":app")
include(":core")
include(":core:coroutines")
include(":core:car")
include(":core:stateKeeper")
include(":core:remoteConfig")
include(":core:filedownloader")
include(":com_geely")
include(":adaptapi")
include(":ecarx_car")
include(":ecarx_fw")
include(":baselineprofile")
