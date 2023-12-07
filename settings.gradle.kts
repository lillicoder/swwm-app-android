@file:Suppress("UnstableApiUsage") // repositoriesMode{} and repositories{} are incubating

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SWWM"
include(":app")
include(":data:dialogs")
include(":domain:device")
include(":domain:dialogs")
include(":ui:about")
include(":ui:collections")
include(":ui:common")
include(":ui:dialogs")
include(":ui:recycler")
