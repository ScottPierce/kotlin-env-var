package dev.scottpierce.envvar

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import platform.posix.getenv

internal actual object EnvVarPlatform {
    @OptIn(ExperimentalForeignApi::class)
    actual operator fun get(name: String): String? {
        return getenv(name)?.toKString()
    }
}
