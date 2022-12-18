package com.copolio.webcron.controller

import com.copolio.webcron.dto.HttpJobInfoDto
import com.copolio.webcron.dto.HttpJobGroupDto
import com.copolio.webcron.dto.CreateHttpJobCommand
import com.copolio.webcron.service.HttpSchedulerService
import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.domains.quartz.service.HttpJobService
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/http-scheduler")
class HttpSchedulerController(
    val httpSchedulerService: HttpSchedulerService,
    val httpJobService: HttpJobService
) {
    @PostMapping("/")
    fun addJob(@RequestBody params: CreateHttpJobCommand): ResponseEntity<HttpJobInfoDto> {
        return ResponseEntity(httpSchedulerService.addJob(params), HttpStatus.CREATED)
    }

    @GetMapping("/groups")
    fun getGroups(): ResponseEntity<List<HttpJobGroupDto>> {
        return ResponseEntity.ok(
            httpSchedulerService.getGroups()
        )
    }

    @GetMapping("/groups/{groupName}/jobs")
    fun getJobs(@PathVariable("groupName") groupName: String): ResponseEntity<List<HttpJobInfoDto>> {
        return ResponseEntity.ok(
            httpSchedulerService.getJobs(groupName)
        )
    }

    @GetMapping("/groups/{groupName}/jobs/{jobName}")
    fun getJob(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String
    ): ResponseEntity<HttpJobInfoDto> {
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

    @GetMapping("/executions/search")
    fun getJobExecutions(
        @RequestParam("groupName") groupName: String?,
        @RequestParam("jobName") jobName: String?,
        @ParameterObject @PageableDefault(page = 0, size = 20) pageable: Pageable
    ): ResponseEntity<Page<HttpJobExecution>> {
        return ResponseEntity.ok(
            httpJobService.getJobExecutions(
                jobGroup = groupName, jobName = jobName, pageable = pageable
            )
        )
    }
}