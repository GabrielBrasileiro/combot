<div style="text-align: left;">
  <img src="./logo.png" width="400" />
</div>

<br>

![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-blue)
![GitHub Actions](https://github.com/GabrielBrasileiro/combot/actions/workflows/main.yml/badge.svg?branch=main)
![License](https://img.shields.io/badge/License-Apache%202.0-blue)
![JitPack](https://img.shields.io/jitpack/version/com.github.GabrielBrasileiro/combot.svg?color=blue)

## Description

Combot is a Compose testing library designed to simplify testing and automation of UI components in
Jetpack Compose. It allows developers to easily write tests for Compose components.

Implementation is inspired by modern Compose testing patterns and provides a straightforward API to
interact with UI elements programmatically.

---

## Installation

### Maven

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Add for your project
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Gradle Kts

```kotlin
implementation("com.github.GabrielBrasileiro:combot:x.x.x")
```

### Gradle Toml + Kts

```toml
[versions]
combot = "x.x.x"

[libraries]
combot = { group = "com.github.GabrielBrasileiro", name = "combot", version.ref = "combot" }
```

```kotlin
dependencies {
    implementation(libs.combot)
}
```

## Documentation

For full documentation, examples, and guides on how to use Combot, please visit:

[https://gabrielbrasileiro.dev/combot](https://gabrielbrasileiro.dev/combot)

This site includes:

- Quick start examples
- API reference
- Advanced usage patterns
- Tips for testing Compose UI components
