package org.horae.scheduling.configuration

import org.horae.scheduling.recurrence.Recurrence
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

data class SchedulingConfiguration(
    val name: String,
    val description: String,
    val startTime: LocalTime,
    val duration: Duration,
    val recurrence: Recurrence,
    val overrides: Map<LocalDate, SchedulingConfiguration> = emptyMap(),
)
