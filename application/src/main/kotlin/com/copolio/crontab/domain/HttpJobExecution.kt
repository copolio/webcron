package com.copolio.crontab.domain

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "HTTP_JOB_EXECUTION")
class HttpJobExecution(
    @Column(name = "START_TIME")
    val startTime: LocalDateTime,
    @Column(name = "END_TIME")
    val endTime: LocalDateTime,
    @Column(name = "STATUS_CODE")
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