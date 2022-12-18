package com.copolio.webcron.service

import com.copolio.webcron.config.HttpJob
import com.copolio.webcron.dto.HttpJobInfoDto
import com.copolio.webcron.dto.HttpJobGroupDto
import com.copolio.webcron.dto.CreateHttpJobCommand
import com.copolio.domains.quartz.dto.HttpJobRequest
import com.copolio.domains.quartz.repository.HttpJobExecutionRepository
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Service

@Service
class HttpSchedulerServiceImpl(
    private val scheduler: Scheduler,
    private val httpJobExecutionRepository: HttpJobExecutionRepository
) : HttpSchedulerService {
    override fun getGroups(): List<HttpJobGroupDto> {
        return scheduler.jobGroupNames.map { groupName -> HttpJobGroupDto(groupName) }
    }

    override fun addJob(
        params: CreateHttpJobCommand
    ): HttpJobInfoDto {
        val jobDataMap = JobDataMap()
        jobDataMap.put("httpJobRequest", params.httpJobRequest)
        val jobDetail = JobBuilder.newJob()
            .ofType(HttpJob::class.java)
            .storeDurably()
            .withIdentity(params.httpJobRequest.jobName, params.httpJobRequest.jobGroup)
            .withDescription(params.description)
            .setJobData(jobDataMap)
            .build()
        val trigger = TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(jobDetail.key.name, jobDetail.key.group)
            .withDescription(jobDetail.description)
            .withSchedule(CronScheduleBuilder.cronSchedule(params.cronExpression))
            .build()
        scheduler.scheduleJob(jobDetail, trigger)
        return HttpJobInfoDto(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = params.cronExpression,
            url = params.httpJobRequest.url,
        )
    }

    override fun getJobs(jobGroup: String): List<HttpJobInfoDto> {
        val jobs = ArrayList<HttpJobInfoDto>()
        for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup))) {
            jobs.add(getJob(jobKey.name, jobKey.group))
        }
        return jobs
    }

    override fun getJob(jobName: String, jobGroup: String): HttpJobInfoDto {
        val jobDetail = scheduler.getJobDetail(JobKey(jobName, jobGroup))
        val httpJobRequest = jobDetail.jobDataMap["httpJobRequest"] as HttpJobRequest
        return HttpJobInfoDto(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = getTrigger(jobName, jobGroup),
            url = httpJobRequest.url
        )
    }

    override fun deleteJob(jobName: String, jobGroup: String): String {
        if (!scheduler.deleteJob(JobKey(jobName, jobGroup))) {
            throw NoSuchElementException("Requested job does not exists")
        }
        return "Deleted scheduler job (${jobGroup} : ${jobName})"
    }

    override fun getTrigger(jobName: String, jobGroup: String): String {
        val triggersOfJob = scheduler.getTriggersOfJob(JobKey(jobName, jobGroup))
        if (triggersOfJob.isEmpty())
            throw NoSuchElementException("Cron Trigger does not exists for the job(${jobGroup} : ${jobName})")
        return (triggersOfJob[0] as CronTrigger).cronExpression
    }
}