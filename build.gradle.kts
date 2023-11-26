plugins {
	id("fabric-loom") version "1.4-SNAPSHOT"
	id("maven-publish")
	id("org.jetbrains.kotlin.jvm") version "1.9.21"
}

version = project.findProperty("mod_version") as String
group = project.findProperty("maven_group") as String

base {
	archivesName.set(project.findProperty("archives_base_name") as String)
}

repositories {
	// Add repositories to retrieve artifacts from in here.
	// You should only use this when depending on other mods because
	// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
	// See https://docs.gradle.org/current/userguide/declaring_repositories.html
	// for more information about repositories.
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

dependencies {
	// To change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:${project.findProperty("minecraft_version")}")
	mappings("net.fabricmc:yarn:${project.findProperty("yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${project.findProperty("loader_version")}")

	// Fabric API. This is technically optional, but you probably want it anyway.
	modImplementation("net.fabricmc.fabric-api:fabric-api:${project.findProperty("fabric_version")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${project.findProperty("fabric_kotlin_version")}")
	// Uncomment the following line to enable the deprecated Fabric API modules.
	// These are included in the Fabric API production distribution and allow you to update your mod to the latest modules at a later more convenient time.

	// modImplementation("net.fabricmc.fabric-api:fabric-api-deprecated:${project.findProperty("fabric_version")}")
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
