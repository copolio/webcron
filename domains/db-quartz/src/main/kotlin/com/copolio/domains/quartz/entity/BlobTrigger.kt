package com.copolio.domains.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "QRTZ_BLOB_TRIGGERS", schema = "QUARTZ")
class BlobTrigger(
    @EmbeddedId
    val id: TriggerId,
    @Column(name = "BLOB_DATA")
    val blobData: ByteArray?,
) {
}