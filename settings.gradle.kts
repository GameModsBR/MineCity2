pluginManagement {
	repositories {
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net/")
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

include(
	":api", ":core",
	":persistence:base", ":persistence:sql",
	":platform:fabric"
)
