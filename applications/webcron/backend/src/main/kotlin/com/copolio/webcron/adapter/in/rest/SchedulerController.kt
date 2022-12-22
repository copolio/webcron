package com.copolio.webcron.adapter.`in`.rest

import com.copolio.domains.quartz.model.HttpJobExecution
import com.copolio.webcron.port.`in`.*
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/http-scheduler")
class SchedulerController(
    val schedulerQueryUseCase: SchedulerQueryUseCase,
    val schedulerCommandUseCase: SchedulerCommandUseCase,
    val httpJobQueryUseCase: HttpJobQueryUseCase
) {
    @PostMapping("/")
    fun addJob(@RequestBody createHttpJob: CreateHttpJob): ResponseEntity<CreateHttpJobResult> {
        return ResponseEntity(
            schedulerCommandUseCase.handle(createHttpJob),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/groups")
    fun getGroups(): ResponseEntity<List<HttpJobGroupQueryResult>> {
        return ResponseEntity.ok(
            schedulerQueryUseCase.getGroups()
        )
    }

    @GetMapping("/groups/{groupName}/jobs")
    fun getJobs(@PathVariable("groupName") groupName: String): ResponseEntity<List<HttpJobQueryResult>> {
        return ResponseEntity.ok(
            schedulerQueryUseCase.getJobs(groupName)
        )
    }

    @GetMapping("/groups/{groupName}/jobs/{jobName}")
    fun getJob(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String
    ): ResponseEntity<HttpJobQueryResult> {
        return ResponseEntity.ok(
            schedulerQueryUseCase.getJob(
                groupName, jobName
            )
        )
    }

    @DeleteMapping("/groups/{groupName}/jobs/{jobName}")
    fun deleteJob(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String
    ): ResponseEntity<DeleteHttpJobResult> {
        return ResponseEntity.ok(
            schedulerCommandUseCase.handle(
                DeleteHttpJob(
                    jobName = jobName,
                    jobGroup = groupName
                )
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
            httpJobQueryUseCase.handle(
                HttpJobExecutionQuery(
                    jobGroup = groupName,
                    jobName = jobName
                ),
                pageable = pageable
            )
        )
    }
}