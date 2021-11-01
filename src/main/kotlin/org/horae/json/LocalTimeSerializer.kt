package org.horae.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object LocalTimeSerializer : StdSerializer<LocalTime>(LocalTime::class.java) {
    override fun serialize(value: LocalTime, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(DateTimeFormatter.ISO_TIME.format(value))
    }
}
