package com.copolio.apiquartz.services

import com.copolio.apiquartz.config.RestJob
import com.copolio.apiquartz.dto.GetCronTriggerResponse
import com.copolio.apiquartz.dto.GetRestJobResponse
import com.copolio.apiquartz.dto.PostCronTriggerRequest
import com.copolio.apiquartz.dto.PostRestJobRequest
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Service

@Service
class RestSchedulerServiceImpl(
    private val scheduler: Scheduler
) : RestSchedulerService {
    override fun getGroups(): List<String> {
        return scheduler.jobGroupNames
    }

    override fun addJob(
        params: PostRestJobRequest
    ): GetRestJobResponse {
        val jobDataMap = JobDataMap()
        jobDataMap.put("url", params.url)
        jobDataMap.put("username", params.username)
        jobDataMap.put("password", params.password)
        jobDataMap.put("httpMethod", params.httpMethod)
        val jobDetail = JobBuilder.newJob()
            .ofType(RestJob::class.java)
            .storeDurably()
            .withIdentity(params.jobName, params.jobGroup)
            .withDescription(params.description)
            .setJobData(jobDataMap)
            .build()
        scheduler.addJob(jobDetail, false)
        return GetRestJobResponse(
            jobName = jobDetail.key.name,
            jobGroup = jobDetail.key.group,
            description = jobDetail.description,
            triggers = emptyList()
        )
    }

    override fun getJobs(jobGroup: String): List<JobKey> {
        val jobs = ArrayList<JobKey>()
        for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup))) {
            jobs.add(jobKey)
        }
        return jobs
    }

    override fun getJob(jobName: String, jobGroup: String): GetRestJobResponse {
        val jobDetail = scheduler.getJobDetail(JobKey(jobName, jobGroup))
        return GetRestJobResponse(
            jobGroup = jobDetail.key.group,
            jobName = jobDetail.key.name,
            description = jobDetail.description,
            triggers = getTriggers(jobName, jobGroup)
        )
    }

    override fun deleteJob(jobName: String, jobGroup: String): String {
        if (!scheduler.deleteJob(JobKey(jobName, jobGroup))) {
            throw NoSuchElementException("Requested job does not exists")
        }
        return "Deleted scheduler job (${jobGroup} : ${jobName})"
    }

    override fun addTrigger(params: PostCronTriggerRequest): String {
        val job = scheduler.getJobDetail(JobKey(params.jobName, params.jobGroup))
        val trigger = TriggerBuilder.newTrigger()
            .forJob(job)
            .withIdentity(params.triggerName, params.triggerGroup)
            .withDescription(params.description)
            .withSchedule(CronScheduleBuilder.cronSchedule(params.cronExpression))
            .build()
        return scheduler.scheduleJob(job, trigger).toString()
    }

    override fun getTriggers(jobName: String, jobGroup: String): List<GetCronTriggerResponse> {
        val result = ArrayList<GetCronTriggerResponse>()
        val triggersOfJob = scheduler.getTriggersOfJob(JobKey(jobName, jobGroup))
        triggersOfJob.forEach { t ->
            val cronTrigger = t as CronTrigger
            result.add(
                GetCronTriggerResponse(
                    triggerGroup = cronTrigger.key.group,
                    triggerName = cronTrigger.key.name,
                    description = cronTrigger.description,
                    cronExpression = cronTrigger.cronExpression
                )
            )
        }
        return result
    }

    override fun getTrigger(triggerName: String, triggerGroup: String): GetCronTriggerResponse {
        val trigger = scheduler.getTrigger(TriggerKey(triggerName, triggerGroup)) as CronTrigger
        return GetCronTriggerResponse(
            triggerGroup = trigger.key.group,
            triggerName = trigger.key.name,
            description = trigger.description,
            cronExpression = trigger.cronExpression
        )
    }

    override fun deleteTrigger(triggerName: String, triggerGroup: String): String {
        if (!scheduler.unscheduleJob(TriggerKey(triggerName, triggerGroup))) {
            throw NoSuchElementException("Requested trigger does not exists")
        }
        return "Trigger was deleted"
    }
}