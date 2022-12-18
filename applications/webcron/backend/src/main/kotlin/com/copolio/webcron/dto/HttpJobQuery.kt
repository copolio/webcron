package com.copolio.webcron.dto

data class HttpJobGroupDto(
    val name: String
)

data class HttpJobInfoDto(
    val groupName: String,
    val name: String,
    val description: String,
    val cronExpression: String,
    val url: String,
)