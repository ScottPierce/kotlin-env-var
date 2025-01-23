@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") version "2.1.0"
    id("com.vanniktech.maven.publish") version "0.28.0"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

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

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
        d8()
    }

    applyDefaultHierarchyTemplate()

    targets.all {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        getByName("jsMain") {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-node:18.11.19-pre.494")
            }
        }
    }
}

val ref = System.getenv()["GITHUB_REF"]
if (System.getenv()["GITHUB_REF_TYPE"] == "tag" && ref?.startsWith("refs/tags/v") == true) {
    val version = ref.removePrefix("refs/tags/v")
    println("Releasing Version: $version")

    mavenPublishing {
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

        signAllPublications()

        coordinates(
            groupId = "dev.scottpierce",
            artifactId = "kotlin-env-var",
            version = version,
        )

        pom {
            name.set("Kotlin Environment Variables")
            description.set("A Kotlin Multiplatform library for retrieving environment variables")
            inceptionYear.set("2024")
            url.set("https://github.com/ScottPierce/kotlin-env-var")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("ScottPierce")
                    name.set("Scott Pierce")
                    url.set("https://github.com/ScottPierce")
                }
            }
            scm {
                url.set("https://github.com/ScottPierce/kotlin-env-var")
                connection.set("scm:git:git://github.com/ScottPierce/kotlin-env-var.git")
                developerConnection.set("scm:git:ssh://git@github.com/ScottPierce/kotlin-env-var.git")
            }
        }
    }
}
