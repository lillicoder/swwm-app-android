plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    id("androidx.navigation.safeargs.kotlin") // Doesn't play nice with libs toml, using raw id
}

android {
    namespace = "com.lillicoder.android.ui.dialogs"

    defaultConfig {
        compileSdk = 34
        minSdk = 26
        targetSdk = 34
    }

    sourceSets.getByName("main") {
        java.srcDirs("src/main/kotlin")
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
    implementation(project(":data:dialogs"))
    implementation(project(":domain:dialogs"))
    implementation(project(":ui:common"))
    implementation(project(":ui:recycler"))

    // CardView
    implementation(libs.androidx.cardview)

    // AppCompat
    implementation(libs.androidx.fragment.ktx)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // RecyclerView
    implementation(libs.androidx.recyclerview)

    // Material Design
    implementation(libs.android.material)

    // Kotlin
    implementation(libs.kotlin.stdlib)
}
