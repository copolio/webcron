package com.copolio.quartzapi.service

import com.copolio.quartzapi.entity.HttpJobExecution
import com.copolio.quartzapi.repository.HttpJobExecutionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

interface HttpJobService {
    fun getJobExecutions(jobName: String?, jobGroup: String?, pageable: Pageable): Page<HttpJobExecution>
}

@Service
class HttpJobServiceImpl(
    private val httpJobExecutionRepository: HttpJobExecutionRepository
) : HttpJobService {
    override fun getJobExecutions(
        jobName: String?,
        jobGroup: String?,
        pageable: Pageable
    ): Page<HttpJobExecution> {
        if (jobGroup.isNullOrBlank())
            return httpJobExecutionRepository.findAll(pageable)
        else if (jobName.isNullOrBlank())
            return httpJobExecutionRepository.findAllByJobGroup(
                jobGroup = jobGroup,
                pageable = pageable
            )
        return httpJobExecutionRepository.findAllByJobGroupAndJobName(
            jobName = jobName,
            jobGroup = jobGroup,
            pageable = pageable
        )
    }
}