plugins {
    kotlin("jvm") version "2.2.21"
}

group = "info.firozansari"
version = "1.1"

repositories {
    mavenCentral()
}


dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.3.0")
    implementation("com.github.ajalt.mordant:mordant:2.4.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.14.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.14.2")
    testImplementation("io.mockk:mockk:1.14.7")
}
tasks.test {
    useJUnitPlatform()
}

