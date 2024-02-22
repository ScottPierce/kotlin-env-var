package dev.scottpierce.envvar

import node.process.process
import js.core.get

internal actual object EnvVarPlatform {
    actual operator fun get(name: String): String? {
        return process.env[name]
    }
}
