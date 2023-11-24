package net.dilius.untitled.domain.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class User(
    @SerialName("_id")
    val id: String = ObjectId().toHexString(),
    val name: String,
    val email: String,
    val password: String)
