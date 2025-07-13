pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
    val quarkusPluginVersion: String by settings
    plugins {
        id("io.quarkus.extension") version quarkusPluginVersion
    }
}
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
includeBuild("../../libraries")
rootProject.name = "another-example-extension-parent"
include(":deployment")
include(":runtime")
project(":deployment").name = "another-example-extension-deployment"
project(":runtime").name = "another-example-extension"