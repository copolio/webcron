package com.copolio.domains.quartz

import com.copolio.core.YamlPropertySourceFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@ConfigurationProperties(prefix = "yaml")
@PropertySource(
    value = [
        "classpath:application-quartz.yml",
        "classpath:application-quartz-\${spring.profiles.active}.yml"
    ],
    factory = YamlPropertySourceFactory::class,
    ignoreResourceNotFound = true
)
class QuartzApplication

fun main(args: Array<String>) {
    runApplication<QuartzApplication>(*args)
}
