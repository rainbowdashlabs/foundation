rootProject.name = "foundation"
include("system")
include("configuration")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("jackson", "2.17.2")
            library("jackson-core", "com.fasterxml.jackson.core", "jackson-databind").versionRef("jackson")
            library("slf4j", "org.slf4j:slf4j-api:2.0.16")
        }
    }
}
include("core")