package com.copolio.webcron.port.`in`

import com.copolio.clients.webcronclient.dto.query.SearchHttpJobLog
import com.copolio.clients.webcronclient.dto.query.HttpJobExecutionInfo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HttpJobQueryUseCase {
    fun handle(
        searchHttpJobLog: SearchHttpJobLog,
        pageable: Pageable
    ): Page<HttpJobExecutionInfo>
}