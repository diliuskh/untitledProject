package net.dilius.untitled.domain.users

import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class User(
    val id: Id<User> = newId(),
    val name: String,
    val email: String,
    val password: String)
