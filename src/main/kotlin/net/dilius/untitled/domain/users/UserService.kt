package net.dilius.untitled.domain.users

import org.litote.kmongo.id.WrappedObjectId
import org.springframework.stereotype.Service

@Service
class UserService(private val userDao: UserDao) {
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun updateUserPassword(
        id: String,
        password: String,
    ) {
        userDao.updateUserPassword(WrappedObjectId(id), password)
    }

    suspend fun updateUserPasswordByUsername(
        username: String,
        password: String,
    ) {
        userDao.updateUserPasswordByUsername(username, password)
    }

    suspend fun findUserById(id: String): User? {
        return userDao.findUserById(WrappedObjectId(id))
    }

    suspend fun findUserByEmail(email: String): User? {
        return userDao.findUserByEmail(email)
    }

    suspend fun saveUser(user: User) {
        userDao.saveUser(user)
    }

    suspend fun getUsers(): List<User> {
        return userDao.getUsers()
    }
}
