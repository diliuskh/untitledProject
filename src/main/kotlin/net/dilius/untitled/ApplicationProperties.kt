package net.dilius.untitled

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties("application")
data class ApplicationProperties @ConstructorBinding constructor(var title: String, val banner: Banner) {
    data class Banner(val title: String? = null, val content: String)
}
