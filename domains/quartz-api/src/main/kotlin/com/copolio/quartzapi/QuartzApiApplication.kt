package com.copolio.quartzapi

import com.copolio.springutil.YamlPropertySourceFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@ConfigurationProperties(prefix = "yaml")
@PropertySource(
    value = [
        "classpath:application-quartz-api.yml",
        "classpath:application-quartz-api-\${spring.profiles.active}.yml"
    ],
    factory = YamlPropertySourceFactory::class,
    ignoreResourceNotFound = true
)
class QuartzApiApplication

fun main(args: Array<String>) {
    runApplication<QuartzApiApplication>(*args)
}
