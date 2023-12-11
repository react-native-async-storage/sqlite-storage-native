package org.asyncstorage.sqlite.plugin

import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.TestLoggerPlugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering

fun Project.configureTesting() {
    project.apply {
        plugin(TestLoggerPlugin::class.java)
    }
    (project as ExtensionAware).extensions.configure<TestLoggerExtension>(
        "testlogger",
    ) {
        theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
        showExceptions = true
        showStackTraces = false
        showFullStackTraces = true
        showCauses = true
        slowThreshold = 5000
        showSummary = true
        showSimpleNames = true
        showPassed = true
        showSkipped = true
        showFailed = true
        showOnlySlow = false
        showStandardStreams = false
        showPassedStandardStreams = true
        showSkippedStandardStreams = true
        showFailedStandardStreams = true
        logLevel = LogLevel.LIFECYCLE
    }
    val storageTestsAndroid by tasks.registering {
        group = PLUGIN_GROUP
        description = "Runs tests for Android"
        dependsOn(
            tasks.getByName("testReleaseUnitTest"),
        )
    }
    val storageTestsIos by tasks.registering {
        group = PLUGIN_GROUP
        description = "Runs tests for iOS"
        dependsOn(
            tasks.getByName("iosSimulatorArm64Test"),
            tasks.getByName("iosX64Test"),
        )
    }

    tasks.register("storageTests") {
        description = "Runs all tests for SQLiteStorage"
        group = PLUGIN_GROUP
        dependsOn(
            storageTestsIos,
            storageTestsAndroid,
        )
    }
}
