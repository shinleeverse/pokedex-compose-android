plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.shinleeverse.pokedex.core.network"

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(projects.core.model)

    // Coroutines
    testImplementation(libs.kotlinx.coroutines.test)

    // Network
    implementation(platform(libs.retrofit.bom))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.retrofitBundle)
    testImplementation(libs.okhttp.mockwebserver)

    // Json parsing
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}