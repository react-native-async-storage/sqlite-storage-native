package org.asyncstorage.sqlite.plugin

import org.asyncstorage.sqlite.plugin.extensions.BundleExtension
import org.asyncstorage.sqlite.plugin.extensions.PackageInfoExtension
import org.asyncstorage.sqlite.plugin.tasks.IosBundleTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register

private const val BUNDLE_TASK_NAME = "bundleSQLiteStorage"

fun Project.configureBundle(bundle: BundleExtension, info: PackageInfoExtension) {
    val iosBundle = configureIosBundle(bundle, info)

    tasks.register(BUNDLE_TASK_NAME) {
        group = PLUGIN_GROUP
        description = "Builds final binaries for supported platforms"
        dependsOn(iosBundle)
    }
}

private fun Project.configureIosBundle(
    bundle: BundleExtension,
    info: PackageInfoExtension
): TaskProvider<IosBundleTask> {
    val iosBundleTask =
        tasks.register<IosBundleTask>("${BUNDLE_TASK_NAME}Ios") {
            outputDir.set(bundle.iosOutputDir)
            outputFrameworkName.set(bundle.binaryName)
            sourceFramework.set(project.layout.buildDirectory.dir("XCFrameworks/release/${info.darwin.xcframeworkName}.xcframework"))
        }

    afterEvaluate {
        iosBundleTask.configure {
            dependsOn(tasks.named("assemble${info.darwin.xcframeworkName}ReleaseXCFramework"))
        }
    }

    return iosBundleTask
}

