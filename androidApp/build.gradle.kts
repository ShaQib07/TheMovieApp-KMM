plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.shakib.movieapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.shakib.movieapp.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    implementation("io.insert-koin:koin-android:3.3.3")
    implementation("io.insert-koin:koin-androidx-compose:3.4.2")

    implementation("com.russhwolf:multiplatform-settings:1.0.0")

    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    implementation ("io.github.raamcosta.compose-destinations:animations-core:1.7.36-beta")
    ksp ("io.github.raamcosta.compose-destinations:ksp:1.7.36-beta")
}