package net.dilius.untitled.lib.mongo

import com.mongodb.reactivestreams.client.MongoClient
import org.litote.kmongo.coroutine.CoroutineClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

@Configuration
@DependsOn("mongoConfiguration")
class KMongoConfiguration {

    @Bean
    fun coroutineClient(mongoClient: MongoClient) = CoroutineClient(mongoClient)
}
