package com.copolio.domains.webcron.entity

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import jakarta.persistence.*
import org.springframework.http.HttpStatusCode

@Entity
@Table(name = "HTTP_JOB_EXECUTION", schema = "WEBCRON")
class HttpJobExecution(
    @Column(name = "START_TIME")
    val startTime: LocalDateTime,
    @Column(name = "END_TIME")
    val endTime: LocalDateTime,
    @Column(name = "STATUS_CODE")
    @Enumerated(EnumType.ORDINAL)
    val statusCode: HttpStatus,
    @Column(name = "RESPONSE")
    val response: String,
    @Column(name = "JOB_NAME")
    val jobName: String,
    @Column(name = "JOB_GROUP")
    val jobGroup: String,
    @Id
    @Column(name = "INSTANCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val instanceId: Long? = null,
)