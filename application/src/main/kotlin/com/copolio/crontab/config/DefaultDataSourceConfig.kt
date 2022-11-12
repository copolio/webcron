package com.copolio.crontab.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class DefaultDataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties(value = "spring.datasource.application")
    fun defaultDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.application.hikari")
    fun defaultDataSource(): HikariDataSource {
        return defaultDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun defaultJdbcTemplate(@Qualifier("defaultDataSource") defaultDataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(defaultDataSource)
    }
}