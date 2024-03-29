package com.copolio.webcron.worker.entity

import org.springframework.http.HttpMethod
import java.io.Serializable

data class HttpJob(
    val uri: String,
    val body: String?,
    val apiKey: String?,
    val apiToken: String?,
    val httpMethod: HttpMethod = HttpMethod.GET,

    val jobName: String,
    val jobGroup: String,
) : Serializable
