package com.copolio.quartzmanager.controllers

import com.copolio.quartzmanager.dto.GetHttpJobResponse
import com.copolio.quartzmanager.dto.GetJobGroupResponse
import com.copolio.quartzmanager.dto.PostHttpJobRequest
import com.copolio.quartzmanager.service.HttpSchedulerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/scheduler")
class HttpSchedulerController(
    val httpSchedulerService: HttpSchedulerService
) {
    @PostMapping("/")
    fun addJob(@RequestBody params: PostHttpJobRequest): ResponseEntity<GetHttpJobResponse> {
        return ResponseEntity(httpSchedulerService.addJob(params), HttpStatus.CREATED)
    }

    @GetMapping("/groups")
    fun getGroups(): ResponseEntity<List<GetJobGroupResponse>> {
        return ResponseEntity.ok(
            httpSchedulerService.getGroups()
        )
    }

    @GetMapping("/groups/{groupName}/jobs")
    fun getJobs(@PathVariable("groupName") groupName: String): ResponseEntity<List<GetHttpJobResponse>> {
        return ResponseEntity.ok(
            httpSchedulerService.getJobs(groupName)
        )
    }

    @GetMapping("/groups/{groupName}/jobs/{jobName}")
    fun getJob(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String
    ): ResponseEntity<GetHttpJobResponse> {
        return ResponseEntity.ok(
            httpSchedulerService.getJob(
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
            httpSchedulerService.deleteJob(
                jobName = jobName,
                jobGroup = groupName
            )
        )
    }
}