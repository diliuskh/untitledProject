package net.dilius.untitled.domain.users

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.types.ObjectId
import java.time.LocalDateTime

@Serializable
data class User(
    @Contextual
    @SerialName("_id")
    val id: String = ObjectId().toHexString(),
    val name: String,
    val email: String,
    val password: String,
    val authorities: List<String> = mutableListOf(),
    @Contextual
    val created: LocalDateTime = LocalDateTime.now(),
    @Contextual
    val updated: LocalDateTime = LocalDateTime.now()
)
