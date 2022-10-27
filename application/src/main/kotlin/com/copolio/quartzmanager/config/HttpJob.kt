package com.copolio.quartzmanager.config

import com.copolio.quartzmanager.service.HttpService
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.scheduling.quartz.QuartzJobBean

class HttpJob(
    private val httpService: HttpService,
    private val logger: Logger = LoggerFactory.getLogger(HttpJob::class.java)
) : QuartzJobBean() {
    private lateinit var url: String
    private var username: String? = null
    private var password: String? = null
    private var requestBody: String? = null
    private var httpMethod: HttpMethod = HttpMethod.GET

    fun setUrl(url: String) {
        this.url = if (url.startsWith("http")) url else "https://$url"
    }

    fun setUsername(user: String?) {
        this.username = user
    }

    fun setPassword(pass: String?) {
        this.password = pass
    }

    fun setRequestBody(req: String?) {
        this.requestBody = req
    }

    fun setHttpMethod(httpMethod: HttpMethod) {
        this.httpMethod = httpMethod
    }

    override fun executeInternal(context: JobExecutionContext) {
        logger.info("Start RestJob")
        httpService.send(url = url, httpMethod = httpMethod, body = requestBody)
        logger.info("End RestJob")
    }
}
