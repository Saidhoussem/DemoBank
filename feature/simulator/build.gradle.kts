@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("demobank.android.feature")
}

android {
    namespace = "com.demo.bank.contact"

}

dependencies {
    implementation(libs.androidx.navigation.compose)

}