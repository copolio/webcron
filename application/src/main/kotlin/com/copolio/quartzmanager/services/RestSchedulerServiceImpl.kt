package com.copolio.quartzmanager.services

import com.copolio.quartzmanager.config.RestJob
import com.copolio.quartzmanager.dto.GetJobGroupResponse
import com.copolio.quartzmanager.dto.GetRestJobResponse
import com.copolio.quartzmanager.dto.PostRestJobRequest
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Service

@Service
class RestSchedulerServiceImpl(
    private val scheduler: Scheduler
) : RestSchedulerService {
    override fun getGroups(): List<GetJobGroupResponse> {
        return scheduler.jobGroupNames.map { groupName -> GetJobGroupResponse(groupName) }
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
        val trigger = TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(jobDetail.key.group, jobDetail.key.name)
            .withDescription(jobDetail.description)
            .withSchedule(CronScheduleBuilder.cronSchedule(params.cronExpression))
            .build()
        scheduler.scheduleJob(jobDetail, trigger)
        return GetRestJobResponse(
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = params.cronExpression
        )
    }

    override fun getJobs(jobGroup: String): List<GetRestJobResponse> {
        val jobs = ArrayList<GetRestJobResponse>()
        for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup))) {
            jobs.add(getJob(jobKey.name, jobKey.group))
        }
        return jobs
    }

    override fun getJob(jobName: String, jobGroup: String): GetRestJobResponse {
        val jobDetail = scheduler.getJobDetail(JobKey(jobName, jobGroup))
        return GetRestJobResponse(
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = getTrigger(jobName, jobGroup)
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