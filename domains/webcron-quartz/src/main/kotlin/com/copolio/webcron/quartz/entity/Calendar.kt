package com.copolio.webcron.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Entity
@Table(name = "QRTZ_CALENDARS", schema = "QUARTZ")
class Calendar(
    @EmbeddedId
    val id: CalendarId,
    @Column(name = "CALENDAR")
    val calendar: ByteArray,
) {

}