plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.shinleeverse.pokedex.core.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
