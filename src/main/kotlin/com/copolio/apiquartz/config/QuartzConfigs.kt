//package com.copolio.apiquartz.config
//
//import org.springframework.core.io.ClassPathResource
//import org.springframework.scheduling.quartz.SchedulerFactoryBean
//
//class QuartzConfigs {
//    fun schedulerFactoryBean(): SchedulerFactoryBean {
//        val schedulerFactoryBean = SchedulerFactoryBean()
//        schedulerFactoryBean.setConfigLocation(ClassPathResource("quartz.properties"))
//        return schedulerFactoryBean
//    }
//}