package org.asyncstorage.sqlite.plugin.extensions

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.gradle.api.Project
import java.io.File

/**
 * Read-only extension providing the library release info, based on
 * package.json in root.
 */
abstract class PackageInfoExtension(versionJson: PackageInfoJsonContent) {
    val name = versionJson.name
    val version = versionJson.version
    val description = versionJson.description
    val homepage = versionJson.homepage
    val author = versionJson.author
    val repository = versionJson.repository
    val license = versionJson.license
    val darwin = versionJson.darwin
    val android = versionJson.android
}

internal fun Project.createPackageInfoExtension(): PackageInfoExtension {
    val jsonFile = File(rootDir, "package_info.json")
    val packageInfoJson = Gson().fromJson(jsonFile.reader(), PackageInfoJsonContent::class.java)
    return extensions.create("packageInfo", PackageInfoExtension::class.java, packageInfoJson)
}


data class PackageInfoJsonContent(
    val name: String,
    val version: String,
    val description: String,
    val homepage: String,
    val author: String,
    val repository: String,
    val license: LicenseContent,
    val darwin: DarwinContent,
    val android: AndroidContent
)

data class LicenseContent(
    val type: String,
    val url: String,
    val date: String
)

data class DarwinContent(
    @SerializedName("podspec_name")
    val podspecName: String,
    @SerializedName("xcframework_name")
    val xcframeworkName: String
)

data class AndroidContent(
    val group: String,
    @SerializedName("artifact_id")
    val artifactId: String
)
