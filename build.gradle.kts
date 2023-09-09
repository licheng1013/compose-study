import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    // web 必须加载此依赖否则无法返回json
    kotlin("plugin.serialization").version("1.8.20")
}

group = "com.aiwan"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    // 二维码生成
    maven  ("https://jitpack.io" )
    // web 框架
    maven  ("https://maven.pkg.jetbrains.space/public/p/ktor/eap" )

}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
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
            }
        }
        val jvmTest by getting
    }
}



compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "study-compose"
            packageVersion = "1.0.0"
        }
    }
}
