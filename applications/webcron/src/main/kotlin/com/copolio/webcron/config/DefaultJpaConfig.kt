package com.copolio.webcron.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*

@Configuration
@EnableTransactionManagement
class DefaultJpaConfig {
    @Primary
    @Bean
    fun defaultEntityManagerFactory(
        @Qualifier("webcronEntityManagerFactory") webcronEntityManagerFactory: LocalContainerEntityManagerFactoryBean,
    ): LocalContainerEntityManagerFactoryBean {
        return webcronEntityManagerFactory
    }

    @Primary
    @Bean
    fun defaultTransactionManager(
        @Qualifier("webcronTransactionManager") webcronTransactionManager: PlatformTransactionManager
    ): PlatformTransactionManager {
        return webcronTransactionManager
    }
}