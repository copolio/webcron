package com.copolio.webcron.config

import com.copolio.webcron.model.HttpJob
import com.copolio.webcron.port.`in`.HttpJobCommandUseCase
import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean

class QuartzConfig(
    private val httpJobCommandUseCase: HttpJobCommandUseCase,
) : QuartzJobBean() {
    private lateinit var httpJob: HttpJob

    fun setHttpJob(httpJob: HttpJob) {
        this.httpJob = httpJob
    }

    override fun executeInternal(context: JobExecutionContext) {
        httpJobCommandUseCase.execute(httpJob)
    }
}
