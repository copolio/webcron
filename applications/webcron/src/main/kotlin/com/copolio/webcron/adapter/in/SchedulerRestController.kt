package com.copolio.webcron.adapter.`in`

import com.copolio.webcron.port.`in`.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/http-scheduler")
class SchedulerRestController(
    val schedulerQueryUseCase: SchedulerQueryUseCase,
    val schedulerCommandUseCase: SchedulerCommandUseCase,
    val httpJobExecutionQueryUseCase: HttpJobExecutionQueryUseCase
) {
    @PostMapping("/")
    fun addJob(@RequestBody createHttpJob: CreateHttpJob): ResponseEntity<CreateHttpJobResult> {
        return ResponseEntity(
            schedulerCommandUseCase.handle(createHttpJob),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/groups")
    fun getGroups(): ResponseEntity<List<SchedulerJobGroupQueryResult>> {
        return ResponseEntity.ok(
            schedulerQueryUseCase.getGroups()
        )
    }

    @GetMapping("/groups/{groupName}/jobs")
    fun getJobs(@PathVariable("groupName") groupName: String): ResponseEntity<List<SchedulerJobQueryResult>> {
        return ResponseEntity.ok(
            schedulerQueryUseCase.handle(SchedulerJobByGroupQuery(jobGroup = groupName))
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
}