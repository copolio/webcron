package com.copolio.domains.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "QRTZ_JOB_DETAILS", schema = "QUARTZ")
class JobDetail(
    @EmbeddedId
    val id: JobDetailId,
    @Column(name = "DESCRIPTION")
    val description: String,
    @Column(name = "JOB_CLASS_NAME")
    val jobClassName: String,
    @Column(name = "IS_DURABLE")
    val isDurable: String,
    @Column(name = "IS_NONCONCURRENT")
    val isNonConcurrent: String,
    @Column(name = "IS_UPDATE_DATA")
    val isUpdateData: String,
    @Column(name = "REQUESTS_RECOVERY")
    val requestsRecovery: String,
    @Column(name = "JOB_DATA")
    val jobData: ByteArray?,
)