package com.copolio.webcron.port.`in`

interface SchedulerQueryUseCase {
    fun getGroups(): List<SchedulerJobGroupQueryResult>
    fun handle(schedulerJobByGroupQuery: SchedulerJobByGroupQuery): List<SchedulerJobQueryResult>
    fun handle(schedulerJobDetailQuery: SchedulerJobDetailQuery): SchedulerJobQueryResult
    fun handle(schedulerTriggerQuery: SchedulerTriggerQuery): String
}