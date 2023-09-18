pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }
    }
}

rootProject.name = "Alarm"
include(":app")
include(":core:database")
include(":core:model")
include(":core:data")
include(":core:alarmscheduler")
include(":core:service")
include(":core:receiver")
include(":core:mediaplayer")
