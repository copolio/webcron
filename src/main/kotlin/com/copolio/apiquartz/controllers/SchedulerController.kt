package com.copolio.apiquartz.controllers

import com.copolio.apiquartz.dto.GetRestJobResponse
import com.copolio.apiquartz.dto.PostRestJobRequest
import com.copolio.apiquartz.services.RestSchedulerService
import org.quartz.JobKey
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quartz-api")
class SchedulerController(
    val restSchedulerService: RestSchedulerService
) {
    @GetMapping("/groups")
    fun getGroups(): ResponseEntity<List<String>> {
        return ResponseEntity.ok(
            restSchedulerService.getGroups()
        )
    }

    @PostMapping("/")
    fun addJob(@RequestBody params: PostRestJobRequest): ResponseEntity<GetRestJobResponse> {
        return ResponseEntity(restSchedulerService.addJob(params), HttpStatus.CREATED)
    }

    @GetMapping("/groups/{groupName}/jobs")
    fun getJobs(@PathVariable("groupName") groupName: String): ResponseEntity<List<JobKey>> {
        return ResponseEntity.ok(
            restSchedulerService.getJobs(groupName)
        )
    }

    @GetMapping("/groups/{groupName}/jobs/{jobName}")
    fun getJob(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String
    ): ResponseEntity<GetRestJobResponse> {
        return ResponseEntity.ok(
            restSchedulerService.getJob(
                groupName, jobName
            )
        )
    }

    @DeleteMapping("/groups/{groupName}/jobs/{jobName}")
    fun deleteJob(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String
    ): ResponseEntity<String> {
        return ResponseEntity.ok(
            restSchedulerService.deleteJob(
                jobName = jobName,
                jobGroup = groupName
            )
        )
    }
}