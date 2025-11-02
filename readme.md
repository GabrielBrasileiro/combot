# Combot

![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-blue)
![GitHub Actions](https://github.com/GabrielBrasileiro/combot/actions/workflows/build.yml/badge.svg)
![License](https://img.shields.io/badge/License-Apache%202.0-blue)
![Latest Version](https://img.shields.io/maven-metadata/v/https://maven.pkg.github.com/GabrielBrasileiro/combot/br/com/gabrielbrasileiro/combot/maven-metadata.xml.svg)
## Description

Combot is a Compose testing library designed to simplify testing and automation of UI components in
Jetpack Compose. It allows developers to write reliable tests for Composable functions and
interactions, similar to how Koin simplifies dependency injection in Android.

Implementation is inspired by modern Compose testing patterns and provides a straightforward API to
interact with UI elements programmatically.

---

## Installation

### Gradle Groovy

```groovy
implementation "br.com.gabrielbrasileiro:combot:x.x.x"
```
### Gradle Kts

```groovy
implementation("br.com.gabrielbrasileiro:combot:x.x.x")
```

### Gradle Toml + Kts

```groovy
[versions]
combot = "x.x.x"

[libraries]
combot = { group = "br.com.gabrielbrasileiro", name = "combot", version.ref = "combot" }
```

```groovy
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

