import com.android.build.api.dsl.ApplicationExtension
import com.shinleeverse.pokedex.convention.ExtensionType
import com.shinleeverse.pokedex.convention.configureBuildTypes
import com.shinleeverse.pokedex.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = 36
                configureKotlinAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }

            extensions.getByType<KotlinAndroidProjectExtension>().apply{
                configureKotlinAndroid(this)
            }
        }
    }
}