package com.copolio.quartzapi.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class QuartzApiDbConfig {
    @Bean
    @ConfigurationProperties(value = "spring.datasource-quartzapi")
    fun quartzApiDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties("spring.datasource-quartzapi.hikari")
    fun quartzApiDataSource(): DataSource {
        return quartzApiDataSourceProperties()
            .initializeDataSourceBuilder()
            .build()
    }
}