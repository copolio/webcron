package com.copolio.domains.quartz.config

import com.copolio.core.YamlPropertySourceFactory
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@ConfigurationProperties(prefix = "yaml")
@PropertySource(
    value = [
        "classpath:application-quartz.yml",
        "classpath:application-quartz-\${spring.profiles.active}.yml"
    ],
    factory = YamlPropertySourceFactory::class,
    ignoreResourceNotFound = true
)
class QuartzDataSourceConfig {
    @Bean
    @ConfigurationProperties(value = "spring.datasource.quartz")
    fun quartzApiDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.quartz.hikari")
    fun quartzDataSource(): HikariDataSource {
        return quartzApiDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }
}