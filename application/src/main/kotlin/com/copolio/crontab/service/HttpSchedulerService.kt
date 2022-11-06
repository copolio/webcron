package com.copolio.crontab.service

import com.copolio.crontab.dto.GetHttpJobResponse
import com.copolio.crontab.dto.GetJobGroupResponse
import com.copolio.crontab.dto.PostHttpJobRequest

interface HttpSchedulerService {
    fun addJob(params: PostHttpJobRequest): GetHttpJobResponse
    fun getGroups(): List<GetJobGroupResponse>
    fun getJobs(jobGroup: String): List<GetHttpJobResponse>
    fun getJob(jobName: String, jobGroup: String): GetHttpJobResponse
    fun deleteJob(jobName: String, jobGroup: String): String
    fun getTrigger(jobName: String, jobGroup: String): String
}