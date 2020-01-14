package net.dilius.untitled

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("application")
data class ApplicationProperties(var title: String, val banner: Banner) {
    data class Banner(val title: String? = null, val content: String)
}
