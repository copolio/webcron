package com.copolio.domains.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class TriggerId(
    @Column(name = "SCHED_NAME", columnDefinition = "varchar(120) not null")
    val schedulerName: String,
    @Column(name = "TRIGGER_NAME", columnDefinition = "varchar(190) not null")
    val triggerName: String,
    @Column(name = "TRIGGER_GROUP", columnDefinition = "varchar(190) not null")
    val triggerGroup: String,
) : Serializable {
}