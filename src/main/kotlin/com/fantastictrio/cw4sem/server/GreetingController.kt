package com.fantastictrio.cw4sem.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {
    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String?): Greeting {
        return Greeting(++counter, String.format(template, name))
    }

    companion object {
        private const val template = "Hello, %s!"
        private var counter = 0L
    }
}
