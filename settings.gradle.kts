rootProject.name = "untitledProject"


dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.9.20")
            version("coroutines", "1.7.3")
            version("serialization", "1.6.1")
            version("springBoot", "3.1.5")
            version("mongodb", "4.11.1")
            version("kmongo", "4.11.0")
            version("netty", "4.1.101.Final")

            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlin")
            library("kotlin-stdlib-jdk7", "org.jetbrains.kotlin", "kotlin-stdlib-jdk7").versionRef("kotlin")
            library("kotlin-stdlib-jdk8", "org.jetbrains.kotlin", "kotlin-stdlib-jdk8").versionRef("kotlin")
            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
        }
    }
}
