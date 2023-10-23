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


    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.kotlin.serialization)



    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    kapt(libs.hilt.compiler)
    api(libs.hilt.android.testing)

    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
}