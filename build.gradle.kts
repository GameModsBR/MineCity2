plugins {
	id("maven-publish")
	kotlin("jvm") version "1.9.21" apply false
	kotlin("plugin.serialization") version "1.9.21" apply false
}

allprojects {
	version = "2.0.0-SNAPSHOT"
	group = "br.com.gamemods.minecity"

	repositories {
		mavenCentral()
	}
}
