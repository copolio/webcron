package com.copolio.webcron.port.`in`

import com.copolio.webcron.domain.HttpJob

interface HttpJobPublishUseCase {
    fun publish(httpJob: HttpJob)
}