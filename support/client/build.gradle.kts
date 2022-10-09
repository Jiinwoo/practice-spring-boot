
dependencies {
    implementation("io.ktor:ktor-client-core:${rootProject.extra["ktorVersion"]}")
    implementation("io.ktor:ktor-client-cio:${rootProject.extra["ktorVersion"]}")
    implementation("io.ktor:ktor-client-logging:${rootProject.extra["ktorVersion"]}")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
}