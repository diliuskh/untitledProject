package net.dilius.untitled.domain.users.oauth2

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService
import reactor.core.publisher.Mono

class ReactiveOAuth2AuthorizedClientServiceImpl : ReactiveOAuth2AuthorizedClientService {
    override fun <T : OAuth2AuthorizedClient> loadAuthorizedClient(
        clientRegistrationId: String,
        principalName: String?,
    ): Mono<T> {
        TODO("Not yet implemented")
    }

    override fun saveAuthorizedClient(
        authorizedClient: OAuth2AuthorizedClient,
        principal: Authentication,
    ): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun removeAuthorizedClient(
        clientRegistrationId: String,
        principalName: String,
    ): Mono<Void> {
        TODO("Not yet implemented")
    }
}
