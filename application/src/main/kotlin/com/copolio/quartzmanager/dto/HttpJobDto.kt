package com.copolio.quartzmanager.dto

import org.springframework.http.HttpMethod

data class GetJobGroupResponse(
    val name: String
)

data class PostHttpJobRequest(
    val jobGroup: String?,
    val jobName: String,
    val description: String,
    val url: String,
    val username: String?,
    val password: String?,
    val requestBody: String?,
    val httpMethod: HttpMethod,
    val cronExpression: String,
)

data class GetHttpJobResponse(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)