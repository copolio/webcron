package com.copolio.webcron.mapper

import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.webcron.port.`in`.HttpJobExecutionSearchQueryResult

interface HttpJobExecutionMapper {
    fun convert(httpJobExecution: HttpJobExecution): HttpJobExecutionSearchQueryResult
}