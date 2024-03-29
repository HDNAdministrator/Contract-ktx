package pt.hdn.contract.adapters

import com.google.gson.*
import pt.hdn.contract.annotations.Parameter.Companion.ID
import pt.hdn.contract.annotations.SchemaType.Companion.FIX
import pt.hdn.contract.annotations.SchemaType.Companion.RATE
import pt.hdn.contract.annotations.SchemaType.Companion.COMMISSION
import pt.hdn.contract.annotations.SchemaType.Companion.OBJECTIVE
import pt.hdn.contract.annotations.SchemaType.Companion.THRESHOLD
import pt.hdn.contract.schemas.*
import java.lang.reflect.Type

class SchemaTypeAdapter: JsonSerializer<Schema?>, JsonDeserializer<Schema?> {
    override fun serialize(src: Schema?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? = src?.let { context?.serialize(it) }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Schema? {
        return json
            ?.asJsonObject
            ?.run {
                when (this[ID].asInt) {
                    FIX -> FixSchema::class.java
                    RATE -> RateSchema::class.java
                    COMMISSION -> CommissionSchema::class.java
                    OBJECTIVE -> ObjectiveSchema::class.java
                    THRESHOLD -> ThresholdSchema::class.java
                    else -> null
                }?.let { context?.deserialize(this, it) }
            }
    }
}