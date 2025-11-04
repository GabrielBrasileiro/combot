import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinBinary)
    id("maven-publish")
}

group = "br.com.gabrielbrasileiro"
version = "1.0.3"

android {
    namespace = "br.com.gabrielbrasileiro.combot"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
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
    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.test)
    implementation(libs.compose.ui.test.android)

    // Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])

                artifactId = "combot"

                pom {
                    name.set("Combot")
                    description.set("Compose robot testing library")
                    url.set("https://github.com/gabrielbrasileiro/combot")
                }
            }
        }
    }
}