package com.copolio.webcron.mapper

import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.clients.webcronclient.dto.query.HttpJobExecutionInfo
import org.springframework.stereotype.Service

@Service
class HttpJobExecutionMapperImpl : HttpJobExecutionMapper {
    override fun convert(httpJobExecution: HttpJobExecution): HttpJobExecutionInfo {
        return HttpJobExecutionInfo(
            jobGroup = httpJobExecution.jobGroup,
            jobName = httpJobExecution.jobName,
            startTime = httpJobExecution.startTime,
            endTime = httpJobExecution.endTime,
            statusCode = httpJobExecution.statusCode,
            response = httpJobExecution.response
        )
    }
}