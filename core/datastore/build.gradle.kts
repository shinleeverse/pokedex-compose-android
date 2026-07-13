plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.protobuf.plugin)
}

android {
    namespace = "com.shinleeverse.pokedex.core.datastore"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.model)

    api(libs.androidx.dataStore)
    implementation(libs.protobuf.kotlin.lite)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
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