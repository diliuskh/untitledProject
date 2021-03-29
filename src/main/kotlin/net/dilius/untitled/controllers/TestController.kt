package net.dilius.untitled.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping
    fun test(): String {
        return "Hello!"
    }
}
