import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.binary)
    id("com.vanniktech.maven.publish") version "0.37.0"
}

group = "io.github.gabrielbrasileiro"
version = "1.1.0"

android {
    namespace = "br.com.gabrielbrasileiro.combot"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.test)
    implementation(libs.compose.ui.test.android)

    // Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}



mavenPublishing {
    configure(
        AndroidSingleVariantLibrary(
            variant = "release",
            sourcesJar = SourcesJar.Sources(),
            javadocJar = JavadocJar.Javadoc(),
        )
    )

    coordinates(group.toString(), "combot", version.toString())

    pom {
        name.set("Combot")
        description.set("A declarative Android UI testing library for creating and managing Compose test flows easily.")
        inceptionYear.set("2025")
        url.set("https://gabrielbrasileiro.dev/combot")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("GabrielBrasileiro")
                name.set("Gabriel Brasileiro")
                url.set("https://gabrielbrasileiro.dev")
            }
        }

        scm {
            url.set("https://github.com/GabrielBrasileiro/combot/")
            connection.set("scm:git:git://github.com/GabrielBrasileiro/combot.git")
            developerConnection.set("scm:git:ssh://git@github.com/GabrielBrasileiro/combot.git")
        }
    }
}