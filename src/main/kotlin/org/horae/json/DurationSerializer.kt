package org.horae.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.Duration

object DurationSerializer : StdSerializer<Duration>(Duration::class.java) {
    override fun serialize(value: Duration, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toString())
    }
}
