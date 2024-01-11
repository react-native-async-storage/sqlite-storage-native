package org.asyncstorage.sqlite.plugin

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.asyncstorage.sqlite.plugin.extensions.PackageInfoExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure


// todo: configure task for easier publishing
fun Project.configurePublish(info: PackageInfoExtension) {
    (project as ExtensionAware).extensions.configure<MavenPublishBaseExtension> {
        publishToMavenCentral(SonatypeHost.S01)
        signAllPublications()

        coordinates(
            groupId = "io.github.react-native-async-storage",
            artifactId = "sqlite-storage",
            version = info.version
        )
        pom {
            name.set(info.name)
            description.set(info.description)
            inceptionYear.set("2023")
            url.set(info.repoUrl)
            licenses {
                license {
                    name.set("MIT")
                    url.set("https://github.com/react-native-async-storage/sqlite-storage/blob/main/LICENSE.md")
                }
            }
            developers {
                developer {
                    name.set("Krzysztof Borowy")
                    url.set("https://github.com/krizzu")
                }
            }
        }
    }
}
