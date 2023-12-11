package org.asyncstorage.sqlite.plugin

import org.asyncstorage.sqlite.plugin.extensions.createExtensionWithPackageJsonContent
import org.gradle.api.Plugin
import org.gradle.api.Project

class StoragePlugin : Plugin<Project> {
    override fun apply(project: Project) =
        with(project) {
            val infoExt = createExtensionWithPackageJsonContent()

            group = infoExt.group
            version = infoExt.version

            project.configureTesting()
            project.configureLint()
        }
}

internal const val PLUGIN_GROUP = "Async Storage Sqlite"
