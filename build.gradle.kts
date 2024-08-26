import de.chojo.PublishData

plugins {
    java
    `maven-publish`
    `java-library`
    id("de.chojo.publishdata") version "1.4.0"
}

group = "dev.chojo.foundation"
version = "1.0.1"

dependencies {
    api(project(":core"))
    api(project(":configuration"))
    api(project(":system"))
}


allprojects {
    apply<JavaPlugin>()
    apply<JavaLibraryPlugin>()
    apply<MavenPublishPlugin>()
    apply<PublishData>()

    java{
        toolchain{
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.11.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    publishData {
        useEldoNexusRepos()
        publishComponent("java")
    }

    publishing {
        publications.create<MavenPublication>("maven") {
            // configure the publication as defined previously.
            publishData.configurePublication(this)
        }

        repositories {
            maven {
                authentication {
                    credentials(PasswordCredentials::class) {
                        username = System.getenv("NEXUS_USERNAME")
                        password = System.getenv("NEXUS_PASSWORD")
                    }
                }

                name = "EldoNexus"
                setUrl(publishData.getRepository())
            }
        }
    }
    tasks {
        test {
            useJUnitPlatform()
        }
    }
}


