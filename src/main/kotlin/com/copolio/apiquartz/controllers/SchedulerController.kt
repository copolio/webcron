package com.copolio.apiquartz.controllers

import com.copolio.apiquartz.dto.GetCronJobResponse
import com.copolio.apiquartz.dto.PostCronJobRequest
import com.copolio.apiquartz.services.SchedulerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/jobs")
class SchedulerController(
    val schedulerService: SchedulerService
) {
    @GetMapping("/")
    fun getJobs(): ArrayList<GetCronJobResponse> {
        return schedulerService.getJobs()
    }

    @PostMapping("/")
    fun addCronJob(@RequestBody params: PostCronJobRequest): String {
        return schedulerService.scheduleCronJob(params)
    }
}