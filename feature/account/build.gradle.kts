@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("demobank.android.feature")
}

android {
    namespace = "com.demo.bank.account"

}

dependencies {

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(project(mapOf("path" to ":core:model")))
    implementation(libs.material)
    implementation(project(mapOf("path" to ":core:domain")))
    implementation(project(mapOf("path" to ":core:data")))
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.reflect)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
}