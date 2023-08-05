package com.copolio.webcron.port.`in`

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import java.time.LocalDateTime

data class HttpJobExecutionSearchQuery(
    val jobGroup: String?,
    val jobName: String?,
)

data class HttpJobExecutionSearchQueryResult(
    val jobGroup: String,
    val jobName: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val startTime: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val endTime: LocalDateTime,
    val statusCode: HttpStatusCode,
    val response: String
)