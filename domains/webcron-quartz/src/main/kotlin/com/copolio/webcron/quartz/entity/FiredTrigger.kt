package com.copolio.webcron.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigInteger

@Entity
@Table(name = "QRTZ_FIRED_TRIGGERS", schema = "QUARTZ")
class FiredTrigger(
    @EmbeddedId
    val id: FiredTriggerId,
    @Column(name = "TRIGGER_NAME")
    val triggerName: String,
    @Column(name = "TRIGGER_GROUP")
    val triggerGroup: String,
    @Column(name = "INSTANCE_NAME")
    val instanceName: String,
    @Column(name = "FIRED_TIME")
    val firedTime: BigInteger,
    @Column(name = "SCHED_TIME")
    val schedulerTime: BigInteger,
    @Column(name = "PRIORITY")
    val priority: Int,
    @Column(name = "STATE")
    val state: String,
    @Column(name = "JOB_NAME")
    val jobName: String,
    @Column(name = "JOB_GROUP")
    val jobGroup: String,
    @Column(name = "IS_NONCONCURRENT")
    val isNonConcurrent: String,
    @Column(name = "REQUESTS_RECOVERY")
    val requestsRecovery: String,
) {
}