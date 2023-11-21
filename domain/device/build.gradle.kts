plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.lillicoder.android.domain.device"

    defaultConfig {
        compileSdk = 34
        minSdk = 26
        targetSdk = 34
    }

    sourceSets.getByName("main") {
        java.srcDirs("src/main/kotlin")
    }

    sourceSets.getByName("test") {
        java.srcDirs("src/test/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // AppCompat
    implementation(libs.androidx.appcompat.resources)

    // Kotlin
    implementation(libs.kotlin.stdlib)

    testImplementation(libs.junit)
}
