package com.copolio.crontab.service

import com.copolio.crontab.domain.HttpJobExecution
import com.copolio.crontab.dto.GetHttpJobResponse
import com.copolio.crontab.dto.GetJobGroupResponse
import com.copolio.crontab.dto.PostHttpJobRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HttpSchedulerService {
    fun addJob(params: PostHttpJobRequest): GetHttpJobResponse
    fun getGroups(): List<GetJobGroupResponse>
    fun getJobs(jobGroup: String): List<GetHttpJobResponse>
    fun getJob(jobName: String, jobGroup: String): GetHttpJobResponse
    fun deleteJob(jobName: String, jobGroup: String): String
    fun getTrigger(jobName: String, jobGroup: String): String
    fun getJobExecutions(jobName: String, jobGroup: String, pageable: Pageable): Page<HttpJobExecution>
}