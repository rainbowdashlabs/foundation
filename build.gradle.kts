plugins {
    java
    `maven-publish`
    `java-library`
}

group = "de.chojo.foundation"
version = "1.0.0"



allprojects {
    apply<JavaPlugin>()
    apply<JavaLibraryPlugin>()
    apply<MavenPublishPlugin>()

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.11.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}
