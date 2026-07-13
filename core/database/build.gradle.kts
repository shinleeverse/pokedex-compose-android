plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.shinleeverse.pokedex.core.database"

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schema")
        }
    }
}

dependencies {
    implementation(projects.core.model)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // json parsing
    implementation(libs.kotlinx.serialization.json)

    // unit test
    testImplementation(libs.junit)
}