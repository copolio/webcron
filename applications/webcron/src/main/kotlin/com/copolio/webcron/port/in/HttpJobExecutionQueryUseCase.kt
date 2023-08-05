package com.copolio.webcron.port.`in`

import com.copolio.clients.webcronclient.dto.query.SearchHttpJobExecution
import com.copolio.clients.webcronclient.dto.query.HttpJobExecutionInfo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HttpJobExecutionQueryUseCase {
    fun handle(
        searchHttpJobExecution: SearchHttpJobExecution,
        pageable: Pageable
    ): Page<HttpJobExecutionInfo>
}