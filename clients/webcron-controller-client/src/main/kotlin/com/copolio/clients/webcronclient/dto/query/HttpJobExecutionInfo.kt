package com.copolio.clients.webcronclient.dto.query

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatusCode
import java.time.LocalDateTime

data class HttpJobExecutionInfo(
    val jobGroup: String,
    val jobName: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val startTime: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val endTime: LocalDateTime,
    val statusCode: HttpStatusCode,
    val response: String
)