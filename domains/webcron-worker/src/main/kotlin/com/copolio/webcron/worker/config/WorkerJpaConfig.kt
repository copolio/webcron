package com.copolio.webcron.worker.config

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
    basePackages = ["com.copolio.webcron.worker.repository"],
    entityManagerFactoryRef = "workerEntityManagerFactory",
    transactionManagerRef = "workerTransactionManager"
)
class WorkerJpaConfig {
    @Bean
    fun workerEntityManagerFactory(
        @Qualifier("workerDataSource") workerDataSource: DataSource,
        workerEntityManagerFactoryBean: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        val em = workerEntityManagerFactoryBean
            .dataSource(workerDataSource)
            .packages("com.copolio.webcron.worker.entity")
            .build()
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        return em
    }

    @Bean
    fun workerTransactionManager(
        @Qualifier("workerEntityManagerFactory") workerEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(
            Objects.requireNonNull(workerEntityManagerFactory.`object`)!!
        )
    }
}