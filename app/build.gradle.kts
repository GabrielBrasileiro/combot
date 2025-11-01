import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "br.com.gabrielbrasileiro.sample"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.gabrielbrasileiro.sample"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

dependencies {
    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.androidx.material.icons.extended)

    // Compose Compile Only
    compileOnly(libs.compose.tooling.preview)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    // Compose Debug
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.tooling.preview)
    debugImplementation(libs.compose.tooling.preview.android)

    // Needed for createComposeRule.
    debugImplementation(libs.compose.ui.test.manifest)

    // Unit Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.koin.test)

    // Android Test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.compose.ui.test)
    androidTestImplementation(libs.compose.ui.test.android)
    androidTestImplementation(libs.mockk.android) {
        // Exclude JUnit 5 unsupported by instrumented tests
        exclude(group = "org.junit.jupiter")
        exclude(group = "org.junit.platform")
    }

    // Library
    androidTestImplementation(project(":combot"))
}