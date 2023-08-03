package com.copolio.webcron.domain

import com.copolio.webcron.port.`in`.CreateHttpJobExecution
import com.copolio.webcron.port.`in`.HttpJobPublishUseCase
import com.copolio.webcron.port.out.HttpJobExecutionSavePort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

@Service
class HttpJobService(
    private val httpJobExecutionSavePort: HttpJobExecutionSavePort
) : HttpJobPublishUseCase {
    @Transactional
    override fun publish(httpJob: HttpJob) {
        val client = WebClient.create(
            if (httpJob.url.startsWith("http"))
                httpJob.url
            else
                "http://${httpJob.url}"
        )
        val startTime = LocalDateTime.now()
        try {
            val responseEntity = client
                .method(httpJob.httpMethod)
                .uri(httpJob.uri ?: "")
                .contentType(MediaType.APPLICATION_JSON)
                .headers { headers ->
                    if (!httpJob.apiKey.isNullOrBlank() && !httpJob.apiToken.isNullOrBlank())
                        headers.setBasicAuth(httpJob.apiKey, httpJob.apiToken)
                }
                .bodyValue(httpJob.body ?: "")
                .retrieve()
                .toEntity(String::class.java)
                .block()
            httpJobExecutionSavePort.handle(
                CreateHttpJobExecution(
                    startTime = startTime,
                    endTime = LocalDateTime.now(),
                    httpStatus = responseEntity!!.statusCode,
                    response = responseEntity.body ?: "",
                    jobGroup = httpJob.jobGroup,
                    jobName = httpJob.jobName
                )
            )
        } catch (e: Exception) {
            httpJobExecutionSavePort.handle(
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