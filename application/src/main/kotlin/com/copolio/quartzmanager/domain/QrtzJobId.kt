package com.copolio.quartzmanager.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Id

data class QrtzJobId(
    @Id
    @Column(name = "SCHED_NAME")
    val schedName: String,
    @Id
    @Column(name = "JOB_NAME")
    val jobName: String,
    @Id
    @Column(name = "JOB_GROUP")
    val jobGroup: String,
) : Serializable