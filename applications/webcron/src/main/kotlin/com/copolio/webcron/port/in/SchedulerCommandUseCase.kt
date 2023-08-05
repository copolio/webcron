package com.copolio.webcron.port.`in`

import com.copolio.clients.webcronclient.dto.command.CreateHttpJob
import com.copolio.clients.webcronclient.dto.command.CreateHttpJobResult
import com.copolio.clients.webcronclient.dto.command.DeleteHttpJob
import com.copolio.clients.webcronclient.dto.command.DeleteHttpJobResult

interface SchedulerCommandUseCase {
    fun handle(createHttpJob: CreateHttpJob): CreateHttpJobResult
    fun handle(deleteHttpJob: DeleteHttpJob): DeleteHttpJobResult
}