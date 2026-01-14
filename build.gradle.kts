plugins {
    kotlin("jvm") version "1.9.23"
}

group = "info.firozansari"
version = "1.0"

repositories {
    mavenCentral()
}


dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.3.0")
    implementation("com.github.ajalt.mordant:mordant:2.4.0")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.mockk:mockk:1.13.10")
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
