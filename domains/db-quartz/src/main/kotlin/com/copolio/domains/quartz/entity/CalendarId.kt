package com.copolio.domains.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class CalendarId(
    @Column(name = "SCHED_NAME")
    val schedulerName: String,
    @Column(name = "CALENDAR_NAME")
    val calendarName: String,
): Serializable {}