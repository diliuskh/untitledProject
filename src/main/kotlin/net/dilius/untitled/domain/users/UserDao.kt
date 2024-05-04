package net.dilius.untitled.domain.users

import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.setTo
import org.springframework.stereotype.Repository

@Repository
class UserDao(private val db: CoroutineCollection<User>) {
    suspend fun getUsers(): List<User> = db.find().toList()

    suspend fun updateUser(user: User) = db.replaceOneById(user.id, user)

    suspend fun updateUserPassword(
        id: Id<User>,
        password: String,
    ) = db.updateOneById(id, User::password setTo password)

    suspend fun updateUserPasswordByUsername(
        username: String,
        password: String,
    ) = db.updateOne(User::username eq username, User::password eq password)

    suspend fun findUserById(id: Id<User>): User? = db.find(User::id eq id).first()

    suspend fun findUserByEmail(email: String): User? = db.find(User::email eq email).first()

    suspend fun saveUser(user: User) = db.insertOne(user)
}
