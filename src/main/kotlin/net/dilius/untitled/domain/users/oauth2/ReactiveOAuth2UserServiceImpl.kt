package net.dilius.untitled.domain.users.oauth2

import net.dilius.untitled.domain.users.UserDetailsImpl
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService
import reactor.core.publisher.Mono

class ReactiveOAuth2UserServiceImpl : ReactiveOAuth2UserService<OAuth2UserRequest, UserDetailsImpl> {
    override fun loadUser(userRequest: OAuth2UserRequest): Mono<UserDetailsImpl> {
        TODO("Not yet implemented")
    }
}
