package com.copolio.webcron.quartz.config

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
    basePackages = ["com.copolio.webcron.quartz.repository"],
    entityManagerFactoryRef = "quartzEntityManagerFactory",
    transactionManagerRef = "quartzTransactionManager"
)
class QuartzJpaConfig {
    @Bean
    fun quartzEntityManagerFactory(
        @Qualifier("quartzDataSource") quartzDataSource: DataSource,
        quartzEntityManagerFactoryBean: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        val em = quartzEntityManagerFactoryBean
            .dataSource(quartzDataSource)
            .packages("com.copolio.webcron.quartz.entity")
            .build()
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        return em
    }

    @Bean
    fun quartzTransactionManager(
        @Qualifier("quartzEntityManagerFactory") quartzEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(
            Objects.requireNonNull(quartzEntityManagerFactory.`object`)!!
        )
    }
}