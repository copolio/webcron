package com.copolio.domains.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchApiApplication

fun main(args: Array<String>) {
    runApplication<BatchApiApplication>(*args)
}
