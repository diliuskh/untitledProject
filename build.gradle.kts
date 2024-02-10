// Define Versions
val kotlinVersion = "1.9.22"
val springBootVersion = "3.2.2"
val springDepsVersion = "1.1.4"
val springDocVersion = "1.7.0"
val coroutinesVersion = "1.7.3"
val serializationVersion = "1.6.1"
val mongodbVersion = "4.11.1"
val kmongoVersion = "4.11.0"
val nettyVersion = "4.1.101.Final"
val reactorVersion = "3.6.0"
val reactorNettyVersion = "1.1.13"
val junitVersion = "5.10.1"
val mockitoVersion = "5.7.0"
val mockitoKotlinVersion = "5.1.0"

// OS Info
val osName: String by extra { System.getProperty("os.name").lowercase() }
val osArch: String by extra { System.getProperty("os.arch").lowercase().let {
    when(it) {
        "aarch64", "aarch-64", "aarch_64" -> "aarch_64"
        "x86-64", "x86_64" -> "x86_64"
        else -> error("Unsupported OS Architecture: $it")
    }
} }

// Plugins
plugins {

    alias(libs.plugins.kotlin)
    alias(libs.plugins.spring.dependencies)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.noarg)
    alias(libs.plugins.kotlin.spring)
}

// Project Info
group = "net.dilius"
version = "1.0-SNAPSHOT"

// Repositories
repositories {
    mavenCentral()
}

configurations.all {
    resolutionStrategy {
        eachDependency {
            when (requested.group) {
                "io.netty" -> {
                    useVersion(libs.versions.netty.get())
                }
                "org.jetbrains.kotlin" -> {
                    useVersion(libs.versions.kotlin.get())
                }
                "org.springframework.boot" -> {
                    useVersion(libs.versions.spring.boot.get())
                }
                "io.projectreactor" -> {
                    useVersion(libs.versions.reactor.core.get())
                }
                "io.projectreactor.netty" -> {
                    useVersion(libs.versions.reactor.netty.get())
                }
                "org.mongodb" -> {
                    useVersion(libs.versions.mongodb.get())
                }
                "org.jetbrains.kotlinx" -> {
                    if (requested.name.startsWith("kotlinx-coroutines")) {
                        useVersion(libs.versions.coroutines.get())
                    } else if (requested.name.startsWith("kotlinx-serialization")) {
                        useVersion(libs.versions.serialization.get())
                    }
                }
                "org.mockito" -> {
                    useVersion(libs.versions.mockito.core.get())
                }
                "org.mockito.kotlin" -> {
                    useVersion(libs.versions.mockito.kotlin.get())
                }
                "org.yaml" -> {
                    useVersion("2.2")
                }
            }
        }
    }
}

// Dependencies
dependencies {
    annotationProcessor(libs.spring.boot.configuration.processor)

    // Spring Boot
    implementation(libs.spring.boot.webflux) {
        exclude("com.fasterxml.jackson.core")
        exclude("com.fasterxml.jackson.dataformat")
        exclude("com.fasterxml.jackson.datatype")
        exclude("com.fasterxml.jackson.module")
    }
    implementation(libs.spring.boot.reactor.netty){
        exclude("com.fasterxml.jackson.core")
        exclude("com.fasterxml.jackson.dataformat")
        exclude("com.fasterxml.jackson.datatype")
        exclude("com.fasterxml.jackson.module")
    }

    implementation(libs.spring.boot.actuator)
    runtimeOnly(libs.spring.boot.devtools)

    // Security
    implementation(libs.spring.boot.security)
    implementation(libs.spring.boot.oauth2)

    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Netty
    implementation(libs.netty.all)
    when {
        osName.contains("linux") -> implementation("io.netty:netty-transport-native-epoll:$nettyVersion:linux-$osArch")
        osName.contains("mac") -> implementation("io.netty:netty-transport-native-kqueue:$nettyVersion:osx-$osArch")
        osName.contains("win") -> implementation("io.netty:netty-transport-native-epoll:$nettyVersion:windows-$osArch")
        else -> error("Unsupported OS: $osName")
    }


    // Reactor
    implementation(libs.reactor.core) {
        exclude(group = "io.netty")
    }
    implementation("io.projectreactor.netty:reactor-netty:$reactorNettyVersion") {
        exclude(group = "io.netty")
    }

    // Serialization
    implementation(libs.kotlin.serialization)

    // Coroutines
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutines.reactor)

    // MongoDB
    implementation(libs.kmongo.coroutines.serialization)
    implementation(libs.mongodb.reactivestreams)
//    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:$mongodbVersion")

    // Testing
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.spring.boot.test)
}

// Tasks
tasks {
    test { useJUnitPlatform() }

    compileKotlin {
        kotlinOptions { jvmTarget = "21" }
    }
    compileTestKotlin {
        kotlinOptions { jvmTarget = "21" }
    }

    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
    }
}
