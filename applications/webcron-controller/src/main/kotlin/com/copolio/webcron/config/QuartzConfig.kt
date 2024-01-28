package com.copolio.webcron.config

import com.copolio.webcron.domain.HttpJob
import com.copolio.webcron.port.`in`.HttpJobPublishUseCase
import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean

class QuartzConfig(
    private val httpJobPublishUseCase: HttpJobPublishUseCase,
) : QuartzJobBean() {
    private lateinit var httpJob: HttpJob

    fun setHttpJob(httpJob: HttpJob) {
        this.httpJob = httpJob
    }

    override fun executeInternal(context: JobExecutionContext) {
        httpJobPublishUseCase.publish(httpJob)
    }
}
