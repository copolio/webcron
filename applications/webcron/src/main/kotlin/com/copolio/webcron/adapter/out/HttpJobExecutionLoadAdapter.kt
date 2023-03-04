package com.copolio.webcron.adapter.out

import com.copolio.domains.quartz.repository.HttpJobExecutionRepository
import com.copolio.webcron.mapper.HttpJobExecutionMapper
import com.copolio.webcron.port.`in`.HttpJobExecutionSearchQuery
import com.copolio.webcron.port.`in`.HttpJobExecutionSearchQueryResult
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
        httpJobExecutionSearchQuery: HttpJobExecutionSearchQuery,
        pageable: Pageable
    ): Page<HttpJobExecutionSearchQueryResult> {
        if (httpJobExecutionSearchQuery.jobGroup.isNullOrBlank()) {
            return httpJobExecutionRepository.findAll(pageable)
                .map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
        } else if (httpJobExecutionSearchQuery.jobName.isNullOrBlank())
            return httpJobExecutionRepository.findAllByJobGroup(
                jobGroup = httpJobExecutionSearchQuery.jobGroup,
                pageable = pageable
            ).map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
        return httpJobExecutionRepository.findAllByJobGroupAndJobName(
            jobName = httpJobExecutionSearchQuery.jobName,
            jobGroup = httpJobExecutionSearchQuery.jobGroup,
            pageable = pageable
        ).map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
    }
}