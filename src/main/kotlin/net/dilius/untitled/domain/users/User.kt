package net.dilius.untitled.domain.users

import org.litote.kmongo.Id
import org.litote.kmongo.id.StringId

data class User(
    val id: StringId<User>,
    val name: String,
    val email: String,
    val password: String)
