package net.dilius.untitled.domain.users

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfiguration {
    @Bean
    fun userCollection(coroutineDatabase: CoroutineDatabase) = coroutineDatabase.getCollection<User>("users")
}
