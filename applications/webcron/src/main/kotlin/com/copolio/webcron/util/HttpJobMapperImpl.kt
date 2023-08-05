package com.copolio.webcron.util

import com.copolio.clients.webcronclient.dto.command.CreateHttpJob
import com.copolio.webcron.domain.HttpJob
import org.springframework.stereotype.Component

@Component
class HttpJobMapperImpl: HttpJobMapper {
    override fun mapTo(request: CreateHttpJob): HttpJob {
        return HttpJob(
            uri = request.url,
            body = request.body,
            apiKey = request.apiKey,
            apiToken = request.apiToken,
            httpMethod = request.httpMethod,
            jobGroup = request.jobGroup,
            jobName = request.jobName,
        )
    }
}