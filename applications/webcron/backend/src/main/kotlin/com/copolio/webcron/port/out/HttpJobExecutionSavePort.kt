package com.copolio.webcron.port.out

import com.copolio.webcron.port.`in`.CreateHttpJobExecution

interface HttpJobExecutionSavePort {
    fun handle(
        createHttpJobExecution: CreateHttpJobExecution
    )
}