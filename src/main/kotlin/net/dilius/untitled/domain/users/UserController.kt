package net.dilius.untitled.domain.users

import org.litote.kmongo.id.StringId
import org.litote.kmongo.newId
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/users/{id}")
    suspend fun getUser(@PathVariable id: String): User? {
        return userService.findUserById(id)
    }

    @GetMapping("/users")
    suspend fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @PostMapping("/users")
    suspend fun saveUser(@RequestBody user: User) {
        userService.saveUser(user)
    }

    @PutMapping("/users")
    suspend fun updateUser(@RequestBody user: User) {
        userService.updateUser(user)
    }

    @PatchMapping("/users")
    suspend fun patchUser(@RequestBody user: User) {
        userService.updateUser(user)
    }

}