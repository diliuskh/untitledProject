// Define Versions
val kotlinVersion = "1.9.20"
val springBootVersion = "3.1.4"
val springDepsVersion = "1.1.3"
val springDocVersion = "1.7.0"
val coroutinesVersion = "1.7.3"
val mongodbVersion = "4.11.0"
val kmongoVersion = "4.10.0"
val opentracingApiVersion = "0.33.0"
val opentracingSpringVersion = "0.5.9"
val reactorVersion = "3.5.10"
val reactorNettyVersion = "1.1.11"
val junitVersion = "5.10.0"
val mockitoVersion = "5.5.0"
val mockitoKotlinVersion = "5.1.0"

// Plugins
plugins {
    val kotlinVersion = "1.9.20"
    val springBootVersion = "3.1.4"
    val springDepsVersion = "1.1.3"

    java
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDepsVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

// Project Info
group = "net.dilius"
version = "1.0-SNAPSHOT"

// Repositories
repositories {
    mavenCentral()
}

// Dependencies
dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springdoc:springdoc-openapi-webflux-core:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springDocVersion")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    // Open Tracing
    api("io.opentracing:opentracing-api:$opentracingApiVersion")
    implementation("io.opentracing.contrib:opentracing-spring-cloud-starter:$opentracingSpringVersion")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Reactor
    implementation("io.projectreactor:reactor-core:$reactorVersion")
    implementation("io.projectreactor.netty:reactor-netty:$reactorNettyVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")

    // MongoDB
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongoVersion")
    implementation("org.mongodb:mongodb-driver-reactivestreams:$mongodbVersion")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:$mongodbVersion")

    // Testing
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
