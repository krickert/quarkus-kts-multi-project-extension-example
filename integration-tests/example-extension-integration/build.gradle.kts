plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenLocal()
    mavenCentral()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    
    // The extensions to test
    implementation("org.acme.extensions:example-extension")
    implementation("org.acme.extensions:another-example-extension")
    
    // Libraries
    implementation("org.acme.libs:libraryA")
    implementation("org.acme.libs:libraryB")
    
    // Quarkus dependencies
    implementation("io.quarkus:quarkus-resteasy")
    
    // Test dependencies
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}