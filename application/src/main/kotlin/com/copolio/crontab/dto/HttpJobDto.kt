package com.copolio.crontab.dto

import com.copolio.domains.quartz.dto.HttpJobRequest

data class GetJobGroupResponse(
    val name: String
)

data class PostHttpJobRequest(
    val httpJobRequest: HttpJobRequest,
    val description: String,
    val cronExpression: String,
)

data class GetHttpJobResponse(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)