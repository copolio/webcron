package com.copolio.webcron.config

import com.copolio.domains.quartz.dto.HttpJobRequest
import com.copolio.domains.quartz.service.HttpJobService
import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean

class HttpJob(
    private val httpJobService: HttpJobService,
) : QuartzJobBean() {
    private lateinit var httpJobRequest: HttpJobRequest

    fun setHttpJobRequest(httpJobRequest: HttpJobRequest) {
        this.httpJobRequest = httpJobRequest
    }

    override fun executeInternal(context: JobExecutionContext) {
        httpJobService.sendRequest(httpJobRequest)
    }
}
