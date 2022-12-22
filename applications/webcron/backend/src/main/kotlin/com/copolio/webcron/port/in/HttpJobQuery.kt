package com.copolio.webcron.port.`in`

data class HttpJobExecutionQuery(
    val jobGroup: String?,
    val jobName: String?,
)

data class HttpJobGroupQueryResult(
    val name: String
)

data class HttpJobQueryResult(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)