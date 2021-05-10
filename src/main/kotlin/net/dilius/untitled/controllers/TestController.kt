package net.dilius.untitled.controllers

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController() {

    @GetMapping
    fun test(): Mono<String> = mono { coroutineScope { "Hello!" } }
}
