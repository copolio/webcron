package com.copolio.domains.quartz.dto

import org.springframework.http.HttpMethod

data class HttpJobRequest(
    val url: String,
    val uri: String?,
    val body: String?,
    val apiKey: String?,
    val apiPass: String?,
    val httpMethod: HttpMethod = HttpMethod.GET,

    val jobName: String,
    val jobGroup: String,
)
