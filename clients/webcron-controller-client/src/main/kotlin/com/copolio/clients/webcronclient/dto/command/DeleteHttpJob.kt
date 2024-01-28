package com.copolio.clients.webcronclient.dto.command

data class DeleteHttpJob(
    val jobName: String,
    val jobGroup: String
)