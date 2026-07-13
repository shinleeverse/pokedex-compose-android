plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.library.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.pokedex.android.hilt)
}

android {
    namespace = "com.shinleeverse.pokedex.core.navigation"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)

    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)

    implementation(libs.kotlinx.serialization.json)
}