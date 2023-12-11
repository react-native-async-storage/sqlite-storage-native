plugins {
    `kotlin-dsl`
    id("com.diffplug.spotless").version(libs.versions.spotless)
}

gradlePlugin {
    plugins {
        register(
            "StorageConventionPlugin"
        ) {
            id = "sqlite-storage-plugin"
            implementationClass = "org.asyncstorage.sqlite.plugin.StoragePlugin"
        }
    }
}

spotless {
    kotlin {
        ktlint("1.0.1")
        target("src/**/*.kt")
    }
}

kotlin {
    jvmToolchain(11)
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(libs.gradlePlugin.testLogger)
    implementation(libs.gradlePlugin.kotlin)
    implementation(libs.gradlePlugin.spotless)
    implementation(libs.gson)
}
