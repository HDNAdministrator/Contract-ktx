package pt.hdn.contract.adapters

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class UUIDTypeAdapter : JsonSerializer<UUID>, JsonDeserializer<UUID?> {
    override fun serialize(src: UUID?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? = src?.toString()?.let { context?.serialize(it) }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): UUID? = json?.asString?.let { UUID.fromString(it) }
}