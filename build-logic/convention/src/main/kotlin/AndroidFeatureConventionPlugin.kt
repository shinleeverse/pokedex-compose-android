import com.android.build.api.dsl.LibraryExtension
import com.shinleeverse.pokedex.convention.configureAndroidCompose
import com.shinleeverse.pokedex.convention.configureKotlinAndroid
import com.shinleeverse.pokedex.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.library")
            }

            dependencies{
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:navigation"))
                add("api", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
            }

            extensions.getByType<KotlinAndroidProjectExtension>().apply {
                configureKotlinAndroid(this)
            }
        }
    }
}