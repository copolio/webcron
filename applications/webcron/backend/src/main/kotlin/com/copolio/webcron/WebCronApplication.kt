package com.copolio.webcron

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.copolio.webcron", "com.copolio.domains"])
class WebCronApplication

fun main(args: Array<String>) {
    runApplication<WebCronApplication>(*args)
}
