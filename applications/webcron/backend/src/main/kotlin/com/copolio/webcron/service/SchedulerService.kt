package com.copolio.webcron.service

import com.copolio.webcron.config.QuartzConfig
import com.copolio.webcron.model.HttpJob
import com.copolio.webcron.port.`in`.*
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SchedulerService(
    private val scheduler: Scheduler
) : SchedulerCommandUseCase, SchedulerQueryUseCase {
    override fun getGroups(): List<HttpJobGroupQueryResult> {
        return scheduler.jobGroupNames.map { groupName -> HttpJobGroupQueryResult(groupName) }
    }

    override fun handle(
        createHttpJob: CreateHttpJob
    ): CreateHttpJobResult {
        val jobDataMap = JobDataMap()
        jobDataMap.put("httpJob", createHttpJob.httpJob)
        val jobDetail = JobBuilder.newJob()
            .ofType(QuartzConfig::class.java)
            .storeDurably()
            .withIdentity(createHttpJob.httpJob.jobName, createHttpJob.httpJob.jobGroup)
            .withDescription(createHttpJob.description)
            .setJobData(jobDataMap)
            .build()
        val trigger = TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(jobDetail.key.name, jobDetail.key.group)
            .withDescription(jobDetail.description)
            .withSchedule(CronScheduleBuilder.cronSchedule(createHttpJob.cronExpression))
            .build()
        scheduler.scheduleJob(jobDetail, trigger)
        return CreateHttpJobResult(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = createHttpJob.cronExpression,
            url = createHttpJob.httpJob.url,
        )
    }

    override fun handle(deleteHttpJob: DeleteHttpJob): String {
        if (!scheduler.deleteJob(JobKey(deleteHttpJob.jobName, deleteHttpJob.jobGroup))) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Requested job does not exists")
        }
        return "Deleted scheduler job (${deleteHttpJob.jobGroup} : ${deleteHttpJob.jobName})"
    }

    override fun getJobs(jobGroup: String): List<HttpJobQueryResult> {
        val jobs = ArrayList<HttpJobQueryResult>()
        for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup))) {
            jobs.add(getJob(jobKey.name, jobKey.group))
        }
        return jobs
    }

    override fun getJob(jobName: String, jobGroup: String): HttpJobQueryResult {
        val jobDetail = scheduler.getJobDetail(JobKey(jobName, jobGroup))
        val httpJob = jobDetail.jobDataMap["httpJob"] as HttpJob
        return HttpJobQueryResult(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = getTrigger(jobName, jobGroup),
            url = httpJob.url
        )
    }

    override fun getTrigger(jobName: String, jobGroup: String): String {
        val triggersOfJob = scheduler.getTriggersOfJob(JobKey(jobName, jobGroup))
        if (triggersOfJob.isEmpty())
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cron Trigger does not exists for the job(${jobGroup} : ${jobName})"
            )
        return (triggersOfJob[0] as CronTrigger).cronExpression
    }
}