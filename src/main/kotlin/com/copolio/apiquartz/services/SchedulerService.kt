package com.copolio.apiquartz.services

import com.copolio.apiquartz.config.RestJob
import com.copolio.apiquartz.dto.CronTriggerDto
import com.copolio.apiquartz.dto.GetCronJobResponse
import com.copolio.apiquartz.dto.PostCronJobRequest
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

interface SchedulerService {
    fun scheduleCronJob(@RequestBody params: PostCronJobRequest): String
    fun getJobs(): ArrayList<GetCronJobResponse>
}

@Service
class SchedulerServiceImpl(
    val scheduler: Scheduler
) : SchedulerService {
    override fun scheduleCronJob(@RequestBody params: PostCronJobRequest): String {
        val jobDataMap = JobDataMap()
        jobDataMap.put("url", params.url)
        val jobDetail = JobBuilder.newJob()
            .ofType(RestJob::class.java)
            .storeDurably()
            .withIdentity(params.identity)
            .withDescription(params.description)
            .setJobData(jobDataMap)
            .build()
        val trigger = TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(jobDetail.key?.name, jobDetail.key?.group)
            .withDescription(jobDetail.description)
            .withSchedule(CronScheduleBuilder.cronSchedule(params.cronExpression))
            .build()
        return scheduler.scheduleJob(jobDetail, trigger).toString()
    }

    override fun getJobs(): ArrayList<GetCronJobResponse> {
        val jobs = ArrayList<GetCronJobResponse>()

        for (groupName in scheduler.jobGroupNames) {
            for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                val triggers = scheduler.getTriggersOfJob(jobKey)
                val triggerDtos = ArrayList<CronTriggerDto>()
                triggers.forEach { t ->
                    when (t) {
                        is CronTrigger -> triggerDtos.add(
                            CronTriggerDto(
                                identity = t.key.name,
                                description = t.description,
                                cronExpression = t.cronExpression.toString()
                            )
                        )
                    }
                }
                val job = GetCronJobResponse(
                    groupName = jobKey.group,
                    jobName = jobKey.name,
                    triggers = triggerDtos
                )
                jobs.add(job)
            }
        }
        return jobs
    }
}