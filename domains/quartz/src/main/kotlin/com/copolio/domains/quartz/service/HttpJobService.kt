package com.copolio.domains.quartz.service

import com.copolio.domains.quartz.dto.HttpJobRequest
import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.domains.quartz.repository.HttpJobExecutionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

interface HttpJobService {
    fun getJobExecutions(jobName: String?, jobGroup: String?, pageable: Pageable): Page<HttpJobExecution>
    fun sendRequest(request: HttpJobRequest)
}

@Service
class HttpJobServiceImpl(
    private val httpJobExecutionRepository: HttpJobExecutionRepository
) : HttpJobService {
    override fun getJobExecutions(
        jobName: String?,
        jobGroup: String?,
        pageable: Pageable
    ): Page<HttpJobExecution> {
        if (jobGroup.isNullOrBlank())
            return httpJobExecutionRepository.findAll(pageable)
        else if (jobName.isNullOrBlank())
            return httpJobExecutionRepository.findAllByJobGroup(
                jobGroup = jobGroup,
                pageable = pageable
            )
        return httpJobExecutionRepository.findAllByJobGroupAndJobName(
            jobName = jobName,
            jobGroup = jobGroup,
            pageable = pageable
        )
    }

    @Transactional
    override fun sendRequest(request: HttpJobRequest) {
        val client = WebClient
            .create(if (request.url.startsWith("http")) request.url else "http://${request.url}")
        val startTime = LocalDateTime.now()
        val responseEntity = client
            .method(request.httpMethod)
            .uri(request.uri ?: "")
            .contentType(MediaType.APPLICATION_JSON)
            .headers { headers ->
                if (!request.apiKey.isNullOrBlank() && !request.apiToken.isNullOrBlank())
                    headers.setBasicAuth(request.apiKey, request.apiToken)
            }
            .bodyValue(request.body ?: "")
            .retrieve()
            .toEntity(String::class.java)
            .block()

        val httpJobExecution = HttpJobExecution(
            startTime = startTime,
            endTime = LocalDateTime.now(),
            statusCode = responseEntity?.statusCode ?: HttpStatus.INTERNAL_SERVER_ERROR,
            response = responseEntity?.body ?: "",
            jobName = request.jobName,
            jobGroup = request.jobGroup
        )
        httpJobExecutionRepository.save(httpJobExecution)
    }
}