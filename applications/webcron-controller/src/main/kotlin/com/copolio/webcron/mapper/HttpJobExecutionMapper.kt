package com.copolio.webcron.mapper

import com.copolio.webcron.worker.entity.HttpJobExecution
import com.copolio.clients.webcronclient.dto.query.HttpJobExecutionInfo

interface HttpJobExecutionMapper {
    fun convert(httpJobExecution: HttpJobExecution): HttpJobExecutionInfo
}