package com.copolio.domains.quartz.repository

import com.copolio.domains.quartz.entity.HttpJobExecution
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface HttpJobExecutionRepository : JpaRepository<HttpJobExecution, Long> {
    fun findAllByJobGroup(jobGroup: String, pageable: Pageable): Page<HttpJobExecution>
    fun findAllByJobGroupAndJobName(jobGroup: String, jobName: String, pageable: Pageable): Page<HttpJobExecution>
}