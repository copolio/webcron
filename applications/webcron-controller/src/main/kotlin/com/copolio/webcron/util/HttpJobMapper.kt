package com.copolio.webcron.util

import com.copolio.clients.webcronclient.dto.command.CreateHttpJob
import com.copolio.webcron.worker.entity.HttpJob

interface HttpJobMapper {
    fun mapTo(request: CreateHttpJob): HttpJob
}