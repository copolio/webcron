package com.copolio.webcron.port.`in`

import org.springframework.http.HttpMethod
import java.io.Serializable

data class HttpJob(
    val url: String,
    val uri: String?,
    val body: String?,
    val apiKey: String?,
    val apiToken: String?,
    val httpMethod: HttpMethod = HttpMethod.GET,

    val jobName: String,
    val jobGroup: String,
) : Serializable
