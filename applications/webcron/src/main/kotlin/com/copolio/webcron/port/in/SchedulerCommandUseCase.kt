package com.copolio.webcron.port.`in`

import com.copolio.webcron.domain.CreateHttpJob
import com.copolio.webcron.domain.CreateHttpJobResult
import com.copolio.webcron.domain.DeleteHttpJob
import com.copolio.webcron.domain.DeleteHttpJobResult

interface SchedulerCommandUseCase {
    fun handle(createHttpJob: CreateHttpJob): CreateHttpJobResult
    fun handle(deleteHttpJob: DeleteHttpJob): DeleteHttpJobResult
}