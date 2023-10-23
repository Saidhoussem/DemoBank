plugins {
    id("demobank.android.application")
    id("demobank.android.compose")


}

android {
    namespace = "com.demo.bank"

    defaultConfig {
        applicationId = "com.demo.bank"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =("androidx.test.runner.AndroidJUnitRunner")
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }



    packaging {
        resources {
            resources.excludes.add("./META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation (libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation.compose)
    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":feature:account")))
    implementation(project(mapOf("path" to ":feature:converter")))
    implementation(project(mapOf("path" to ":feature:simulator")))
    implementation(project(mapOf("path" to ":core:domain")))
    implementation(project(mapOf("path" to ":core:model")))

}