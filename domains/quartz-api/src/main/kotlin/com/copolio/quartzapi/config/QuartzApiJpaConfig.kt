package com.copolio.quartzapi.config

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
    basePackages = ["com.copolio.quartzapi.entity", "com.copolio.quartzapi.repository"],
    entityManagerFactoryRef = "quartzApiEntityManagerFactory",
    transactionManagerRef = "quartzApiTransactionManager"
)
class QuartzApiJpaConfig {
    @Bean
    fun quartzApiEntityManagerFactory(
        @Qualifier("quartzApiDataSource") quartzApiDataSource: DataSource,
        entityManagerFactoryBean: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        val em = entityManagerFactoryBean
            .dataSource(quartzApiDataSource)
            .packages("com.copolio.quartzapi.entity")
            .build()
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        return em
    }

    @Bean
    fun quartzApiTransactionManager(
        @Qualifier("quartzApiEntityManagerFactory") quartzApiEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(
            Objects.requireNonNull(quartzApiEntityManagerFactory.`object`)!!
        )
    }
}