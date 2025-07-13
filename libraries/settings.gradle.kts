pluginManagement {
    repositories {
        mavenLocal {
            content {
                includeGroupByRegex("io.quarkus.*")
                includeGroup("org.hibernate.orm")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    val quarkusPluginVersion: String by settings
    plugins {
        id("io.quarkus") version quarkusPluginVersion
    }
}
rootProject.name = "libraries"

include("libraryA")
include("libraryB")