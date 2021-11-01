package org.horae.scheduling

import org.horae.scheduling.configuration.SchedulingConfiguration
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

data class Scheduling(
    val name: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val startTime: LocalTime,
    val duration: Duration,
) {
    companion object {
        fun create(
            schedulingConfig: SchedulingConfiguration,
            period: Period,
        ) = Scheduling(
            name = schedulingConfig.name,
            description = schedulingConfig.description,
            startTime = schedulingConfig.startTime,
            duration = schedulingConfig.duration,
            startDate = period.startDate,
            endDate = period.endDate,
        )
    }
}
