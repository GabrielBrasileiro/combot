import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

group = "br.com.gabrielbrasileiro"
version = "0.0.7"

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

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/GabrielBrasileiro/combot")
                credentials {
                    username = System.getenv("USERNAME")
                    password = System.getenv("TOKEN")
                }
            }
        }
    }
}