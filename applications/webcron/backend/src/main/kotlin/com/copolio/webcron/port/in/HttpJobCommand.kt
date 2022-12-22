package com.copolio.webcron.port.`in`

import com.copolio.webcron.model.HttpJob

data class CreateHttpJob(
    val httpJob: HttpJob,
    val description: String,
    val cronExpression: String,
)

data class CreateHttpJobResult(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)

data class DeleteHttpJob(
    val jobName: String,
    val jobGroup: String
)