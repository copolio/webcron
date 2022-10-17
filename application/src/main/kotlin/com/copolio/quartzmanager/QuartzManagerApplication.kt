package com.copolio.quartzmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuartzManagerApplication

fun main(args: Array<String>) {
    runApplication<QuartzManagerApplication>(*args)
}
