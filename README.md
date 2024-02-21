# kotlin-env-var
A simple Kotlin Multiplatform library for retrieving environment variables.

## Usage:
```Kotlin
val optionalEnvVar: String? = EnvVar["OPTIONAL_ENV_VAR"]
val envVarWithDefault: String = EnvVar.get("ENV_VAR_WITH_DEFAULT") { "default" }

// Throws IllegalStateException if it's not found
val requiredEnvVar: String = EnvVar.require("REQUIRED_ENV_VAR")

val isProd: Boolean = EnvVar.get("IS_PROD")?.toBooleanStrictOrNull() ?: false
// Throws IllegalStateException if required is true and it's not found
val sometimesRequiredEnvVar: String? = EnvVar.get("SOMETIMES_REQUIRED_ENV_VAR", required = isProd)
```