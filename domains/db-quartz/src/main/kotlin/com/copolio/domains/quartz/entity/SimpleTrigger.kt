package com.copolio.domains.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigInteger

@Entity
@Table(name = "QRTZ_SIMPLE_TRIGGERS", schema = "QUARTZ")
class SimpleTrigger (
    @EmbeddedId
    val id: TriggerId,
    @Column(name = "REPEAT_COUNT")
    val repeatCount: BigInteger,
    @Column(name = "REPEAT_INTERVAL")
    val repeatInterval: BigInteger,
    @Column(name = "TIMES_TRIGGERED")
    val timesTriggered: BigInteger,
)