package org.horae.scheduling.recurrence

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.horae.scheduling.Period
import org.horae.scheduling.Scheduling
import org.horae.scheduling.configuration.SchedulingConfiguration
import java.time.LocalDate

data class Single(
    val date: LocalDate,
) : Recurrence {
    override val startDate: LocalDate = date
    override val endDate: LocalDate = date

    override fun expand(schedulingConfiguration: SchedulingConfiguration, period: Period): Flow<Scheduling> {
        return flow {
            if (date.isBetween(period)) {
                emit(Scheduling.create(schedulingConfiguration, Period(startDate, endDate)))
            }
        }
    }

    private fun LocalDate.isBetween(period: Period): Boolean = this in period.startDate..period.endDate
}
