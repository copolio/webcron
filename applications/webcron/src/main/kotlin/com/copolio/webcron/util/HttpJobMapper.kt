package com.copolio.webcron.util

import com.copolio.clients.webcronclient.dto.command.CreateHttpJob
import com.copolio.webcron.domain.HttpJob

interface HttpJobMapper {
    fun mapTo(request: CreateHttpJob): HttpJob
}