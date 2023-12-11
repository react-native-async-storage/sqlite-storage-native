pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

rootProject.name = "SQLiteStorage"
include(":sqlite-storage")
include(":sample-android")
project(":sample-android").projectDir = File(rootDir, "samples/android")

includeBuild("sqlite-storage-plugin")
