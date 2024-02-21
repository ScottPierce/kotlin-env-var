plugins {
    kotlin("multiplatform") version "1.9.22"
}

group = "dev.scottpierce"
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

    applyDefaultHierarchyTemplate()

    sourceSets {
        getByName("jsMain") {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-node:18.16.12-pre.687")
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
