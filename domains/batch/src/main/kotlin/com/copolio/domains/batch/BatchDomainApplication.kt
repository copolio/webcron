package com.copolio.domains.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchDomainApplication

fun main(args: Array<String>) {
    runApplication<BatchDomainApplication>(*args)
}
