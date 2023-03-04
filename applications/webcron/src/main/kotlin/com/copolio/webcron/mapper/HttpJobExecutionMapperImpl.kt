package com.copolio.webcron.mapper

import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.webcron.port.`in`.HttpJobExecutionSearchQueryResult
import org.springframework.stereotype.Service

@Service
class HttpJobExecutionMapperImpl : HttpJobExecutionMapper {
    override fun convert(httpJobExecution: HttpJobExecution): HttpJobExecutionSearchQueryResult {
        return HttpJobExecutionSearchQueryResult(
            jobGroup = httpJobExecution.jobGroup,
            jobName = httpJobExecution.jobName,
            startTime = httpJobExecution.startTime,
            endTime = httpJobExecution.endTime,
            statusCode = httpJobExecution.statusCode,
            response = httpJobExecution.response
        )
    }
}