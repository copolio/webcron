package com.copolio.webcron.adapter.out

import com.copolio.domains.quartz.model.HttpJobExecution
import com.copolio.domains.quartz.model.HttpJobExecutionRepository
import com.copolio.webcron.port.`in`.HttpJobExecutionQuery
import com.copolio.webcron.port.out.HttpJobExecutionLoadPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class HttpJobExecutionLoadAdapter(
    private val httpJobExecutionRepository: HttpJobExecutionRepository
) : HttpJobExecutionLoadPort {
    override fun handle(
        httpJobExecutionQuery: HttpJobExecutionQuery,
        pageable: Pageable
    ): Page<HttpJobExecution> {
        if (httpJobExecutionQuery.jobGroup.isNullOrBlank())
            return httpJobExecutionRepository.findAll(pageable)
        else if (httpJobExecutionQuery.jobName.isNullOrBlank())
            return httpJobExecutionRepository.findAllByJobGroup(
                jobGroup = httpJobExecutionQuery.jobGroup,
                pageable = pageable
            )
        return httpJobExecutionRepository.findAllByJobGroupAndJobName(
            jobName = httpJobExecutionQuery.jobName,
            jobGroup = httpJobExecutionQuery.jobGroup,
            pageable = pageable
        )
    }
}