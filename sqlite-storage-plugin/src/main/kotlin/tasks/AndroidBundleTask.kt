package org.asyncstorage.sqlite.plugin.tasks

import org.asyncstorage.sqlite.plugin.PLUGIN_GROUP
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

internal abstract class SqliteStorageAndroidBundle : DefaultTask() {
    override fun getGroup() = PLUGIN_GROUP

    override fun getDescription() = "Bundles release AAR and moves it to release directory"

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val outputAarName: Property<String>

    @get:InputFile
    abstract val sourceAarLocation: RegularFileProperty

    @TaskAction
    fun execute() {
        val aar = sourceAarLocation.get().asFile
        if (!aar.exists()) {
            throw IllegalStateException("AAR file not built")
        }
        val finalAarName = outputAarName.get().let { currentName ->
            if (currentName.endsWith(".aar")) {
                currentName
            } else {
                "${currentName}.aar"
            }
        }

        aar.copyTo(target = outputDir.file(finalAarName).get().asFile, overwrite = true)
    }
}
