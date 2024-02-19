package net.dilius.untitled.domain.users

import org.litote.kmongo.id.WrappedObjectId
import org.springframework.stereotype.Service

@Service
class UserService(private val userDao: UserDao) {
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
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
