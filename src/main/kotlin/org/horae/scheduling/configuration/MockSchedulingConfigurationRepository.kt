package org.horae.scheduling.configuration

import org.horae.scheduling.Period
import org.horae.scheduling.recurrence.Single
import org.horae.scheduling.recurrence.Weekdays
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

object MockSchedulingConfigurationRepository : SchedulingConfigurationRepository {
    override suspend fun findSchedulingConfigurationBy(userId: String, period: Period): List<SchedulingConfiguration> {
        return listOf(
            SchedulingConfiguration(
                name = "Dentist",
                description = "Should check my teeth regularly",

                duration = Duration.ofHours(2),
                startTime = LocalTime.of(10, 30),
                recurrence = Single(
                    date = LocalDate.of(2021, 11, 4),
                )
            ),
            SchedulingConfiguration(
                name = "Touchpoint divy",
                description = "Lets align with the Team",
                duration = Duration.ofMinutes(30),
                startTime = LocalTime.of(14, 45),
                recurrence = Weekdays(
                    startDate = LocalDate.of(2021, 11, 1),
                    endDate = LocalDate.of(2021, 12, 1),
                    repeatingDays = setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY)
                )
            ),
            SchedulingConfiguration(
                name = "Daily meeting",
                description = "Lets be good teammates",
                duration = Duration.ofMinutes(15),
                startTime = LocalTime.of(11, 30),
                recurrence = Weekdays.workDays(
                    startDate = LocalDate.of(2021, 11, 1),
                ),
                overrides = mapOf(
                    LocalDate.of(2021, 11, 4) to SchedulingConfiguration(
                        name = "Daily meeting",
                        description = "Lets be good teammates",
                        duration = Duration.ofMinutes(15),
                        startTime = LocalTime.of(9, 0),
                        recurrence = Single(
                            date = LocalDate.of(2021, 11, 5),
                        )
                    ))
            )
        )
    }
}
