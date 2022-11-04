package com.copolio.crontab.domain

import javax.persistence.*

@Entity
@Table(name = "QRTZ_JOB_DETAILS")
@IdClass(QrtzJobId::class)
class QrtzJobDetails(
    @Id
    @Column(name = "SCHED_NAME")
    val schedName: String,
    @Id
    @Column(name = "JOB_NAME")
    val jobName: String,
    @Id
    @Column(name = "JOB_GROUP")
    val jobGroup: String,
    @Column(name = "DESCRIPTION")
    val description: String,
    @Column(name = "JOB_CLASS_NAME")
    val jobClassName: String,
    @Column(name = "IS_DURABLE")
    val isDurable: String,
    @Column(name = "IS_NONCONCURRENT")
    val isNonconcurrent: String,
    @Column(name = "IS_UPDATE_DATA")
    val isUpdateData: String,
    @Column(name = "REQUESTS_RECOVERY")
    val requestsRecovery: String,
    @Column(name = "JOB_DATA")
    val jobData: String,
)