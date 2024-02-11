

// OS Info
val osName: String by extra { System.getProperty("os.name").lowercase().let {
    when {
        it.contains("mac") -> "osx"
        it.contains("nix") || it.contains("linux")-> "linux"
        else -> error("Unsupported OS: $it")
    }

} }
val osArch: String by extra {
    System.getProperty("os.arch").lowercase().let {
        when (it) {
            "aarch64", "aarch-64", "aarch_64" -> "aarch_64"
            "x86-64", "x86_64" -> "x86_64"
            else -> error("Unsupported OS Architecture: $it")
        }
    }
}

// Plugins
plugins {

    alias(libs.plugins.kotlin)
    alias(libs.plugins.spring.dependencies)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.noarg)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.docker.compose)
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
            }
        }
    }
}

// Dependencies
dependencies {
    annotationProcessor(libs.spring.boot.configuration.processor)

    // Spring Boot
    implementation(libs.spring.boot.webflux)
    implementation(libs.spring.boot.reactor.netty)
    implementation(libs.spring.boot.actuator)
    runtimeOnly(libs.spring.boot.devtools)

    // Security
    implementation(libs.spring.boot.security)
    implementation(libs.spring.boot.oauth2)

    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)

    // Jackson
    implementation(platform(libs.jackson.bom))
    implementation(libs.jackson.core)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.annotations)
    implementation(libs.jackson.datatype.jdk8)
    implementation(libs.jackson.datatype.jsr310)
    implementation(libs.jackson.module.kotlin)

    //Logging
    implementation(libs.logback.classic)
    implementation(libs.logback.core)
    implementation(libs.slf4j.api)

    // Netty
    if (osName.contains("linux")) {
        implementation(variantOf(libs.netty.epoll) { classifier("$osName-$osArch") })
    } else if (osName.contains("osx")) {
        implementation(variantOf(libs.netty.kqueue) { classifier("$osName-$osArch") })
    } else {
        throw GradleException("Unsupported OS: $osName")
    }

    // Reactor
    implementation(libs.reactor.core)
    implementation(libs.reactor.netty)
    // Serialization
    implementation(libs.kotlin.serialization)

    // Coroutines
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutines.reactor)

    // MongoDB
    implementation(libs.kmongo.coroutines.serialization)
    implementation(libs.mongodb.reactivestreams)

    // Testing
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.spring.boot.test)
    testImplementation(libs.json.path)
}

// Tasks
tasks {
    jar {
        enabled = false
    }
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

dockerCompose {
    useComposeFiles = listOf("docker-compose.yml")
    forceRecreate = true
    stopContainers = true
    removeOrphans = true
    removeContainers = true
    removeVolumes = true
}

dockerCompose.isRequiredBy(tasks.named("bootRun"))
dockerCompose.isRequiredBy(tasks.named("bootTestRun"))
