package com.copolio.apiquartz.services

import com.copolio.apiquartz.dto.GetCronTriggerResponse
import com.copolio.apiquartz.dto.GetRestJobResponse
import com.copolio.apiquartz.dto.PostCronTriggerRequest
import com.copolio.apiquartz.dto.PostRestJobRequest
import org.quartz.JobKey

interface RestSchedulerService {
    fun addJob(params: PostRestJobRequest): GetRestJobResponse
    fun getGroups(): List<String>
    fun getJobs(jobGroup: String): List<JobKey>
    fun getJob(jobName: String, jobGroup: String): GetRestJobResponse
    fun deleteJob(jobName: String, jobGroup: String): String
    fun addTrigger(params: PostCronTriggerRequest): GetCronTriggerResponse
    fun getTriggers(jobName: String, jobGroup: String): List<GetCronTriggerResponse>
    fun deleteTrigger(triggerName: String): String
}