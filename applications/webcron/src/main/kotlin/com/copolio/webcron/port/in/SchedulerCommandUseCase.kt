package com.copolio.webcron.port.`in`

interface SchedulerCommandUseCase {
    fun handle(createHttpJob: CreateHttpJob): CreateHttpJobResult
    fun handle(deleteHttpJob: DeleteHttpJob): DeleteHttpJobResult
}