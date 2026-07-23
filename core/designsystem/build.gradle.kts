plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.library.compose)
}

android {
    namespace = "com.shinleeverse.pokedex.core.designsystem"
}

dependencies {
    api(platform(libs.androidx.compose.bom))

    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
}