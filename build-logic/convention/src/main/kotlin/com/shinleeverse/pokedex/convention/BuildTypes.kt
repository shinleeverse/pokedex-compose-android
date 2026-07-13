package com.shinleeverse.pokedex.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension,
    extensionType: ExtensionType
) {
    commonExtension.buildFeatures.buildConfig = true

    when(extensionType) {
        ExtensionType.APPLICATION -> {
            this@configureBuildTypes.extensions.configure<ApplicationExtension> {
                buildTypes {
                    debug {
                        configureDebugBuildType()
                    }
                    release {
                        configureReleaseBuildType(
                            defaultProguardFile = getDefaultProguardFile("proguard-android-optimize.txt")
                        )
                    }
                }
            }
        }
        ExtensionType.LIBRARY -> {
            this@configureBuildTypes.extensions.configure<LibraryExtension> {
                buildTypes {
                    debug {
                        configureDebugBuildType()
                    }
                    release {
                        configureReleaseBuildType(
                            defaultProguardFile = getDefaultProguardFile("proguard-android-optimize.txt")
                        )
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(){

}

private fun BuildType.configureReleaseBuildType(
    defaultProguardFile : File,
) {
    isMinifyEnabled = true
    proguardFiles(
        defaultProguardFile,
        "proguard-rules.pro"
    )
}