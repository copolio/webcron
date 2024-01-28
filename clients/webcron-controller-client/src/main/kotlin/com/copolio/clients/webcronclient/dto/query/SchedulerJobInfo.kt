package com.copolio.clients.webcronclient.dto.query

data class SchedulerJobInfo(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)