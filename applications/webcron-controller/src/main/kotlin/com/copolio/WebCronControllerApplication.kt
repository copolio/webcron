package com.copolio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebCronControllerApplication

fun main(args: Array<String>) {
    runApplication<WebCronControllerApplication>(*args)
}
