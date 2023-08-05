package com.copolio.clients.webcronclient.dto.command

data class CreateHttpJobResult(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)