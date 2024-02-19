package net.dilius.untitled

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.litote.kmongo.id.ObjectIdGenerator
import org.litote.kmongo.id.jackson.IdJacksonModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.jackson.JsonComponentModule
import org.springframework.boot.jackson.JsonMixinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.jackson2.CoreJackson2Module
import org.springframework.security.oauth2.client.jackson2.OAuth2ClientJackson2Module
import org.springframework.security.web.jackson2.WebJackson2Module
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class ApplicationConfiguration : WebFluxConfigurer {
    @Bean
    fun customJacksonModuleCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { jacksonObjectMapperBuilder ->
            jacksonObjectMapperBuilder.modules(
                Jdk8Module(),
                JavaTimeModule(),
                ParameterNamesModule(),
                JsonComponentModule(),
                JsonMixinModule(),
                CoreJackson2Module(),
                OAuth2ClientJackson2Module(),
                WebJackson2Module(),
                IdJacksonModule(ObjectIdGenerator),
                KotlinModule.Builder().build(),
            )
        }
    }
}
