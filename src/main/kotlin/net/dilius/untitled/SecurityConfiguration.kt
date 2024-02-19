package net.dilius.untitled

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf {
                it.disable()
            }
            .cors {
                it.disable()
//            }.authorizeExchange {
//                it.anyExchange().authenticated()
//            }.oauth2Login {
            }
            .build()
    }
}
