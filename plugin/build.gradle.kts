plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.aiwan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
    // 依赖 editor
    implementation(project(":editor"))
}

