plugins {
    java
    id("io.quarkus")
}

group = "io.quarkus.test.application"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("org.acme.libs:libraryB")
    implementation("org.acme.libs:libraryA")
    implementation("org.acme.extensions:example-extension")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}