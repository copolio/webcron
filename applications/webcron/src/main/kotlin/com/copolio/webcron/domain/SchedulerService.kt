package com.copolio.webcron.domain

import com.copolio.clients.webcronclient.dto.command.CreateHttpJob
import com.copolio.clients.webcronclient.dto.command.CreateHttpJobResult
import com.copolio.clients.webcronclient.dto.command.DeleteHttpJob
import com.copolio.clients.webcronclient.dto.command.DeleteHttpJobResult
import com.copolio.clients.webcronclient.dto.query.*
import com.copolio.webcron.config.QuartzConfig
import com.copolio.webcron.port.`in`.*
import com.copolio.webcron.util.HttpJobMapper
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SchedulerService(
    private val scheduler: Scheduler,
    private val httpJobMapper: HttpJobMapper,
) : SchedulerUseCase, SchedulerQueryUseCase {
    override fun getGroups(): List<SchedulerJobGroupInfo> {
        return scheduler.jobGroupNames.map { groupName -> SchedulerJobGroupInfo(groupName) }
    }

    override fun handle(
        createHttpJob: CreateHttpJob
    ): CreateHttpJobResult {
        val jobDataMap = JobDataMap()
        jobDataMap.put("httpJob", httpJobMapper.mapTo(createHttpJob))
        val jobDetail = JobBuilder.newJob()
            .ofType(QuartzConfig::class.java)
            .storeDurably()
            .withIdentity(createHttpJob.jobName, createHttpJob.jobGroup)
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
            url = createHttpJob.url,
        )
    }

    override fun handle(deleteHttpJob: DeleteHttpJob): DeleteHttpJobResult {
        if (!scheduler.deleteJob(JobKey(deleteHttpJob.jobName, deleteHttpJob.jobGroup))) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested job does not exists")
        }
        return DeleteHttpJobResult(message = "Deleted scheduler job (${deleteHttpJob.jobGroup} : ${deleteHttpJob.jobName})")
    }

    override fun handle(searchSchedulerJobs: SearchSchedulerJobs): List<SchedulerJobInfo> {
        val jobs = ArrayList<SchedulerJobInfo>()
        for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(searchSchedulerJobs.jobGroup))) {
            jobs.add(
                handle(
                    SearchSchedulerJobInfo(
                        jobName = jobKey.name, jobGroup = jobKey.group
                    )
                )
            )
        }
        return jobs
    }

    override fun handle(searchSchedulerJobInfo: SearchSchedulerJobInfo): SchedulerJobInfo {
        val jobDetail = scheduler.getJobDetail(
            JobKey(searchSchedulerJobInfo.jobName, searchSchedulerJobInfo.jobGroup)
        )
        val httpJob = jobDetail.jobDataMap["httpJob"] as HttpJob
        return SchedulerJobInfo(
            groupName = jobDetail.key.group,
            name = jobDetail.key.name,
            description = jobDetail.description,
            cronExpression = handle(
                SchedulerTriggerQuery(
                    searchSchedulerJobInfo.jobName,
                    searchSchedulerJobInfo.jobGroup
                )
            ),
            url = httpJob.uri
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