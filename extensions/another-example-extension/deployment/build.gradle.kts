plugins {
    java
    `java-library`
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
    annotationProcessor("io.quarkus:quarkus-extension-processor:${quarkusPlatformVersion}")

    api(project(":another-example-extension")) // why: https://quarkus.io/guides/building-my-first-extension
    implementation("org.acme.extensions:example-extension-deployment")

    implementation("io.quarkus:quarkus-core-deployment")
    implementation("io.quarkus:quarkus-arc-deployment")
    implementation("org.acme.libs:libraryB")

    testImplementation("io.quarkus:quarkus-smallrye-health")
}

java {
    // withJavadocJar()
    withSourcesJar()
}