@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("demobank.android.library")
}

android {
    namespace = "com.demo.banck.data"
}

dependencies {

    implementation(project(mapOf("path" to ":core:datasource")))
    implementation(project(mapOf("path" to ":core:model")))

    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
}