package com.copolio.webcron.domain

import com.copolio.clients.webcronclient.dto.command.CreateHttpJobExecution
import com.copolio.webcron.port.`in`.HttpJobPublishUseCase
import com.copolio.webcron.port.out.HttpJobSavePort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

@Service
class HttpJobService(
    private val httpJobSavePort: HttpJobSavePort
) : HttpJobPublishUseCase {
    @Transactional
    override fun publish(httpJob: HttpJob) {
        val client = WebClient.create(
            if (httpJob.uri.startsWith("http"))
                httpJob.uri
            else
                "http://${httpJob.uri}"
        )
        val startTime = LocalDateTime.now()
        try {
            val responseEntity = client
                .method(httpJob.httpMethod)
                .uri(httpJob.uri)
                .contentType(MediaType.APPLICATION_JSON)
                .headers { headers ->
                    if (!httpJob.apiKey.isNullOrBlank() && !httpJob.apiToken.isNullOrBlank())
                        headers.setBasicAuth(httpJob.apiKey, httpJob.apiToken)
                }
                .bodyValue(httpJob.body ?: "")
                .retrieve()
                .toEntity(String::class.java)
                .block()
            httpJobSavePort.handle(
                CreateHttpJobExecution(
                    startTime = startTime,
                    endTime = LocalDateTime.now(),
                    httpStatus = HttpStatus.valueOf(responseEntity!!.statusCode.toString()),
                    response = responseEntity.body ?: "",
                    jobGroup = httpJob.jobGroup,
                    jobName = httpJob.jobName
                )
            )
        } catch (e: Exception) {
            httpJobSavePort.handle(
                CreateHttpJobExecution(
                    startTime = startTime,
                    endTime = LocalDateTime.now(),
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
                    response = e.message ?: "Execution failed",
                    jobGroup = httpJob.jobGroup,
                    jobName = httpJob.jobName
                )
            )
        }
    }
}