plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.plugin.serialization).apply(false)
    alias(libs.plugins.sqldelight).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.nativecoroutines).apply(false)
}