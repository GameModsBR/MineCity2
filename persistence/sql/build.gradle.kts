plugins {
    id("maven-publish")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val inlineLoggerVersion: String by rootProject

dependencies {
    implementation(project(":persistence:base"))
    implementation("org.flywaydb:flyway-core:10.1.0")
    implementation("org.jdbi:jdbi3-core:3.41.3")
    compileOnly("com.michael-bull.kotlin-inline-logger:kotlin-inline-logger:$inlineLoggerVersion")
}
