package net.dilius.untitled.domain.users

import org.litote.kmongo.Id
import org.litote.kmongo.id.ObjectIdGenerator
import java.time.LocalDateTime

data class User(
    val id: Id<User> = ObjectIdGenerator.generateNewId(),
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val authorities: List<String> = mutableListOf(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val authorizedClient: AuthorizedClient? = null,
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)

data class AuthorizedClient(
    val clientRegistrationId: String,
    val principalName: String,
    val accessTokenType: String,
    val accessTokenValue: String,
    val accessTokenIssuedAt: LocalDateTime,
    val accessTokenExpiresAt: LocalDateTime,
    val accessTokenScopes: List<String>? = null,
    val refreshTokenValue: String? = null,
    val refreshTokenIssuedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
