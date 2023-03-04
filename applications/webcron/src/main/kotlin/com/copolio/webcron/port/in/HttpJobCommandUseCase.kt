package com.copolio.webcron.port.`in`

interface HttpJobCommandUseCase {
    fun execute(httpJob: HttpJob)
}