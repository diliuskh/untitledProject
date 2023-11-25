package net.dilius.untitled.domain.auth

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @PostMapping("/auth")
    suspend fun auth() {

    }
}