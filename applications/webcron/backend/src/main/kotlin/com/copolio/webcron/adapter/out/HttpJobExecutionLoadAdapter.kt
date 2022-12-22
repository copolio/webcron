package com.copolio.webcron.adapter.out

import com.copolio.domains.quartz.model.HttpJobExecutionRepository
import com.copolio.webcron.mapper.HttpJobExecutionMapper
import com.copolio.webcron.port.`in`.HttpJobExecutionQuery
import com.copolio.webcron.port.`in`.HttpJobExecutionQueryResult
import com.copolio.webcron.port.out.HttpJobExecutionLoadPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class HttpJobExecutionLoadAdapter(
    private val httpJobExecutionRepository: HttpJobExecutionRepository,
    private val httpJobExecutionMapper: HttpJobExecutionMapper
) : HttpJobExecutionLoadPort {
    override fun handle(
        httpJobExecutionQuery: HttpJobExecutionQuery,
        pageable: Pageable
    ): Page<HttpJobExecutionQueryResult> {
        if (httpJobExecutionQuery.jobGroup.isNullOrBlank()) {
            return httpJobExecutionRepository.findAll(pageable)
                .map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
        } else if (httpJobExecutionQuery.jobName.isNullOrBlank())
            return httpJobExecutionRepository.findAllByJobGroup(
                jobGroup = httpJobExecutionQuery.jobGroup,
                pageable = pageable
            ).map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
        return httpJobExecutionRepository.findAllByJobGroupAndJobName(
            jobName = httpJobExecutionQuery.jobName,
            jobGroup = httpJobExecutionQuery.jobGroup,
            pageable = pageable
        ).map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
    }
}