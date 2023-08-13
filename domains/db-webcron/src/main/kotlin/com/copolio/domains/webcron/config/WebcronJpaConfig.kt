package com.copolio.domains.webcron.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.copolio.domains.webcron.repository"],
    entityManagerFactoryRef = "webcronEntityManagerFactory",
    transactionManagerRef = "webcronTransactionManager"
)
class WebcronJpaConfig {
    @Bean
    fun webcronEntityManagerFactory(
        @Qualifier("webcronDataSource") webcronDataSource: DataSource,
        entityManagerFactoryBean: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        val em = entityManagerFactoryBean
            .dataSource(webcronDataSource)
            .packages("com.copolio.domains.webcron.entity")
            .build()
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        return em
    }

    @Bean
    fun webcronTransactionManager(
        @Qualifier("webcronEntityManagerFactory") webcronEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(
            Objects.requireNonNull(webcronEntityManagerFactory.`object`)!!
        )
    }
}