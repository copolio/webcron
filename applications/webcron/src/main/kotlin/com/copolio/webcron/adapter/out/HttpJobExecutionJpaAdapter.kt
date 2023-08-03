package com.copolio.webcron.adapter.out

import com.copolio.domains.quartz.entity.HttpJobExecution
import com.copolio.domains.quartz.repository.HttpJobExecutionRepository
import com.copolio.webcron.mapper.HttpJobExecutionMapper
import com.copolio.webcron.port.`in`.CreateHttpJobExecution
import com.copolio.webcron.port.`in`.HttpJobExecutionSearchQuery
import com.copolio.webcron.port.`in`.HttpJobExecutionSearchQueryResult
import com.copolio.webcron.port.out.HttpJobExecutionLoadPort
import com.copolio.webcron.port.out.HttpJobExecutionSavePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HttpJobExecutionJpaAdapter(
    private val httpJobExecutionRepository: HttpJobExecutionRepository,
    private val httpJobExecutionMapper: HttpJobExecutionMapper
) : HttpJobExecutionLoadPort, HttpJobExecutionSavePort {
    @Transactional(readOnly = true)
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

    @Transactional
    override fun handle(
        createHttpJobExecution: CreateHttpJobExecution
    ) {
        val httpJobExecution = HttpJobExecution(
            startTime = createHttpJobExecution.startTime,
            endTime = createHttpJobExecution.endTime,
            statusCode = createHttpJobExecution.httpStatus,
            response = createHttpJobExecution.response,
            jobName = createHttpJobExecution.jobName,
            jobGroup = createHttpJobExecution.jobGroup
        )
        httpJobExecutionRepository.save(httpJobExecution)
    }
}