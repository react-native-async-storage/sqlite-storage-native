import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("sqlite-storage-plugin")
    id("app.cash.sqldelight")
}


kotlin {
    androidTarget()
    val xcf = XCFramework("SqliteStorage")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = "SqliteStorage"
            xcf.add(this)
        }
    }

    jvmToolchain(11)

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.common.main)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.common.test)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.bundles.android.main)
            }
        }
        getByName("androidUnitTest") {
            dependencies {
                implementation(libs.bundles.android.test)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.bundles.ios.main)
            }
        }
    }
}

sqldelight {
    databases {
        create("AsyncStorageDB") {
            packageName.set("org.asyncstorage.sqlitestorage.db")
            srcDirs("src/schema")
            dialect("app.cash.sqldelight:sqlite-3-24-dialect:${libs.versions.sqldelight.get()}")
        }
    }
}

asyncStorage {
    outputDir.set(rootProject.file("nativeLib"))
    binaryName.set("sqlite-storage")
}

android {
    compileSdk = 34
    namespace = "org.asyncstorage.sqlitestorage"
    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
