rootProject.name = "practice-spring-boot"

include (
    "api-client",
    "api-server",
    "support:logging",
    "support:client"
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val ktorClientVersion: String by settings
    val ktorCIOEngineVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "ktor-client-core" -> useVersion(ktorClientVersion)
                "ktor-client-cio" -> useVersion(ktorCIOEngineVersion)
            }
        }
    }
}