package com.copolio.apiquartz.dto

data class PostCronJobRequest(
    val identity: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)

data class GetCronJobResponse(
    val groupName: String,
    val jobName: String,
    val triggers: List<CronTriggerDto>,
)

data class CronTriggerDto(
    val identity: String,
    val description: String,
    val cronExpression: String,
)