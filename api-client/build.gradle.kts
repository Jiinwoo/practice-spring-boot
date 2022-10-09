
tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    implementation(project(":support:logging"))
    implementation(project(":support:client"))
    implementation("io.ktor:ktor-client-core:${rootProject.extra["ktorVersion"]}")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
}
