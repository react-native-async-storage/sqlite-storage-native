import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("sqlite-storage-plugin")
    id("app.cash.sqldelight")
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines")
}


kotlin {
    androidTarget()
    val xcf = XCFramework(packageInfo.darwin.xcframeworkName)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = packageInfo.darwin.xcframeworkName
            xcf.add(this)
        }
    }
    // disable expect-actual warning
    targets.all {
        compilations.all {
            compilerOptions.configure {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }


    jvmToolchain(11)

    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }

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

sqliteStorage {
    iosOutputDir.set(rootProject.layout.projectDirectory.dir(packageInfo.darwin.podspecName).asFile)
    binaryName.set(packageInfo.darwin.xcframeworkName)
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
