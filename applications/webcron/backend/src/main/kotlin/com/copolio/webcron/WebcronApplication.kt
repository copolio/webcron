package com.copolio.webcron

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.copolio.webcron", "com.copolio.domains"])
class WebcronApplication

fun main(args: Array<String>) {
    runApplication<WebcronApplication>(*args)
}
