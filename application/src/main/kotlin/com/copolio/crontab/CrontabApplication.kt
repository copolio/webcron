package com.copolio.crontab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CrontabApplication

fun main(args: Array<String>) {
    runApplication<CrontabApplication>(*args)
}
