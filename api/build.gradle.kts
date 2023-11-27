plugins {
    id("maven-publish")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {
    explicitApi()
}

val kotlinVersion: String by rootProject
val kotlinCoroutinesVersion: String by rootProject
val kotlinSerializationVersion: String by rootProject
val atomicFuVersion: String by rootProject
val kotlinDatetimeVersion: String by rootProject

dependencies {
    implementation("net.kyori:adventure-api:4.14.0")
    implementation("net.kyori:adventure-text-minimessage:4.14.0")
    api("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-cbor:$kotlinSerializationVersion")
    api("org.jetbrains.kotlinx:atomicfu:$atomicFuVersion")
    api("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetimeVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("io.mockk:mockk:1.13.8")
}

tasks.test {
    useJUnitPlatform()
}
