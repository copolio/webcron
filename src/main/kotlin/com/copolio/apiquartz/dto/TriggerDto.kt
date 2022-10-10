package com.copolio.apiquartz.dto

data class PostCronTriggerRequest(
    val jobGroup: String,
    val jobName: String,
    val triggerName: String,
    val description: String,
    val cronExpression: String
)

data class GetCronTriggerResponse(
    val jobGroup: String,
    val jobName: String,
    val description: String,
    val cronExpression: String
)