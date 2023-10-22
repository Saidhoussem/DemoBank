import com.demo.build.logic.internal.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.kotlin.dsl.getByType

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val extension = extensions.getByType<ApplicationExtension>()
            configureCompose(extension)

        }
    }

}
