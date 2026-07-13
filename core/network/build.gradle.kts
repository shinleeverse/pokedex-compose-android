plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.shinleeverse.pokedex.core.network"
}

dependencies {
    implementation(projects.core.model)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // Network
    implementation(platform(libs.retrofit.bom))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.retrofitBundle)
    testImplementation(libs.okhttp.mockwebserver)

    // Json parsing
    implementation(libs.kotlinx.serialization.json)
}