plugins {
    id("com.android.application").version(libs.versions.agp.get()).apply(false)
    id("com.android.library").version(libs.versions.agp.get()).apply(false)
    kotlin("android").version(libs.versions.kotlin.get()).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin.get()).apply(false)
    kotlin("plugin.serialization").version(libs.versions.kotlin.get()).apply(false)
    id("app.cash.sqldelight").version(libs.versions.sqldelight.get()).apply(false)
    id("com.google.devtools.ksp").version(libs.versions.ksp.get()).apply(false)
    id("com.rickclephas.kmp.nativecoroutines").version(libs.versions.nativeCoroutines.get())
        .apply(false)
}
