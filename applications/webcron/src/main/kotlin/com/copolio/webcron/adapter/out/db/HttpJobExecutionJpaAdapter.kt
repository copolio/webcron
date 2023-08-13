package com.copolio.webcron.adapter.out.db

import com.copolio.domains.webcron.entity.HttpJobExecution
import com.copolio.domains.webcron.repository.HttpJobExecutionRepository
import com.copolio.webcron.mapper.HttpJobExecutionMapper
import com.copolio.clients.webcronclient.dto.command.CreateHttpJobExecution
import com.copolio.clients.webcronclient.dto.query.SearchHttpJobExecution
import com.copolio.clients.webcronclient.dto.query.HttpJobExecutionInfo
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
        searchHttpJobExecution: SearchHttpJobExecution,
        pageable: Pageable
    ): Page<HttpJobExecutionInfo> {
        if (searchHttpJobExecution.jobGroup.isNullOrBlank()) {
            return httpJobExecutionRepository.findAll(pageable)
                .map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
        } else if (searchHttpJobExecution.jobName.isNullOrBlank())
            return httpJobExecutionRepository.findAllByJobGroup(
                jobGroup = searchHttpJobExecution.jobGroup!!,
                pageable = pageable
            ).map { httpJobExecution -> httpJobExecutionMapper.convert(httpJobExecution) }
        return httpJobExecutionRepository.findAllByJobGroupAndJobName(
            jobName = searchHttpJobExecution.jobName!!,
            jobGroup = searchHttpJobExecution.jobGroup!!,
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