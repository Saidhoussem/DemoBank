@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("demobank.android.library")
    id("demobank.android.hilt")

}

android {
    namespace = "com.demo.domain"

}

dependencies {

    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":core:datasource")))
    implementation(project(mapOf("path" to ":core:model")))
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
}