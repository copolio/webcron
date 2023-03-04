package com.copolio.webcron.port.`in`

data class SchedulerJobGroupQueryResult(
    val name: String
)

data class SchedulerJobByGroupQuery(
    val jobGroup: String
)

data class SchedulerJobDetailQuery(
    val jobGroup: String,
    val jobName: String
)

data class SchedulerJobQueryResult(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)

data class SchedulerTriggerQuery(
    val jobName: String, val jobGroup: String
)