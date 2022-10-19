package com.copolio.quartzmanager.dto

import org.springframework.http.HttpMethod

data class GetJobGroupResponse(
    val groupName: String
)

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

data class GetRestJobResponse(
    val jobGroup: String,
    val jobName: String,
    val description: String,
    val cronExpression: String,
)