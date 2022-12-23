package com.copolio.webcron.port.`in`

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HttpJobExecutionQueryUseCase {
    fun handle(
        httpJobExecutionSearchQuery: HttpJobExecutionSearchQuery,
        pageable: Pageable
    ): Page<HttpJobExecutionSearchQueryResult>
}