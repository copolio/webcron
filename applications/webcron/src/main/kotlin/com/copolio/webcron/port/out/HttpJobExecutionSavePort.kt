package com.copolio.webcron.port.out

import com.copolio.clients.webcronclient.dto.command.CreateHttpJobExecution

interface HttpJobExecutionSavePort {
    fun handle(
        createHttpJobExecution: CreateHttpJobExecution
    )
}