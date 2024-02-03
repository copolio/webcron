package com.copolio.webcron.worker.config

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
        "classpath:application-worker.yml",
        "classpath:application-worker-\${spring.profiles.active}.yml"
    ],
    factory = YamlPropertySourceFactory::class,
    ignoreResourceNotFound = true
)
class WorkerDataSourceConfig {
    @Bean
    @ConfigurationProperties(value = "spring.datasource.webcron.worker")
    fun workerDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.webcron.worker.hikari")
    fun workerDataSource(): HikariDataSource {
        return workerDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }
}