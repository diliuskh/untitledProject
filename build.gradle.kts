// Define Versions
val kotlinVersion = "1.9.21"
val springBootVersion = "3.2.0"
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
    val kotlinVersion = "1.9.21"
    val springBootVersion = "3.2.0"
    val springDepsVersion = "1.1.3"

    java
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDepsVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
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
                    useVersion(nettyVersion)
                }
                "org.jetbrains.kotlin" -> {
                    useVersion(kotlinVersion)
                }
                "org.springframework.boot" -> {
                    useVersion(springBootVersion)
                }
                "io.projectreactor" -> {
                    useVersion(reactorVersion)
                }
                "io.projectreactor.netty" -> {
                    useVersion(reactorNettyVersion)
                }
                "org.mongodb" -> {
                    useVersion(mongodbVersion)
                }
                "org.jetbrains.kotlinx" -> {
                    if (requested.name.startsWith("kotlinx-coroutines")) {
                        useVersion(coroutinesVersion)
                    } else if (requested.name.startsWith("kotlinx-serialization")) {
                        useVersion(serializationVersion)
                    }
                }
                "org.mockito" -> {
                    useVersion(mockitoVersion)
                }
                "org.mockito.kotlin" -> {
                    useVersion(mockitoKotlinVersion)
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
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-reactor-netty:$springBootVersion")
    implementation("org.springdoc:springdoc-openapi-webflux-core:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springDocVersion")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client:$springBootVersion")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Netty
    implementation("io.netty:netty-all:$nettyVersion")
    when {
        osName.contains("linux") -> implementation("io.netty:netty-transport-native-epoll:$nettyVersion:linux-$osArch")
        osName.contains("mac") -> implementation("io.netty:netty-transport-native-kqueue:$nettyVersion:osx-$osArch")
        osName.contains("win") -> implementation("io.netty:netty-transport-native-epoll:$nettyVersion:windows-$osArch")
        else -> error("Unsupported OS: $osName")
    }


    // Reactor
    implementation("io.projectreactor:reactor-core:$reactorVersion") {
        exclude(group = "io.netty")
    }
    implementation("io.projectreactor.netty:reactor-netty:$reactorNettyVersion") {
        exclude(group = "io.netty")
    }

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")

    // MongoDB
    implementation("org.litote.kmongo:kmongo-coroutine-serialization:$kmongoVersion")
    implementation("org.mongodb:mongodb-driver-reactivestreams:$mongodbVersion")
//    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:$mongodbVersion")

    // Testing
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

// Tasks
tasks {
    test { useJUnitPlatform() }

    compileKotlin {
        kotlinOptions { jvmTarget = "20" }
    }
    compileTestKotlin {
        kotlinOptions { jvmTarget = "20" }
    }

    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(20)) }
    }
}
