/*
 * Copyright 2025 Scott Weeden-Moody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    id("androidx.navigation.safeargs.kotlin") // Doesn't play nice with libs toml, using raw id
}

android {
    namespace = "com.lillicoder.android.feature.dialogs"

    defaultConfig {
        compileSdk = 34
        minSdk = 26
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
    implementation(project(":feature:common"))
    implementation(project(":feature:recycler"))

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
