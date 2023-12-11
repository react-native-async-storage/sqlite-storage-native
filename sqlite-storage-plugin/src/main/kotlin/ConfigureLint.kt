package org.asyncstorage.sqlite.plugin

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure

fun Project.configureLint() {
    project.apply {
        plugin(SpotlessPlugin::class.java)
    }

    (project as ExtensionAware).extensions.configure<SpotlessExtension> {
        kotlin {
            ktlint("1.0.1")
            target("src/**/*.kt")
        }
    }
}
