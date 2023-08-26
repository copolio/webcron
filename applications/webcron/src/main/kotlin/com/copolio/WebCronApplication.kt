package com.copolio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebCronApplication

fun main(args: Array<String>) {
    runApplication<WebCronApplication>(*args)
}
