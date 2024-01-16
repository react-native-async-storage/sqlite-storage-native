package org.asyncstorage.sqlite.plugin.extensions

import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class BundleExtension(
    @Inject private val project: Project,
) {
    val iosOutputDir: DirectoryProperty = project.objects.directoryProperty()
    val binaryName: Property<String> = project.objects.property(String::class.java)
}
