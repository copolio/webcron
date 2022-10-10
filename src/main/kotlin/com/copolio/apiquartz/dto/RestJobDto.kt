package com.copolio.apiquartz.dto

import org.springframework.http.HttpMethod

data class PostRestJobRequest(
    val jobGroup: String?,
    val jobName: String,
    val description: String,
    val url: String,
    val username: String?,
    val password: String?,
    val httpMethod: HttpMethod
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
    val triggers: List<GetCronTriggerResponse>,
)