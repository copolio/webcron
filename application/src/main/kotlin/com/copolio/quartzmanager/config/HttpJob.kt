package com.copolio.quartzmanager.config

import com.copolio.quartzmanager.domain.HttpJobExecution
import com.copolio.quartzmanager.domain.HttpJobExecutionRepository
import com.copolio.quartzmanager.service.HttpService
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.scheduling.quartz.QuartzJobBean
import java.time.LocalDateTime

class HttpJob(
    private val httpService: HttpService,
    private val httpJobExecutionRepository: HttpJobExecutionRepository,
    private val logger: Logger = LoggerFactory.getLogger(HttpJob::class.java),
) : QuartzJobBean() {
    private lateinit var url: String
    private var jobName: String? = null
    private var jobGroup: String? = null
    private var username: String? = null
    private var password: String? = null
    private var requestBody: String? = null
    private var httpMethod: HttpMethod = HttpMethod.GET
    private var count: Int = 0

    fun setJobName(jobName: String?) {
        this.jobName = jobName
    }

    fun setJobGroup(jobGroup: String?) {
        this.jobGroup = jobGroup
    }

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

    fun setCount(count: Int) {
        this.count = count
    }

    override fun executeInternal(context: JobExecutionContext) {
        val startTime = LocalDateTime.now()
        val response = httpService.send(url = url, httpMethod = httpMethod, body = requestBody)
        val endTime = LocalDateTime.now()
        val httpJobExecution = HttpJobExecution(
            startTime = startTime,
            endTime = endTime,
            statusCode = response.statusCode,
            response = response.body.toString(),
            jobName = this.jobName!!,
            jobGroup = this.jobGroup!!
        )
        httpJobExecutionRepository.save(httpJobExecution)
        if (response.statusCode.is4xxClientError || response.statusCode.is5xxServerError) {
            count++
            if (count < 3) {
                throw JobExecutionException(
                    response.body.toString(),
                    true
                )
            }
        }
        count = 0
    }
}
