package net.dilius.untitled.lib.mongo

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.litote.kmongo.coroutine.CoroutineClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KMongoConfiguration {

    @Bean
    fun coroutineClient(@Value("\${mongodb.uri}") uri: String): CoroutineClient {
        val mongoClient: MongoClient = MongoClients.create(uri)
        return CoroutineClient(mongoClient)
    }
}
