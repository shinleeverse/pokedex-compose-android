import com.android.build.api.dsl.LibraryExtension
import com.shinleeverse.pokedex.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply("com.android.library")
            }
            extensions.getByType<LibraryExtension>().apply {
                configureAndroidCompose(this)
            }
        }
    }
}