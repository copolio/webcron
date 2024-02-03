package com.copolio.webcron.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class LockId(
    @Column(name = "SCHED_NAME")
    val schedulerName: String,
    @Column(name = "LOCK_NAME")
    val lockName: String,
) : Serializable