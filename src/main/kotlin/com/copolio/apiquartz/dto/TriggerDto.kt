package com.copolio.apiquartz.dto

data class PostCronTriggerRequest(
    val jobGroup: String,
    val jobName: String,
    val triggerGroup: String,
    val triggerName: String,
    val description: String,
    val cronExpression: String
)

data class GetCronTriggerResponse(
    val triggerGroup: String,
    val triggerName: String,
    val description: String,
    val cronExpression: String
)