import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("fabric-loom") version "1.4-SNAPSHOT"
    id("maven-publish")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

// Fabric Properties
// check these on https://fabricmc.net/develop
val minecraftVersion="1.20.1"
val yarnMappings="1.20.1+build.10"
val loaderVersion="0.14.24"
val fabricKotlinVersion="1.10.15+kotlin.1.9.21"

// Dependencies
val fabricVersion="0.91.0+1.20.1"
val inlineLoggerVersion: String by rootProject

base {
    archivesName.set("minecity-fabric")
}

loom {
    splitEnvironmentSourceSets()

    mods {
        mods.create("minecity") {
            sourceSet(sourceSets.main.get())
            sourceSet(sourceSets.getByName("client"))
        }
    }
}

val persistenceProject = project(":persistence")

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")

    // Broken in server, but necessary for IDE to work (our jar, for some reason, isn't accepted by modImplementation)
    modImplementation("net.kyori:adventure-platform-fabric:5.9.0")
    // Custom version that backports the fix (runtime only, since in IDE we use the default version)
    modRuntimeOnly(files("libs/adventure-platform-fabric-5.9.1-PC.jar"))

    // Uncomment the following line to enable the deprecated Fabric API modules.
    // These are included in the Fabric API production distribution and allow you to update your mod to the latest modules at a later more convenient time.

    // modImplementation("net.fabricmc.fabric-api:fabric-api-deprecated:${project.findProperty("fabric_version")}")
    implementation(include(project(":api"))!!)
    implementation(include(project(":core"))!!)
    persistenceProject.subprojects.forEach { persistenceImpl ->
        implementation(include(project(persistenceImpl.path))!!)
    }

    compileOnly("com.michael-bull.kotlin-inline-logger:kotlin-inline-logger:$inlineLoggerVersion")
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile> {
    options.release.set(17)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<RemapJarTask> {
    // Include the adventure-platform-fabric jar in the remapped jar (include task only works with maven dependency)
    nestedJars.from(files("libs/adventure-platform-fabric-5.9.1-PC.jar"))
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesName.get()}"}
    }
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}
