package com.copolio.domains.webcron.config

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
        "classpath:application-webcron.yml",
        "classpath:application-webcron-\${spring.profiles.active}.yml"
    ],
    factory = YamlPropertySourceFactory::class,
    ignoreResourceNotFound = true
)
class WebcronDataSourceConfig {
    @Bean
    @ConfigurationProperties(value = "spring.datasource.webcron")
    fun webcronDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.webcron.hikari")
    fun webcronDataSource(): HikariDataSource {
        return webcronDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }
}