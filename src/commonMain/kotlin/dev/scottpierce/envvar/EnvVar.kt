package dev.scottpierce.envvar

object EnvVar {
    /**
     * Gets an environment variable as a [String] from the running platform, or returns null if it's not available.
     */
    operator fun get(name: String): String? = EnvVarPlatform[name]

    /**
     * Gets an environment variable as a [String] from the running platform, or returns null if it's not available.
     */
    operator fun get(name: String, required: Boolean): String? {
        val value = get(name)
        if (required && value == null) {
            error("Environment variable '$name' is required, but not found.")
        }
        return value
    }

    inline fun get(name: String, required: Boolean = false, default: () -> String): String {
        return get(name = name, required = required)
            ?: run {
                // Printing this statement helps to debug issues in deployments
                println("Environment variable '$name' wasn't set, so using default value.")
                default()
            }
    }

    fun require(name: String): String = EnvVarPlatform[name]
        ?: error("Environment Variable '$name' is required, but missing.")
}
