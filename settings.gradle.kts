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
include(":ui:core")
include(":ui:dialogs")
include(":ui:lifecycle")
include(":ui:recycler")
