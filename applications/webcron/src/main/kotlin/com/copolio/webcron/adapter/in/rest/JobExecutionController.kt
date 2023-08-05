package com.copolio.webcron.adapter.`in`.rest

import com.copolio.webcron.port.`in`.HttpJobExecutionQueryUseCase
import com.copolio.clients.webcronclient.dto.query.SearchHttpJobExecution
import com.copolio.clients.webcronclient.dto.query.HttpJobExecutionInfo
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/job-executions")
class JobExecutionController(
    val httpJobExecutionQueryUseCase: HttpJobExecutionQueryUseCase
) {
    @GetMapping("/search")
    fun getJobExecutions(
        @RequestParam("groupName") groupName: String?,
        @RequestParam("jobName") jobName: String?,
        @ParameterObject @PageableDefault(page = 0, size = 20) pageable: Pageable
    ): ResponseEntity<Page<HttpJobExecutionInfo>> {
        return ResponseEntity.ok(
            httpJobExecutionQueryUseCase.handle(
                SearchHttpJobExecution(
                    jobGroup = groupName,
                    jobName = jobName
                ),
                pageable = pageable
            )
        )
    }
}