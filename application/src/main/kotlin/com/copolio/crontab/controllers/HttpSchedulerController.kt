package com.copolio.crontab.controllers

import com.copolio.crontab.domain.HttpJobExecution
import com.copolio.crontab.dto.GetHttpJobResponse
import com.copolio.crontab.dto.GetJobGroupResponse
import com.copolio.crontab.dto.PostHttpJobRequest
import com.copolio.crontab.service.HttpSchedulerService
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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

    @GetMapping("/groups/{groupName}/jobs/{jobName}/executions")
    fun getJobExecutions(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String,
        @ParameterObject @PageableDefault(page = 0, size = 20) pageable: Pageable
    ): ResponseEntity<Page<HttpJobExecution>> {
        return ResponseEntity.ok(
            httpSchedulerService.getJobExecutions(
                jobGroup = groupName, jobName = jobName, pageable = pageable
            )
        )
    }
}