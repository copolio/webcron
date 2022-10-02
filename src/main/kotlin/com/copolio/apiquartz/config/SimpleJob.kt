package com.copolio.apiquartz.config

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory

class SimpleJob : Job {
    val logger = LoggerFactory.getLogger(SimpleJob::class.java)

    override fun execute(context: JobExecutionContext?) {
        logger.info("Executing SimpleJob .....")
    }
}