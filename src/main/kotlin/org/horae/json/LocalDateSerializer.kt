package org.horae.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {
    override fun serialize(value: LocalDate, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(DateTimeFormatter.ISO_DATE.format(value))
    }
}
