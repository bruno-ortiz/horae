package org.horae.scheduling.recurrence

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.horae.scheduling.Period
import org.horae.scheduling.Scheduling
import org.horae.scheduling.configuration.SchedulingConfiguration
import java.time.DayOfWeek
import java.time.LocalDate

data class Weekdays(
    override val startDate: LocalDate,
    override val endDate: LocalDate?,
    val repeatingDays: Set<DayOfWeek>,
) : Recurrence {

    override fun expand(schedulingConfiguration: SchedulingConfiguration, period: Period): Flow<Scheduling> {
        return flow {
            if (shouldCalculateWeekdays(this@Weekdays, period)) {
                val recurrenceEndingDate = endDate ?: period.endDate
                var currentDate = period.startDate
                while (currentDate.isBefore(period.endDate) && currentDate.isBefore(recurrenceEndingDate)) {
                    val scheduling = if (schedulingConfiguration.overrides.containsKey(currentDate)) {
                        val overrideConfig = schedulingConfiguration.overrides[currentDate]!!
                        val overrideRecurrence = overrideConfig.recurrence
                        Scheduling.create(overrideConfig,
                            Period(overrideRecurrence.startDate, overrideRecurrence.endDate!!))
                    } else if (currentDate.dayOfWeek in repeatingDays) {
                        Scheduling.create(schedulingConfiguration, Period(currentDate, currentDate))
                    } else null

                    if (scheduling != null) {
                        emit(scheduling)
                    }
                    currentDate = currentDate.plusDays(1)
                }
            }
        }
    }

    private fun shouldCalculateWeekdays(recurrence: Recurrence, period: Period) =
        recurrence.startDate <= period.endDate && (recurrence.endDate == null || recurrence.endDate!! >= period.startDate)

    companion object {
        fun everyDay(startDate: LocalDate, endDate: LocalDate? = null) = Weekdays(
            startDate = startDate,
            endDate = endDate,
            repeatingDays = DayOfWeek.values().toSet()
        )

        fun workDays(startDate: LocalDate, endDate: LocalDate? = null) = Weekdays(
            startDate = startDate,
            endDate = endDate,
            repeatingDays = setOf(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY
            )
        )
    }
}
