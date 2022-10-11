plugins {
    val kotlinVersion = "1.7.20"
    val springBootVersion = "2.7.4"
    val springDepsVersion = "1.0.14.RELEASE"

    java
    kotlin("plugin.jpa") version kotlinVersion
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDepsVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

group = "net.dilius"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotlinVersion = "1.7.20"
val coroutinesVersion = "1.6.4"
val junitVersion = "5.9.1"

val mongodbVersion = "4.7.2"
val kmongoVersion = "4.7.1"

val springDocVersion = "1.6.11"

val opentracingApiVersion = "0.33.0"
val opentracingSpringVersion = "0.5.9"

val mockitoVersion = "4.8.0"
val mockitoKotlinVersion = "4.0.0"

val reactorVersion = "3.4.23"
val reactorNettyVersion = "1.0.23"

dependencies {
    annotationProcessor("org.springframework.boot", "spring-boot-configuration-processor")
    implementation("org.springframework.boot", "spring-boot-starter-webflux")
    implementation("org.springdoc","springdoc-openapi-webflux-core", springDocVersion)
    implementation("org.springdoc","springdoc-openapi-webflux-ui", springDocVersion)
    implementation("org.springdoc","springdoc-openapi-kotlin", springDocVersion)
    api("io.opentracing", "opentracing-api", opentracingApiVersion)
    implementation("io.opentracing.contrib", "opentracing-spring-cloud-starter", opentracingSpringVersion)
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.projectreactor", "reactor-core", reactorVersion)
    implementation("io.projectreactor.netty", "reactor-netty", reactorNettyVersion)
    implementation("io.projectreactor.netty", "reactor-netty-http", reactorNettyVersion)
    implementation("io.projectreactor.netty", "reactor-netty-core", reactorNettyVersion)

    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", coroutinesVersion)
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core-jvm", coroutinesVersion)
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", coroutinesVersion)
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-reactor", coroutinesVersion)

    implementation("org.springframework.boot", "spring-boot-starter-data-mongodb-reactive")
    implementation("org.litote.kmongo", "kmongo-coroutine", kmongoVersion)
    implementation("org.litote.kmongo", "kmongo-reactor", kmongoVersion)
    implementation("org.mongodb", "mongodb-driver-reactivestreams", mongodbVersion)
    implementation("org.mongodb", "mongodb-driver-core", mongodbVersion)
    implementation("org.mongodb", "bson", mongodbVersion)

    runtimeOnly("org.springframework.boot", "spring-boot-devtools")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter", "junit-jupiter")
    testImplementation("org.junit.jupiter", "junit-jupiter-params")
    testImplementation("org.jetbrains.kotlin", "kotlin-test-junit5", kotlinVersion)
    testImplementation("org.mockito", "mockito-core", mockitoVersion)
    testImplementation("org.mockito", "mockito-junit-jupiter", mockitoVersion)
    testImplementation("org.mockito.kotlin", "mockito-kotlin", mockitoKotlinVersion)
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlinx", "kotlinx-coroutines-test", coroutinesVersion)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

}
