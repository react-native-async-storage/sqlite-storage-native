package org.asyncstorage.sqlite.plugin.extensions

import com.google.gson.Gson
import org.gradle.api.Project
import java.io.File

data class PackageJsonContent(
    var version: String,
    val description: String,
    val homepage: String,
    val license: String,
    val author: String,
    val repository: Repository,
) {
    data class Repository(
        val url: String,
        val owner: String,
        val name: String,
    )
}

/**
 * Read-only extension providing the library release info, based on
 * package.json in root.
 */
abstract class PackageInfoExtension(private val pckJson: PackageJsonContent) {
    val group = "org.asyncstorage"
    val version: String
        get() = pckJson.version

    val description: String
        get() = pckJson.description
    val homepage: String
        get() = pckJson.homepage
    val license: String
        get() = pckJson.license
    val author: String
        get() = pckJson.author
    val repoUrl: String
        get() = pckJson.repository.url
    val repoOwner: String
        get() = pckJson.repository.owner
    val repoName: String
        get() = pckJson.repository.name
}

internal fun Project.createExtensionWithPackageJsonContent(): PackageInfoExtension {
    val packageJsonFile = File(rootDir, "package.json")
    val pckJson = Gson().fromJson(packageJsonFile.reader(), PackageJsonContent::class.java)
    return extensions.create("packageInfo", PackageInfoExtension::class.java, pckJson)
}
