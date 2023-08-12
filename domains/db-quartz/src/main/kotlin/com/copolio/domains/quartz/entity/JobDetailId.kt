package com.copolio.domains.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class JobDetailId(
    @Column(name = "SCHED_NAME")
    val schedulerName: String,
    @Column(name = "JOB_NAME")
    val jobName: String,
    @Column(name = "JOB_GROUP")
    val jobGroup: String,
) : Serializable