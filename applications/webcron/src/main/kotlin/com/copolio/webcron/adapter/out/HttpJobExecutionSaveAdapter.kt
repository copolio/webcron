package com.copolio.webcron.adapter.out

import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.domains.quartz.repository.HttpJobExecutionRepository
import com.copolio.webcron.port.`in`.CreateHttpJobExecution
import com.copolio.webcron.port.out.HttpJobExecutionSavePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HttpJobExecutionSaveAdapter(
    private val httpJobExecutionRepository: HttpJobExecutionRepository
) : HttpJobExecutionSavePort {
    @Transactional
    override fun handle(
        createHttpJobExecution: CreateHttpJobExecution
    ) {
        val httpJobExecution = HttpJobExecution(
            startTime = createHttpJobExecution.startTime,
            endTime = createHttpJobExecution.endTime,
            statusCode = createHttpJobExecution.httpStatus,
            response = createHttpJobExecution.response,
            jobName = createHttpJobExecution.jobName,
            jobGroup = createHttpJobExecution.jobGroup
        )
        httpJobExecutionRepository.save(httpJobExecution)
    }
}