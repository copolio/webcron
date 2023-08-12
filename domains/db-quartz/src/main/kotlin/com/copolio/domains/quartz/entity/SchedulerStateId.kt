package com.copolio.domains.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class SchedulerStateId (
    @Column(name = "SCHED_NAME")
    val schedulerName: String,
    @Column(name = "INSTANCE_NAME")
    val instanceName: String,
): Serializable