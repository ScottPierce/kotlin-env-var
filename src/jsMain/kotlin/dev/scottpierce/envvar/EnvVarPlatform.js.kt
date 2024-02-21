package dev.scottpierce.envvar

import node.process.process

internal actual object EnvVarPlatform {
    actual operator fun get(name: String): String? {
        return process.env[name]
    }
}
