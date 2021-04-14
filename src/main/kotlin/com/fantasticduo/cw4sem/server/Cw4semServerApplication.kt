package com.fantasticduo.cw4sem.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping




@SpringBootApplication
class Cw4semServerApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<Cw4semServerApplication>(*args)
        }
    }
}
