buildscript {
    val kotlinVersion = (rootProject.extra["kotlinVersion"] as String?) ?: "1.9.21"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        // noinspection DifferentKotlinGradleVersion
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}


plugins {
    id("com.android.library")
    kotlin("android")
    id("com.facebook.react").apply(false)
}

if (isNewArchitectureEnabled()) {
    apply(plugin = "com.facebook.react")
}

android {
    if (supportsNamespace()) {
        namespace = "org.reactnative.asyncstorage.sqlite"
    }

    buildFeatures {
        buildConfig = true
    }

    kotlin {
        jvmToolchain(11)
    }

    lint {
        disable += "GradleCompatible"
    }

    defaultConfig {
        compileSdk = getExtraIntOrDefault("compileSdkVersion", 34)
        minSdk = getExtraIntOrDefault("minSdkVersion", 21)
        buildConfigField(
            "boolean",
            "IS_NEW_ARCHITECTURE_ENABLED",
            isNewArchitectureEnabled().toString()
        )
    }

    sourceSets {
        all {
            if (isNewArchitectureEnabled()) {
                kotlin.srcDirs(
                    "src/newarch/kotlin",
                    "${project.buildDir}/generated/source/codegen/java"
                )
            } else {
                kotlin.srcDirs("src/oldarch/kotlin")
            }
        }
    }
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    // For < 0.71, this will be from the local maven repo
    // For > 0.71, this will be replaced by `com.facebook.react:react-android:$version` by react gradle plugin
    //noinspection GradleDynamicVersion
    implementation("com.facebook.react:react-native:+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
    implementation(files("../nativeLib/sqlite-storage-0.0.0.aar"))
    implementation("app.cash.sqldelight:android-driver:2.0.1")

}

fun supportsNamespace(): Boolean {
    val parsed = com.android.Version.ANDROID_GRADLE_PLUGIN_VERSION.split(".")
    val major = parsed[0].toInt()
    val minor = parsed[1].toInt()
    // Namespace support was added in 7.3.0
    return (major == 7 && minor >= 3) || major >= 8
}

fun isNewArchitectureEnabled() = getExtraOrDefault("newArchEnabled", "false") == "true"


fun getExtraOrDefault(name: String, default: String) =
    rootProject.extra.properties.getOrDefault(name, default).toString()

fun getExtraIntOrDefault(name: String, default: Int) =
    (rootProject.extra.properties[name]?.toString()?.toInt()) ?: default
