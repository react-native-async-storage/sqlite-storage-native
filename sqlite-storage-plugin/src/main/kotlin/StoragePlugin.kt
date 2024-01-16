package org.asyncstorage.sqlite.plugin

import org.asyncstorage.sqlite.plugin.extensions.BundleExtension
import org.asyncstorage.sqlite.plugin.extensions.createPackageInfoExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class StoragePlugin : Plugin<Project> {
    override fun apply(project: Project) =
        with(project) {
            val infoExt = createPackageInfoExtension()
            val bundleExt =
                project.extensions.create("sqliteStorage", BundleExtension::class.java, project)

            group = infoExt.android.group
            version = infoExt.version

            project.configureTesting()
            project.configureLint()
            project.configureBundle(bundleExt, infoExt)
        }
}

internal const val PLUGIN_GROUP = "SQLite Storage"
