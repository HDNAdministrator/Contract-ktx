package pt.hdn.contract.adapters

import com.google.gson.*
import java.lang.reflect.Type

class MapTypeAdapter<K, V>: JsonSerializer<Map<K, V>?>, JsonDeserializer<Map<K, V>?> {
    override fun serialize(src: Map<K, V>?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? = src?.let { context?.serialize(it) }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Map<K, V>? = context?.deserialize(json, typeOfT)
}