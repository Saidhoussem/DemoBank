@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("demobank.android.library")
    id("demobank.android.room")
    id("demobank.android.hilt")

}

android {
    namespace = "com.demo.bank.database"
    defaultConfig {
        testInstrumentationRunner =
            "com.demo.bank.BankTestRunner"
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.hilt.ext.work)


    androidTestImplementation(libs.androidx.test.core)

    testImplementation(libs.junit4)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    kapt(libs.hilt.compiler)
    api(libs.hilt.android.testing)

    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
}