plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.library.compose)
}

android {
    namespace = "com.shinleeverse.pokedex.core.designsystem"
}

dependencies {


    api(platform(libs.androidx.compose.bom))
}