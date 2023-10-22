plugins {
    `kotlin-dsl`
}

group = "com.demo.buildlogic"

// Configure the build-logic plugins to target JDK 17
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}


dependencies {

    compileOnly(libs.android.gradlePlugin)
    //compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}



gradlePlugin {
    plugins {
        register("androidApplicationConventionPlugin") {
            id = "demobank.android.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("androidComposeConventionPlugin") {
            id = "demobank.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("androidHiltConventionPlugin") {
            id = "demobank.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidLibraryConventionPlugin") {
            id = "demobank.android.library"
            implementationClass = "LibraryConventionPlugin"
        }

        register("androidFeatureConventionPlugin") {
            id = "demobank.android.feature"
            implementationClass = "FeatureConventionPlugin"
        }

        register("androidRoomConventionPlugin") {
            id = "demobank.android.room"
            implementationClass = "RoomConventionPlugin"
        }
    }
}