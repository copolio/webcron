package com.copolio.webcron.service

import com.copolio.webcron.dto.HttpJobInfoDto
import com.copolio.webcron.dto.HttpJobGroupDto
import com.copolio.webcron.dto.CreateHttpJobCommand

interface HttpSchedulerService {
    fun addJob(params: CreateHttpJobCommand): HttpJobInfoDto
    fun getGroups(): List<HttpJobGroupDto>
    fun getJobs(jobGroup: String): List<HttpJobInfoDto>
    fun getJob(jobName: String, jobGroup: String): HttpJobInfoDto
    fun deleteJob(jobName: String, jobGroup: String): String
    fun getTrigger(jobName: String, jobGroup: String): String
}