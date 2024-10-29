plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose").version(libs.versions.kotlin)
    kotlin("android")
}


android {
    compileSdk = 34
    namespace = "com.asyncstorage.sqlite.android.example"
    defaultConfig {
        applicationId = "com.asyncstorage.sqlite.android.example"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(":sqlite-storage"))
    implementation(libs.coroutines.android)
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.5.4")
    implementation("androidx.compose.material:material:1.5.4")
    implementation("com.google.code.gson:gson:2.10.1")
}
