package net.dilius.untitled.domain.users

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.time.LocalDateTime

@Serializable
data class User(
    @Contextual
    @SerialName("_id")
    val id: String = ObjectId().toHexString(),
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val authorities: List<String> = mutableListOf(),
    @Contextual
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val authorizedClient: AuthorizedClient? = null,
    @Contextual
    val updatedAt: LocalDateTime = LocalDateTime.now()
)


@Serializable
data class AuthorizedClient(
    val clientRegistrationId: String,
    val principalName: String,
    val accessTokenType: String,
    val accessTokenValue: String,
    @Contextual
    val accessTokenIssuedAt: LocalDateTime,
    @Contextual
    val accessTokenExpiresAt: LocalDateTime,
    val accessTokenScopes: List<String>? = null,
    val refreshTokenValue: String? = null,
    @Contextual
    val refreshTokenIssuedAt: LocalDateTime? = null,
    @Contextual
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
