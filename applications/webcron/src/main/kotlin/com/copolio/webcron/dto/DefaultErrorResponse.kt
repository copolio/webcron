package com.copolio.webcron.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class DefaultErrorResponse(
    val timestamp: LocalDateTime,
    val status: HttpStatus,
    val error: String,
    val exception: String,
    val message: String,
    val path: String
)