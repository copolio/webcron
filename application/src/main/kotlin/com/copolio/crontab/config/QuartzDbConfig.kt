package com.copolio.crontab.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class QuartzDbConfig {
    @Bean
    @Primary
    @ConfigurationProperties(value = "spring.datasource")
    fun quartzDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.hikari")
    fun quartzDataSource(): DataSource {
        return quartzDataSourceProperties()
            .initializeDataSourceBuilder()
            .build()
    }

    @Bean
    fun quartzJdbcTemplate(@Qualifier("quartzDataSource") quartzDataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(quartzDataSource)
    }
}