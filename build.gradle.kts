plugins {
    kotlin("jvm") version "1.5.31"
}

group = "org.horae"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")

}
