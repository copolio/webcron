package com.copolio.webcron.port.`in`

import com.copolio.webcron.worker.entity.HttpJob

interface HttpJobPublishUseCase {
    fun publish(httpJob: HttpJob)
}