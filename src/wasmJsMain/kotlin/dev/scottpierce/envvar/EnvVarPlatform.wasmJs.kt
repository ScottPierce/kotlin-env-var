package dev.scottpierce.envvar

private fun getenv(name: String): String? = js("process.env[name]")

internal actual object EnvVarPlatform {
    actual operator fun get(name: String): String? {
        return getenv(name)
    }
}
