package dev.scottpierce.envvar

internal actual object EnvVarPlatform {
    actual operator fun get(name: String): String? {
        return System.getenv(name)
    }
}
