plugins {
    id("maven-publish")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val inlineLoggerVersion: String by rootProject

dependencies {
    api(project(":api"))
    implementation("com.michael-bull.kotlin-inline-logger:kotlin-inline-logger:$inlineLoggerVersion")
}
