package com.copolio.domains.quartz.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "QRTZ_PAUSED_TRIGGER_GRPS", schema = "QUARTZ")
class PausedTriggerGroup(
    @EmbeddedId
    val id: PausedTriggerGroupId,
)