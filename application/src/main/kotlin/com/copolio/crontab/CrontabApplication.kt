package com.copolio.crontab

import com.copolio.springutil.YamlPropertySourceFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@ComponentScan(basePackages = ["com.copolio.crontab", "com.copolio.quartzapi"])
@ConfigurationProperties(prefix = "yaml")
@PropertySource(
    value = [
        "classpath:application.yml",
        "classpath:application-\${spring.profiles.active}.yml"
    ],
    factory = YamlPropertySourceFactory::class,
    ignoreResourceNotFound = true
)
class CrontabApplication

fun main(args: Array<String>) {
    runApplication<CrontabApplication>(*args)
}
