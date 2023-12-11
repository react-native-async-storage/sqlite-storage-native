plugins {
    id("com.android.application")
    kotlin("android")
}


android {
    compileSdk = 33
    namespace = "com.asyncstorage.sqlite.android.example"
    defaultConfig {
        applicationId = "com.asyncstorage.sqlite.android.example"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    kotlin {
        jvmToolchain(17)
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    implementation(project(":sqlite-storage"))
    implementation(libs.coroutines.android)
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation ("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation ("androidx.compose.material:material:1.4.3")
}