# kotlin-env-var
A simple Kotlin Multiplatform library for retrieving environment variables.

## Usage:
```Kotlin
implementation("dev.scottpierce:kotlin-env-var:<latest version>")
```

```Kotlin
val optionalEnvVar: String? = EnvVar["OPTIONAL_ENV_VAR"]
val envVarWithDefault: String = EnvVar.get("ENV_VAR_WITH_DEFAULT") { "default" }

// Throws IllegalStateException if it's not found
val requiredEnvVar: String = EnvVar.require("REQUIRED_ENV_VAR")

val isProd: Boolean = EnvVar.get("IS_PROD")?.toBooleanStrictOrNull() ?: false
// Throws IllegalStateException if required is true and it's not found
val sometimesRequiredEnvVar: String? = EnvVar.get("SOMETIMES_REQUIRED_ENV_VAR", required = isProd)
```

## Supported Platforms:
```kotlin
jvm()

js {
    browser()
    nodejs()
}

iosArm64()
iosX64()
iosSimulatorArm64()

tvosArm64()
tvosSimulatorArm64()
tvosX64()

macosArm64()
macosX64()

mingwX64()

linuxX64()
linuxArm64()

wasmJs {
    browser()
    nodejs()
    d8()
}
```
