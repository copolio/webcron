package com.copolio.webcron.port.`in`

import com.copolio.webcron.model.HttpJob

interface HttpJobCommandUseCase {
    fun execute(httpJob: HttpJob)
}