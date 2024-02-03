package com.copolio.webcron.quartz.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "QRTZ_SIMPROP_TRIGGERS", schema = "QUARTZ")
class SimpropTrigger (
    @EmbeddedId
    val id: TriggerId,
    @Column(name = "STR_PROP_1")
    val strProp1: String?,
    @Column(name = "STR_PROP_2")
    val strProp2: String?,
    @Column(name = "STR_PROP_3")
    val strProp3: String?,
    @Column(name = "INT_PROP_1")
    val intProp1: Int?,
    @Column(name = "INT_PROP_2")
    val intProp2: Int?,
    @Column(name = "LONG_PROP_1")
    val longProp1: Long?,
    @Column(name = "LONG_PROP_2")
    val longProp2: Long?,
    @Column(name = "DEC_PROP_1")
    val decProp1: BigDecimal?,
    @Column(name = "DEC_PROP_2")
    val decProp2: BigDecimal?,
    @Column(name = "BOOL_PROP_1")
    val boolProp1: String?,
    @Column(name = "BOOL_PROP_2")
    val boolProp2: String?,
)