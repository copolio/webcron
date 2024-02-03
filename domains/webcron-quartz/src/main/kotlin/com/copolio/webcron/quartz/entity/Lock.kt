package com.copolio.webcron.quartz.entity

import jakarta.persistence.*

@Entity
@Table(name = "QRTZ_LOCKS", schema = "QUARTZ")
class Lock (
    @EmbeddedId
    val id: LockId,
)