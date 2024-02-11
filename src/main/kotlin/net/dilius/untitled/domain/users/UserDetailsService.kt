package net.dilius.untitled.domain.users

import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserDetailsService(private val userService: UserService) : ReactiveUserDetailsService,
    ReactiveUserDetailsPasswordService, AuthenticationUserDetailsService<OAuth2LoginAuthenticationToken> {
    override fun findByUsername(username: String): Mono<UserDetails> {
        return mono {
            userService.findUserByEmail(username)?.let { UserDetailsImpl(it) }
                ?: throw UsernameNotFoundException("User not found")
        }
    }

    override fun updatePassword(user: UserDetails, newPassword: String): Mono<UserDetails> {
        TODO("Not yet implemented")
    }

    override fun loadUserDetails(token: OAuth2LoginAuthenticationToken): UserDetails {
        TODO("Not yet implemented")
    }
}