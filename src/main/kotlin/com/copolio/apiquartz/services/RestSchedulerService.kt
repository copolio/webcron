package com.copolio.apiquartz.services

import com.copolio.apiquartz.dto.GetCronTriggerResponse
import com.copolio.apiquartz.dto.GetRestJobResponse
import com.copolio.apiquartz.dto.PostCronTriggerRequest
import com.copolio.apiquartz.dto.PostRestJobRequest
import org.quartz.JobKey

interface RestSchedulerService {
    fun getGroups(): List<String>
    fun addJob(params: PostRestJobRequest): GetRestJobResponse

    /**
     * TODO
     */
    fun getJobs(jobGroup: String): List<JobKey>
    fun getJob(jobName: String, jobGroup: String): GetRestJobResponse

    //    fun updateJob(params)
    fun deleteJob(jobName: String, jobGroup: String): String
    fun addTrigger(params: PostCronTriggerRequest): String
    fun getTriggers(jobName: String, jobGroup: String): List<GetCronTriggerResponse>
    fun getTrigger(triggerName: String, triggerGroup: String): GetCronTriggerResponse
    fun deleteTrigger(triggerName: String, triggerGroup: String): String
}