package com.copolio.clients.webcronclient.dto.command

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class CreateHttpJobExecution(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val httpStatus: HttpStatus,
    val response: String,
    val jobName: String,
    val jobGroup: String
)