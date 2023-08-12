package com.copolio.domains.quartz.entity

import jakarta.persistence.*
import java.math.BigInteger

@Entity
@Table(name = "QRTZ_SCHEDULER_STATE", schema = "QUARTZ")
class SchedulerState(
    @EmbeddedId
    val id: SchedulerStateId,
    @Column(name = "LAST_CHECKIN_TIME")
    val lastCheckinTime: BigInteger,
    @Column(name = "CHECKIN_INTERVAL")
    val checkinInterval: BigInteger,
) {
}