package com.copolio.domains.quartz.entity

import jakarta.persistence.*

@Entity
@Table(name = "QRTZ_LOCKS", schema = "QUARTZ")
class Lock (
    @EmbeddedId
    val id: LockId,
)