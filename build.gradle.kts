


buildscript {
    repositories {
        google()
        mavenCentral()
    }
}


// Lists of all plugins to used in the project without applying them.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false

}


task("clean", Delete::class) {
    delete(rootProject.buildDir)
}

