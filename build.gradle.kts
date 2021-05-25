plugins {
    val kotlinVersion = "1.5.10"
    val springBootVersion = "2.4.5"
    val springDepsVersion = "1.0.11.RELEASE"

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

val kotlinVersion = "1.5.10"
val coroutinesVersion = "1.5.0"
val junitVersion = "5.8.0-M1"

val mongodbVersion = "4.2.3"
val kmongoVersion = "4.2.7"

val springDocVersion = "1.5.8"

val opentracingApiVersion = "0.33.0"
val opentracingSpringVersion = "0.5.9"

val mockitoVersion = "3.9.0"
val mockitoKotlinVersion = "3.2.0"

dependencies {
    annotationProcessor("org.springframework.boot", "spring-boot-configuration-processor")
    implementation("org.springframework.boot", "spring-boot-starter-webflux")
//    implementation("com.graphql-java-kickstart", "graphql-kickstart-spring-boot-starter-webflux", "11.0.0")
//    implementation("com.graphql-java-kickstart", "graphql-java-tools", "11.0.1")
    implementation("org.springdoc","springdoc-openapi-webflux-core", springDocVersion)
    implementation("org.springdoc","springdoc-openapi-webflux-ui", springDocVersion)
    implementation("org.springdoc","springdoc-openapi-kotlin", springDocVersion)
    api("io.opentracing", "opentracing-api", opentracingApiVersion)
    implementation("io.opentracing.contrib", "opentracing-spring-cloud-starter", opentracingSpringVersion)
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.projectreactor", "reactor-core", "3.4.6")
    implementation("io.projectreactor.netty", "reactor-netty", "1.0.7")
    implementation("io.projectreactor.netty", "reactor-netty-http", "1.0.7")
    implementation("io.projectreactor.netty", "reactor-netty-core", "1.0.7")

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

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}
