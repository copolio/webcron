package com.copolio.webcron.mapper

import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.clients.webcronclient.dto.query.HttpJobExecutionInfo

interface HttpJobExecutionMapper {
    fun convert(httpJobExecution: HttpJobExecution): HttpJobExecutionInfo
}