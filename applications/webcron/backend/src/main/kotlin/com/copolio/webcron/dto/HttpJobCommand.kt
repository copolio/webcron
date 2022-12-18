package com.copolio.webcron.dto

import com.copolio.domains.quartz.dto.HttpJobRequest

data class CreateHttpJobCommand(
    val httpJobRequest: HttpJobRequest,
    val description: String,
    val cronExpression: String,
)

