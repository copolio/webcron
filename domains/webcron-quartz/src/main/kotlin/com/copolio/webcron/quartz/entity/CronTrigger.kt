package com.copolio.webcron.quartz.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "QRTZ_CRON_TRIGGERS", schema = "QUARTZ")
class CronTrigger(
    @EmbeddedId
    val id: TriggerId,
    @Column(name = "CRON_EXPRESSION")
    val cronExpression: String,
    @Column(name = "TIME_ZONE_ID")
    val timeZoneId: String?,
) {
}