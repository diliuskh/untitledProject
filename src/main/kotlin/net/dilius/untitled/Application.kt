package net.dilius.untitled

import org.litote.kmongo.serialization.SerializationClassMappingTypeService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties::class)
class Application

fun main(args: Array<String>) {
    System.setProperty("org.litote.mongo.mapping.service", SerializationClassMappingTypeService::class.qualifiedName!!)
    runApplication<Application>(*args)
}
