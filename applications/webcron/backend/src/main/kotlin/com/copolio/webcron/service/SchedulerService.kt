package com.copolio.webcron.service

import com.copolio.webcron.config.QuartzConfig
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
    override fun getGroups(): List<SchedulerJobGroupQueryResult> {
        return scheduler.jobGroupNames.map { groupName -> SchedulerJobGroupQueryResult(groupName) }
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

    override fun handle(deleteHttpJob: DeleteHttpJob): DeleteHttpJobResult {
        if (!scheduler.deleteJob(JobKey(deleteHttpJob.jobName, deleteHttpJob.jobGroup))) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested job does not exists")
        }
        return DeleteHttpJobResult(message = "Deleted scheduler job (${deleteHttpJob.jobGroup} : ${deleteHttpJob.jobName})")
    }

    override fun handle(schedulerJobByGroupQuery: SchedulerJobByGroupQuery): List<SchedulerJobQueryResult> {
        val jobs = ArrayList<SchedulerJobQueryResult>()
        for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(schedulerJobByGroupQuery.jobGroup))) {
            jobs.add(
                handle(
                    SchedulerJobDetailQuery(
                        jobName = jobKey.name, jobGroup = jobKey.group
                    )
                )
            )
        }
        return jobs
    }

    override fun handle(schedulerJobDetailQuery: SchedulerJobDetailQuery): SchedulerJobQueryResult {
        val jobDetail = scheduler.getJobDetail(
            JobKey(schedulerJobDetailQuery.jobName, schedulerJobDetailQuery.jobGroup)
        )
        val httpJob = jobDetail.jobDataMap["httpJob"] as HttpJob
        return SchedulerJobQueryResult(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = handle(
                SchedulerTriggerQuery(
                    schedulerJobDetailQuery.jobName,
                    schedulerJobDetailQuery.jobGroup
                )
            ),
            url = httpJob.url
        )
    }

    override fun handle(schedulerTriggerQuery: SchedulerTriggerQuery): String {
        val triggersOfJob =
            scheduler.getTriggersOfJob(JobKey(schedulerTriggerQuery.jobName, schedulerTriggerQuery.jobGroup))
        if (triggersOfJob.isEmpty())
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cron Trigger does not exists for the job(${schedulerTriggerQuery.jobGroup} : ${schedulerTriggerQuery.jobName})"
            )
        return (triggersOfJob[0] as CronTrigger).cronExpression
    }
}