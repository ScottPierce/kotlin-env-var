import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("multiplatform") version "1.9.22"
    id("com.vanniktech.maven.publish") version "0.27.0"
}

group = "io.github.scottpierce"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
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

    applyDefaultHierarchyTemplate()

    sourceSets {
        getByName("jsMain") {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-node:18.11.19-pre.494")
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf("-Xexpect-actual-classes")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon> {
    kotlinOptions {
        freeCompilerArgs += listOf("-Xexpect-actual-classes")
    }
}

mavenPublishing {
//    publishToMavenCentral(SonatypeHost.DEFAULT)
    publishToMavenCentral(SonatypeHost.S01, automaticRelease = true)

    signAllPublications()

    coordinates("dev.scottpierce", "kotlin-env-var", "1.0.0")

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
