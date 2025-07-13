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

includeBuild("../../libraries")
includeBuild("../../extensions/example-extension")
includeBuild("../../extensions/another-example-extension")

rootProject.name = "example-extension-integration"