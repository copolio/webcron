package com.copolio.apiquartz.config

import org.quartz.Scheduler
import org.quartz.spi.TriggerFiredBundle
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SpringBeanJobFactory

@Configuration
class QuartzConfiguration {
    class AutowireCapableBeanJobFactory(val beanJobFactory: AutowireCapableBeanFactory) : SpringBeanJobFactory() {
        override fun createJobInstance(bundle: TriggerFiredBundle): Any {
            val jobInstance = super.createJobInstance(bundle)
            beanJobFactory.autowireBean(jobInstance)
            beanJobFactory.initializeBean(jobInstance, "test job")
            return jobInstance
        }
    }

    @Bean
    fun schedulerFactory(applicationContext: ApplicationContext): SchedulerFactoryBean {
        val schedulerFactoryBean = SchedulerFactoryBean()
        schedulerFactoryBean.setJobFactory(AutowireCapableBeanJobFactory(applicationContext.autowireCapableBeanFactory))
        return schedulerFactoryBean
    }

    @Bean
    fun scheduler(applicationContext: ApplicationContext): Scheduler {
        val scheduler = schedulerFactory(applicationContext).scheduler
        scheduler.start()
        return scheduler
    }
}