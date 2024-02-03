package com.copolio.webcron.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class FiredTriggerId(
    @Column(name = "SCHED_NAME")
    val schedulerName: String,
    @Column(name = "ENTRY_ID")
    val entryId: String,
) : Serializable
