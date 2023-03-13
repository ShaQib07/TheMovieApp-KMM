plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    id("org.jetbrains.kotlin.plugin.serialization").version("1.8.10").apply(false)
    id("com.google.devtools.ksp").version("1.8.10-1.0.9").apply(false)
    id("com.rickclephas.kmp.nativecoroutines").version("1.0.0-ALPHA-5").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
