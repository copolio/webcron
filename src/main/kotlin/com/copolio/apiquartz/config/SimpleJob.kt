package com.copolio.apiquartz.config

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SimpleJob(
    private val logger: Logger = LoggerFactory.getLogger(SimpleJob::class.java)
) : Job {

    override fun execute(context: JobExecutionContext?) {
        logger.info("Executing SimpleJob .....")
    }
}