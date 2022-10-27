package com.copolio.quartzmanager.service

import com.copolio.quartzmanager.dto.GetHttpJobResponse
import com.copolio.quartzmanager.dto.GetJobGroupResponse
import com.copolio.quartzmanager.dto.PostHttpJobRequest

interface HttpSchedulerService {
    fun addJob(params: PostHttpJobRequest): GetHttpJobResponse
    fun getGroups(): List<GetJobGroupResponse>
    fun getJobs(jobGroup: String): List<GetHttpJobResponse>
    fun getJob(jobName: String, jobGroup: String): GetHttpJobResponse
    fun deleteJob(jobName: String, jobGroup: String): String
    fun getTrigger(jobName: String, jobGroup: String): String
}