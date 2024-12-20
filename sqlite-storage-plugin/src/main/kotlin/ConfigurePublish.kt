package org.asyncstorage.sqlite.plugin

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.MavenPublishPlugin
import com.vanniktech.maven.publish.SonatypeHost
import org.asyncstorage.sqlite.plugin.extensions.PackageInfoExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure

internal fun Project.configurePublish(info: PackageInfoExtension) {
    project.apply {
        plugin(MavenPublishPlugin::class.java)
    }

    (project as ExtensionAware).extensions.configure<MavenPublishBaseExtension> {
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = false)
        signAllPublications()

        coordinates(
            groupId = info.android.group,
            artifactId = info.android.artifactId,
            version = info.version
        )
        pom {
            name.set(info.name)
            description.set(info.description)
            inceptionYear.set(info.license.date)
            url.set(info.repository)
            licenses {
                license {
                    name.set(info.license.type)
                    url.set(info.license.url)
                }
            }
            developers {
                developer {
                    name.set(info.author)
                }
            }
            scm {
                url.set(info.homepage)
            }
        }
    }
}
