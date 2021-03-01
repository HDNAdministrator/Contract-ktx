package pt.hdn.contract.adapters

import com.google.gson.*
import pt.hdn.contract.annotations.Parameter
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.schemas.*
import java.lang.reflect.Type

class SchemaTypeAdapter : JsonDeserializer<Schema> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Schema? {
        return json
            ?.let {
                with(it.asJsonObject) {
                    when(this[Parameter.ID].asInt) {
                        SchemaType.FIX -> FixSchema
                        SchemaType.RATE -> RateSchema
                        SchemaType.COMMISSION -> CommissionSchema
                        SchemaType.OBJECTIVE -> ObjectiveSchema
                        SchemaType.THRESHOLD -> ThresholdSchema
                        else -> null
                    }?.deserialize(this)
                }
            }
    }
}