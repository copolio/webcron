package com.copolio.webcron.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class PausedTriggerGroupId(
    @Column(name = "SCHED_NAME")
    val schedName: String,
    @Column(name = "TRIGGER_GROUP")
    val triggerGroup: String,
) : Serializable