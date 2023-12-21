plugins {
    id("maven-publish")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {
    explicitApi()
}

val inlineLoggerVersion: String by rootProject

dependencies {
    api(project(":core"))
    compileOnly("com.michael-bull.kotlin-inline-logger:kotlin-inline-logger:$inlineLoggerVersion")
}
