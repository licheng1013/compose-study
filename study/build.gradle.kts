
plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    // 二维码生成
    maven  ("https://jitpack.io" )
    // web 框架
    maven  ("https://maven.pkg.jetbrains.space/public/p/ktor/eap" )
}

dependencies {
    implementation(compose.desktop.currentOs)
    // QRCode 二维码生成
    implementation("com.github.kenglxn.QRGen:javase:3.0.1")
    // web 框架
    implementation("io.ktor:ktor-server-core-jvm:2.3.4")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.4")
    implementation("io.ktor:ktor-server-status-pages-jvm:2.3.4")
    implementation("io.ktor:ktor-server-default-headers-jvm:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    // websocket
    implementation("org.java-websocket:Java-WebSocket:1.5.4")
}

