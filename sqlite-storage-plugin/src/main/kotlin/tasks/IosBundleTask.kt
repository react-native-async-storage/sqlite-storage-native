package org.asyncstorage.sqlite.plugin.tasks

import org.asyncstorage.sqlite.plugin.PLUGIN_GROUP
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

internal abstract class IosBundleTask : DefaultTask() {
    override fun getGroup() = PLUGIN_GROUP

    override fun getDescription() = "Creates release XCFramework and moves it to output dir"

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val outputFrameworkName: Property<String>

    @get:InputDirectory
    abstract val sourceFramework: DirectoryProperty

    @TaskAction
    fun execute() {
        val framework = sourceFramework.get().asFile
        if (!framework.exists()) {
            throw IllegalStateException("XCFramework not found at ${framework.absolutePath}")
        }
        val finalName: String =
            outputFrameworkName.get().let { name ->
                if (name.endsWith(".xcframework")) {
                    name
                } else {
                    "$name.xcframework"
                }
            }

        framework.copyRecursively(
            target = outputDir.file(finalName).get().asFile,
            overwrite = true,
        )
    }
}
