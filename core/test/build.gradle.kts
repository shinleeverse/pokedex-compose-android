plugins {
    alias(libs.plugins.pokedex.android.library)
}

android {
    namespace = "com.skydoves.pokedex.core.test"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit)
}