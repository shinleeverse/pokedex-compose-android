package com.shinleeverse.pokedex.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension
) {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    commonExtension.buildFeatures.compose = true

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
    }

    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))
        add("implementation", libs.findLibrary("androidx-compose-runtime").get())
    }
}