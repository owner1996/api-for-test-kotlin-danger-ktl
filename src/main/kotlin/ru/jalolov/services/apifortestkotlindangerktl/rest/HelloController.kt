package ru.jalolov.services.apifortestkotlindangerktl.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(
        @RequestParam(defaultValue = "user") name: String
    ): String = "Hello, $name"

    @GetMapping("/bye")
    fun bye(
        @RequestParam(defaultValue = "user") name: String
    ): String = "bye, $name"

    // комментарий для теста
    // комментарий для теста #1
    // комментарий для теста #2
    // комментарий для теста #3
}