import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.demo.build.logic.internal.configureCompose
import com.demo.build.logic.internal.configureKotlinAndroid
import com.google.samples.apps.nowinandroid.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("demobank.android.hilt")

            }


            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureCompose(this)
                defaultConfig.targetSdk = 34
                configureFlavors(this)
            }

        }
    }
}
