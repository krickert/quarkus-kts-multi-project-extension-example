plugins {
    id("io.quarkus.extension")
}

quarkusExtension {
    deploymentModule.set("another-example-extension-deployment")
}

repositories {
    mavenLocal()
    mavenCentral()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(platform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("org.acme.libs:libraryB")
    annotationProcessor("io.quarkus:quarkus-extension-processor:${quarkusPlatformVersion}")
    implementation("io.quarkus:quarkus-core")
    implementation("io.quarkus:quarkus-arc")

    api("org.acme.extensions:example-extension")
}