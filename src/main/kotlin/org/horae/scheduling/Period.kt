package org.horae.scheduling

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.time.temporal.TemporalAdjusters.firstDayOfYear
import java.time.temporal.TemporalAdjusters.lastDayOfYear
import java.time.temporal.WeekFields
import java.util.Locale


data class Period(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    companion object {
        fun today(zoneId: ZoneId = ZoneId.systemDefault()) = Period(
            startDate = LocalDate.now(zoneId),
            endDate = LocalDate.now(zoneId)
        )

        fun currentYear(zoneId: ZoneId = ZoneId.systemDefault()): Period {
            val now = LocalDate.now(zoneId)
            val firstDay = now.with(firstDayOfYear())
            val lastDay = now.with(lastDayOfYear())
            return Period(firstDay, lastDay)
        }

        fun currentWeek(zoneId: ZoneId = ZoneId.systemDefault()): Period {
            val now = LocalDate.now(zoneId)
            val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
            val lastDayOfWeek = DayOfWeek.of((firstDayOfWeek.value + 5) % DayOfWeek.values().size + 1)

            val firstDay = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek))
            val lastDay = now.with(TemporalAdjusters.nextOrSame(lastDayOfWeek))
            return Period(firstDay, lastDay)
        }
    }
}
