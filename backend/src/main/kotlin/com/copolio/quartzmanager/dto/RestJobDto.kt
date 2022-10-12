package com.copolio.quartzmanager.dto

import org.springframework.http.HttpMethod

data class PostRestJobRequest(
    val jobGroup: String?,
    val jobName: String,
    val description: String,
    val url: String,
    val username: String?,
    val password: String?,
    val httpMethod: HttpMethod,
    val cronExpression: String,
)

data class PatchRestJobRequest(
    val jobGroup: String,
    val jobName: String,
    val description: String
)

data class DeleteRestJobRequest(
    val jobGroup: String,
    val jobName: String
)

data class GetRestJobResponse(
    val jobGroup: String,
    val jobName: String,
    val description: String,
    val cronExpression: String,
)