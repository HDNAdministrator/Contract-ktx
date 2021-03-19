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

class SchemaTypeAdapter : JsonDeserializer<Schema> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Schema? {
        return json
            ?.let {
                with(it.asJsonObject) {
                    when(this[ID].asInt) {
                        FIX -> FixSchema
                        RATE -> RateSchema
                        COMMISSION -> CommissionSchema
                        OBJECTIVE -> ObjectiveSchema
                        THRESHOLD -> ThresholdSchema
                        else -> null
                    }?.deserialize(this)
                }
            }
    }
}