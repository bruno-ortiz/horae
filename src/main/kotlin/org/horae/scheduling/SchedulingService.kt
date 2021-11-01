package org.horae.scheduling

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import org.horae.scheduling.configuration.SchedulingConfigurationRepository
import java.util.UUID

class SchedulingService(
    private val repository: SchedulingConfigurationRepository,
) {

    suspend fun getSchedules(period: Period): List<Scheduling> {
        val configurations = repository.findSchedulingConfigurationBy(UUID.randomUUID().toString(), period).asFlow()

        return configurations.transform { schedulingConfig ->
            val recurrence = schedulingConfig.recurrence
            emitAll(recurrence.expand(schedulingConfig, period))
        }.toList()
    }
}
