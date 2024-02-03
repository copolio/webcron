package com.copolio.webcron.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class DataSourceConfig {
    @Primary
    @Bean
    fun defaultDataSourceProperties(@Qualifier("quartzDataSourceProperties") quartzDataSourceProperties: DataSourceProperties): DataSourceProperties {
        return quartzDataSourceProperties
    }

    @Primary
    @Bean
    fun defaultDataSource(@Qualifier("quartzDataSource") quartzDataSource: HikariDataSource): HikariDataSource {
        return quartzDataSource
    }

    @Primary
    @Bean
    fun defaultJdbcTemplate(@Qualifier("defaultDataSource") defaultDataSource: HikariDataSource): JdbcTemplate {
        return JdbcTemplate(defaultDataSource)
    }
}