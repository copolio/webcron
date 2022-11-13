package com.copolio.domains.quartz.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
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