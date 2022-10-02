package com.copolio.apiquartz.controllers

import com.copolio.apiquartz.config.SimpleJob
import com.copolio.apiquartz.dto.PostJobRequest
import org.quartz.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/schedulers")
class SchedulerController(val scheduler: Scheduler) {
    @GetMapping("/triggers")
    fun getTriggers(): MutableList<String>? {
        val triggers = scheduler.getJobGroupNames()
        return triggers
    }

    @PostMapping("/")
    fun scheduleJob(@RequestBody params: PostJobRequest): String {
        val jobDetail = jobDetail(params)
        val trigger = trigger(jobDetail)

        return scheduler.scheduleJob(jobDetail, trigger).toString()
    }

    private fun trigger(jobDetail: JobDetail?): SimpleTrigger? =
        TriggerBuilder.newTrigger().forJob(jobDetail).forJob(jobDetail)
            .withIdentity(jobDetail?.key?.name, jobDetail?.key?.group)
            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10))
            .build()

    private fun jobDetail(params: PostJobRequest): JobDetail? =
        JobBuilder.newJob().ofType(SimpleJob::class.java).storeDurably()
            .withIdentity(params.identity)
            .withDescription(params.description)
            .build()
}