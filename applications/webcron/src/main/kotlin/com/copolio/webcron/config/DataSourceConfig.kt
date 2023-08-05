package com.copolio.webcron.config

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
class DataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties(value = "spring.datasource.application")
    fun defaultDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.application.hikari")
    fun defaultDataSource(): HikariDataSource {
        return defaultDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Primary
    @Bean
    fun defaultJdbcTemplate(@Qualifier("defaultDataSource") defaultDataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(defaultDataSource)
    }
}