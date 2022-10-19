package com.copolio.quartzmanager.services

import com.copolio.quartzmanager.dto.GetJobGroupResponse
import com.copolio.quartzmanager.dto.GetRestJobResponse
import com.copolio.quartzmanager.dto.PostRestJobRequest

interface RestSchedulerService {
    fun addJob(params: PostRestJobRequest): GetRestJobResponse
    fun getGroups(): List<GetJobGroupResponse>
    fun getJobs(jobGroup: String): List<GetRestJobResponse>
    fun getJob(jobName: String, jobGroup: String): GetRestJobResponse
    fun deleteJob(jobName: String, jobGroup: String): String
    fun getTrigger(jobName: String, jobGroup: String): String
}