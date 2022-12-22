package com.copolio.webcron.port.`in`

interface SchedulerQueryUseCase {
    fun getGroups(): List<HttpJobGroupQueryResult>
    fun getJobs(jobGroup: String): List<HttpJobQueryResult>
    fun getJob(jobName: String, jobGroup: String): HttpJobQueryResult
    fun getTrigger(jobName: String, jobGroup: String): String
}