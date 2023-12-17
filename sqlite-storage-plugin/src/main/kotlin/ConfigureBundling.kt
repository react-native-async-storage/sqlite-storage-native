package org.asyncstorage.sqlite.plugin

import org.asyncstorage.sqlite.plugin.tasks.SqliteStorageAndroidBundle
import org.asyncstorage.sqlite.plugin.extensions.BundleExtension
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register


private const val BUNDLE_TASK_NAME = "bundleSqliteStorage"

fun Project.configureBundle(bundleConfig: BundleExtension) {
    val androidBundle = configureAndroidBundle(bundleConfig)


    tasks.register(BUNDLE_TASK_NAME) {
        group = PLUGIN_GROUP
        description = "Builds final binaries for supported platforms"
        dependsOn(androidBundle)
    }
}


private fun Project.configureAndroidBundle(bundleConfig: BundleExtension): TaskProvider<SqliteStorageAndroidBundle> {
    val androidBundle = tasks.register<SqliteStorageAndroidBundle>("${BUNDLE_TASK_NAME}Android") {
        outputDir.set(bundleConfig.outputDir)
        outputAarName.set(bundleConfig.androidAarName)
        sourceAarLocation.set(project.layout.buildDirectory.file("outputs/aar/sqlite-storage-release.aar"))
    }
    afterEvaluate {
        androidBundle.configure {
            dependsOn(tasks.named("bundleReleaseAar"))
        }
    }

    return androidBundle
}


