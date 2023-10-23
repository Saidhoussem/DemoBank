@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("demobank.android.library")
}

android {
    namespace = "com.demobank.model"
}

dependencies {

    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
}