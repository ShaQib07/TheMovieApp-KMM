plugins {
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.android.library")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines")
    kotlin("multiplatform")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val koinVersion = "3.3.3"
        val ktorVersion = "2.2.4"
        val multiPlatformSettingsVersion = "1.0.0"
        val sqlDelightVersion = "1.5.5"
        val commonMain by getting {
            dependencies {
                implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")

                implementation("io.insert-koin:koin-core:$koinVersion")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                implementation("com.russhwolf:multiplatform-settings:$multiPlatformSettingsVersion")

                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.insert-koin:koin-test:$koinVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")

            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.shakib.movieapp"
    compileSdk = 33
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

sqldelight {
    database("MovieDb") {
        packageName = "com.shakib.movieapp"
        schemaOutputDirectory = file("src/commonMain/sqldelight/com/shakib/movieapp/db")
    }
}