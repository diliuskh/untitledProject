package net.dilius.untitled.domain.users

import org.litote.kmongo.id.StringId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/users/{id}")
    suspend fun getUser(id: String): User? {
        return userService.findUserById(StringId(id))
    }

    @GetMapping("/users")
    suspend fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @PostMapping("/users")
    suspend fun saveUser(user: User) {
        userService.saveUser(user)
    }

    @PutMapping("/users")
    suspend fun updateUser(user: User) {
        userService.updateUser(user)
    }

    @PatchMapping("/users")
    suspend fun patchUser(user: User) {
        userService.updateUser(user)
    }

}