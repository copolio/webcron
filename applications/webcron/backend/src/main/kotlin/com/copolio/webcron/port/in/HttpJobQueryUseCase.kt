package com.copolio.webcron.port.`in`

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HttpJobQueryUseCase {
    fun handle(httpJobExecutionQuery: HttpJobExecutionQuery, pageable: Pageable): Page<HttpJobExecutionQueryResult>
}