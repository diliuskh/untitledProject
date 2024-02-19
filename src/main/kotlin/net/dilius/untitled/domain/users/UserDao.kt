package net.dilius.untitled.domain.users

import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.eq
import org.springframework.stereotype.Repository

@Repository
class UserDao(private val mongoClient: CoroutineClient) {
    suspend fun getUsers(): List<User> {
        return mongoClient
            .getDatabase("untitled")
            .getCollection<User>("users")
            .find().toList()
    }

    suspend fun updateUser(user: User) {
        mongoClient
            .getDatabase("untitled")
            .getCollection<User>("users")
            .replaceOneById(user.id, user)
    }

    suspend fun findUserById(id: Id<User>): User? {
        return mongoClient
            .getDatabase("untitled")
            .getCollection<User>("users")
            .find(User::id eq id).first()
    }

    suspend fun findUserByEmail(email: String): User? {
        return mongoClient
            .getDatabase("untitled")
            .getCollection<User>("users")
            .find(User::email eq email).first()
    }

    suspend fun saveUser(user: User) {
        mongoClient
            .getDatabase("untitled")
            .getCollection<User>("users")
            .insertOne(user)
    }
}
