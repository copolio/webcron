package com.copolio.webcron.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class JpaConfig {
    @Primary
    @Bean
    fun defaultEntityManagerFactory(
        @Qualifier("workerEntityManagerFactory") workerEntityManagerFactory: LocalContainerEntityManagerFactoryBean,
    ): LocalContainerEntityManagerFactoryBean {
        return workerEntityManagerFactory
    }

    @Primary
    @Bean
    fun defaultTransactionManager(
        @Qualifier("workerTransactionManager") workerTransactionManager: PlatformTransactionManager
    ): PlatformTransactionManager {
        return workerTransactionManager
    }
}