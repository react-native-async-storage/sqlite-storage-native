package org.asyncstorage.sqlite.plugin

import org.asyncstorage.sqlite.plugin.extensions.BundleExtension
import org.asyncstorage.sqlite.plugin.tasks.SqliteStorageAndroidBundleTask
import org.asyncstorage.sqlite.plugin.tasks.SqliteStorageIosBundleTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register


private const val BUNDLE_TASK_NAME = "bundleSqliteStorage"

fun Project.configureBundle(bundleConfig: BundleExtension) {
    val androidBundle = configureAndroidBundle(bundleConfig)
    val iosBundle = configureIosBundle(bundleConfig)


    tasks.register(BUNDLE_TASK_NAME) {
        group = PLUGIN_GROUP
        description = "Builds final binaries for supported platforms"
        dependsOn(androidBundle)
        dependsOn(iosBundle)
    }
}


private fun Project.configureIosBundle(bundleConfig: BundleExtension): TaskProvider<SqliteStorageIosBundleTask> {
    val iosBundleTask = tasks.register<SqliteStorageIosBundleTask>("${BUNDLE_TASK_NAME}Ios") {
        outputDir.set(bundleConfig.outputDir)
        outputFrameworkName.set(bundleConfig.binaryName)
        sourceFramework.set(project.layout.buildDirectory.dir("XCFrameworks/release/SqliteStorage.xcframework"))
    }

    afterEvaluate {
        iosBundleTask.configure {
            dependsOn(tasks.named("assembleSqliteStorageReleaseXCFramework"))
        }
    }

    return iosBundleTask
}


private fun Project.configureAndroidBundle(bundleConfig: BundleExtension): TaskProvider<SqliteStorageAndroidBundleTask> {
    val androidBundleTask =
        tasks.register<SqliteStorageAndroidBundleTask>("${BUNDLE_TASK_NAME}Android") {
            outputDir.set(bundleConfig.outputDir)
            outputAarName.set(bundleConfig.binaryName)
            sourceAar.set(project.layout.buildDirectory.file("outputs/aar/sqlite-storage-release.aar"))
        }
    afterEvaluate {
        androidBundleTask.configure {
            dependsOn(tasks.named("bundleReleaseAar"))
        }
    }

    return androidBundleTask
}


