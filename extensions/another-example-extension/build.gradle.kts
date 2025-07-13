plugins {
    `java-library`
    `maven-publish`
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    group = "org.acme.extensions"
    version = "1.0-SNAPSHOT"
    
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "org.acme.extensions"
                artifactId = project.name
                version = "1.0-SNAPSHOT"
                from(components["java"])
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.acme.extensions"
            artifactId = rootProject.name
            version = "1.0-SNAPSHOT"
            from(components["java"])
        }
    }
}
group = "org.acme.extensions"
version = "1.0-SNAPSHOT"