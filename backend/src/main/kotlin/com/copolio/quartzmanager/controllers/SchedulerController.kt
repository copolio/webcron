package com.copolio.quartzmanager.controllers

import com.copolio.quartzmanager.dto.GetRestJobResponse
import com.copolio.quartzmanager.dto.PostRestJobRequest
import com.copolio.quartzmanager.services.RestSchedulerService
import org.quartz.JobKey
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/scheduler")
class SchedulerController(
    val restSchedulerService: RestSchedulerService
) {
    @PostMapping("/")
    fun addJob(@RequestBody params: PostRestJobRequest): ResponseEntity<GetRestJobResponse> {
        return ResponseEntity(restSchedulerService.addJob(params), HttpStatus.CREATED)
    }

    @GetMapping("/groups")
    fun getGroups(): ResponseEntity<List<String>> {
        return ResponseEntity.ok(
            restSchedulerService.getGroups()
        )
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