package pt.hdn.contract.adapters

import com.google.gson.*
import java.lang.reflect.Type
import java.time.Instant

class TimestampTypeAdapter : JsonSerializer<Instant>, JsonDeserializer<Instant> {
    override fun serialize(src: Instant?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? = src?.let { JsonPrimitive(it.toString()) }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Instant? = json?.let { Instant.parse(json.asString) }
}