package com.copolio.clients.webcronclient.dto.command

import org.springframework.http.HttpMethod

data class CreateHttpJob(
    val url: String,
    val body: String?,
    val apiKey: String?,
    val apiToken: String?,
    val httpMethod: HttpMethod = HttpMethod.GET,
    val jobName: String,
    val jobGroup: String,
    val description: String,
    val cronExpression: String,
)