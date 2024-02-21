package dev.scottpierce.envvar

internal expect object EnvVarPlatform {
    operator fun get(name: String): String?
}
