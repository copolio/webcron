package com.copolio.webcron.quartz.entity

import jakarta.persistence.*
import java.math.BigInteger

@Entity
@Table(name = "QRTZ_TRIGGERS", schema = "QUARTZ")
class Trigger (
    @EmbeddedId
    val id: TriggerId,
    @MapsId(value = "jobDetailId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = [
        JoinColumn(
            name = "SCHED_NAME",
            referencedColumnName = "SCHED_NAME",
        ),
        JoinColumn(
            name = "JOB_NAME",
            referencedColumnName = "JOB_NAME"
        ),
        JoinColumn(
            name = "JOB_GROUP",
            referencedColumnName = "JOB_GROUP"
        ),
    ]
    )
    val jobDetail: JobDetail,
    @Column(name = "DESCRIPTION")
    val description: String?,
    @Column(name = "NEXT_FIRE_TIME")
    val nextFireTime: BigInteger?,
    @Column(name = "PREV_FIRE_TIME")
    val prevFireTime: BigInteger?,
    @Column(name = "PRIORITY")
    val priority: Int?,
    @Column(name = "TRIGGER_STATE")
    val triggerState: String?,
    @Column(name = "TRIGGER_TYPE")
    val triggerType: String?,
    @Column(name = "START_TIME")
    val startTime: BigInteger?,
    @Column(name = "END_TIME")
    val endTime: BigInteger?,
    @Column(name = "CALENDAR_NAME")
    val calendarName: String?,
    @Column(name = "MISFIRE_INSTR")
    val misfireInstr: Int?,
    @Column(name = "JOB_DATA")
    val jobData: ByteArray?,
)