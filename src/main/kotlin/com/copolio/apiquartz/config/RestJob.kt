package com.copolio.apiquartz.config

import com.copolio.apiquartz.services.RestService
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.quartz.QuartzJobBean

class RestJob(
    private val restService: RestService,
    private val logger: Logger = LoggerFactory.getLogger(RestJob::class.java)
) : QuartzJobBean() {
    private lateinit var url: String

    fun setUrl(url: String) {
        this.url = url
    }

    override fun executeInternal(context: JobExecutionContext) {
        logger.info("Start RestJob")
        restService.sendRequest(url = url)
        logger.info("End RestJob")
    }
}
