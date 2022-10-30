package com.copolio.quartzmanager.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface HttpJobExecutionRepository : JpaRepository<HttpJobExecution, Long> {
    fun findAllByJobGroupAndJobName(jobGroup: String, jobName: String, pageable: Pageable): Page<HttpJobExecution>
}