package org.horae.scheduling.configuration

import org.horae.scheduling.Period

interface SchedulingConfigurationRepository {
    suspend fun findSchedulingConfigurationBy(userId: String, period: Period): List<SchedulingConfiguration>
}
