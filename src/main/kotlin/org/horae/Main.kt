package org.horae

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import kotlinx.coroutines.runBlocking
import org.horae.json.DurationSerializer
import org.horae.json.LocalDateSerializer
import org.horae.json.LocalTimeSerializer
import org.horae.scheduling.Period
import org.horae.scheduling.SchedulingService
import org.horae.scheduling.configuration.MockSchedulingConfigurationRepository
import java.time.LocalDate

private val jackson = jsonMapper {
    val simpleModule = SimpleModule().apply {
        addSerializer(LocalDateSerializer)
        addSerializer(LocalTimeSerializer)
        addSerializer(DurationSerializer)
    }
    addModule(simpleModule)
    addModule(kotlinModule())
    enable(SerializationFeature.INDENT_OUTPUT)
}

fun main() = runBlocking {
    val service = SchedulingService(MockSchedulingConfigurationRepository)

    val period = Period(
        startDate = LocalDate.of(2021, 10, 31),
        endDate = LocalDate.of(2021, 11, 10)
    )
    val schedulingList = service.getSchedules(period)
    println(jackson.writeValueAsString(schedulingList))
}
