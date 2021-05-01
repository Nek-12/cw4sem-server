package com.fantastictrio.cw4sem.controller

import com.fantastictrio.cw4sem.model.Greeting
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {
    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String?): Greeting {
        return Greeting(++counter, String.format(template, name))
    }

    @PostMapping("/counter")
    fun changeCounter(@RequestParam(value = "counter") c: Long) {
        counter = c
    }

    companion object {
        private const val template = "Hello, %s!"
        private var counter = 0L
    }
}
