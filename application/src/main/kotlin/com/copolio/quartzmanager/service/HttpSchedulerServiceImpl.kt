package com.copolio.quartzmanager.service

import com.copolio.quartzmanager.config.HttpJob
import com.copolio.quartzmanager.domain.HttpJobExecution
import com.copolio.quartzmanager.domain.HttpJobExecutionRepository
import com.copolio.quartzmanager.dto.GetHttpJobResponse
import com.copolio.quartzmanager.dto.GetJobGroupResponse
import com.copolio.quartzmanager.dto.PostHttpJobRequest
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class HttpSchedulerServiceImpl(
    private val scheduler: Scheduler,
    private val httpJobExecutionRepository: HttpJobExecutionRepository
) : HttpSchedulerService {
    override fun getGroups(): List<GetJobGroupResponse> {
        return scheduler.jobGroupNames.map { groupName -> GetJobGroupResponse(groupName) }
    }

    override fun addJob(
        params: PostHttpJobRequest
    ): GetHttpJobResponse {
        val jobDataMap = JobDataMap()
        jobDataMap.put("jobName", params.jobName)
        jobDataMap.put("jobGroup", params.jobGroup)
        jobDataMap.put("url", params.url)
        jobDataMap.put("username", params.username)
        jobDataMap.put("password", params.password)
        jobDataMap.put("requestBody", params.requestBody)
        jobDataMap.put("httpMethod", params.httpMethod)
        val jobDetail = JobBuilder.newJob()
            .ofType(HttpJob::class.java)
            .storeDurably()
            .withIdentity(params.jobName, params.jobGroup)
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
        return GetHttpJobResponse(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = params.cronExpression,
            url = params.url
        )
    }

    override fun getJobs(jobGroup: String): List<GetHttpJobResponse> {
        val jobs = ArrayList<GetHttpJobResponse>()
        for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup))) {
            jobs.add(getJob(jobKey.name, jobKey.group))
        }
        return jobs
    }

    override fun getJob(jobName: String, jobGroup: String): GetHttpJobResponse {
        val jobDetail = scheduler.getJobDetail(JobKey(jobName, jobGroup))
        return GetHttpJobResponse(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = getTrigger(jobName, jobGroup),
            url = jobDetail.jobDataMap["url"] as String
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

    override fun getJobExecutions(
        jobName: String,
        jobGroup: String,
        pageable: Pageable
    ): Page<HttpJobExecution> {
        return httpJobExecutionRepository.findAllByJobGroupAndJobName(
            jobName = jobName,
            jobGroup = jobGroup,
            pageable = pageable
        )
    }
}