package com.copolio.webcron.mapper

import com.copolio.domains.quartz.model.HttpJobExecution
import com.copolio.webcron.port.`in`.HttpJobExecutionQueryResult

interface HttpJobExecutionMapper {
    fun convert(httpJobExecution: HttpJobExecution): HttpJobExecutionQueryResult
}