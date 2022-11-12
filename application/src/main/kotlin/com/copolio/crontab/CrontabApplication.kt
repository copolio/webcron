package com.copolio.crontab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.copolio.crontab", "com.copolio.domains"])
class CrontabApplication

fun main(args: Array<String>) {
    runApplication<CrontabApplication>(*args)
}
