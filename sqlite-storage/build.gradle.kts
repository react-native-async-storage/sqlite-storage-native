import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("sqlite-storage-plugin")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ksp)
    alias(libs.plugins.nativecoroutines)
}


kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }
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
            compileTaskProvider.get().compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    jvmToolchain(17)

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
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
