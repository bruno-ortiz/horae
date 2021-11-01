package org.horae.scheduling.recurrence

import kotlinx.coroutines.flow.Flow
import org.horae.scheduling.Period
import org.horae.scheduling.Scheduling
import org.horae.scheduling.configuration.SchedulingConfiguration
import java.time.LocalDate

interface Recurrence {
    val startDate: LocalDate
    val endDate: LocalDate?

    fun expand(schedulingConfiguration: SchedulingConfiguration, period: Period): Flow<Scheduling>
}
