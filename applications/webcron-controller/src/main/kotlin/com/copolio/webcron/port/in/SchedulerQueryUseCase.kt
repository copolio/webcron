package com.copolio.webcron.port.`in`

import com.copolio.clients.webcronclient.dto.query.*

interface SchedulerQueryUseCase {
    fun getGroups(): List<SchedulerJobGroupInfo>
    fun handle(searchSchedulerJobs: SearchSchedulerJobs): List<SchedulerJobInfo>
    fun handle(searchSchedulerJobInfo: SearchSchedulerJobInfo): SchedulerJobInfo
    fun handle(schedulerTriggerQuery: SchedulerTriggerQuery): String
}