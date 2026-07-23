plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.protobuf.plugin)
}

android {
    namespace = "com.shinleeverse.pokedex.core.datastore"

    defaultConfig {
        consumerProguardFiles("consumer-rules.keep")
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)

    api(libs.androidx.dataStore)
    implementation(libs.protobuf.kotlin.lite)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.test.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java"){
                    option("lite")
                }
                register("kotlin"){
                    option("lite")
                }
            }
        }
    }
}