package com.copolio.webcron.adapter.`in`.rest

import com.copolio.clients.webcronclient.dto.command.CreateHttpJob
import com.copolio.clients.webcronclient.dto.command.CreateHttpJobResult
import com.copolio.clients.webcronclient.dto.command.DeleteHttpJob
import com.copolio.clients.webcronclient.dto.command.DeleteHttpJobResult
import com.copolio.clients.webcronclient.dto.query.SearchSchedulerJobs
import com.copolio.clients.webcronclient.dto.query.SchedulerJobGroupInfo
import com.copolio.clients.webcronclient.dto.query.SchedulerJobInfo
import com.copolio.webcron.port.`in`.SchedulerQueryUseCase
import com.copolio.webcron.port.`in`.SchedulerUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/http-scheduler")
class SchedulerController(
    val schedulerQueryUseCase: SchedulerQueryUseCase,
    val schedulerUseCase: SchedulerUseCase,
) {
    @PostMapping("/")
    fun addJob(@RequestBody createHttpJob: CreateHttpJob): ResponseEntity<CreateHttpJobResult> {
        return ResponseEntity(
            schedulerUseCase.handle(createHttpJob),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/groups")
    fun getGroups(): ResponseEntity<List<SchedulerJobGroupInfo>> {
        return ResponseEntity.ok(
            schedulerQueryUseCase.getGroups()
        )
    }

    @GetMapping("/groups/{groupName}/jobs")
    fun getJobs(@PathVariable("groupName") groupName: String): ResponseEntity<List<SchedulerJobInfo>> {
        return ResponseEntity.ok(
            schedulerQueryUseCase.handle(SearchSchedulerJobs(jobGroup = groupName))
        )
    }

    @DeleteMapping("/groups/{groupName}/jobs/{jobName}")
    fun deleteJob(
        @PathVariable("groupName") groupName: String,
        @PathVariable("jobName") jobName: String
    ): ResponseEntity<DeleteHttpJobResult> {
        return ResponseEntity.ok(
            schedulerUseCase.handle(
                DeleteHttpJob(
                    jobName = jobName,
                    jobGroup = groupName
                )
            )
        )
    }
}