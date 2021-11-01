package org.horae.scheduling.recurrence

import kotlinx.coroutines.flow.Flow
import org.horae.scheduling.Period
import org.horae.scheduling.Scheduling
import org.horae.scheduling.configuration.SchedulingConfiguration
import java.time.LocalDate

data class Monthly(
    val dayToRepeat: Int,
    override val startDate: LocalDate,
    override val endDate: LocalDate?,
    ) : Recurrence {
        override fun expand(schedulingConfiguration: SchedulingConfiguration, period: Period): Flow<Scheduling> {
            TODO("Not yet implemented")
        }
    }
