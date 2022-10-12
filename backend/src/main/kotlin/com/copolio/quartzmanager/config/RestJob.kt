package com.copolio.quartzmanager.config

import com.copolio.quartzmanager.services.RestService
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.scheduling.quartz.QuartzJobBean

class RestJob(
    private val restService: RestService,
    private val logger: Logger = LoggerFactory.getLogger(RestJob::class.java)
) : QuartzJobBean() {
    private lateinit var url: String
    private var username: String? = null
    private var password: String? = null
    private var httpMethod: HttpMethod = HttpMethod.GET

    fun setUrl(url: String) {
        this.url = url
    }

    fun setUsername(user: String) {
        this.username = user
    }

    fun setPassword(pass: String) {
        this.password = pass
    }

    fun setHttpMethod(httpMethod: HttpMethod) {
        this.httpMethod = httpMethod
    }

    override fun executeInternal(context: JobExecutionContext) {
        logger.info("Start RestJob")
        restService.sendRequest(url = url)
        logger.info("End RestJob")
    }
}
