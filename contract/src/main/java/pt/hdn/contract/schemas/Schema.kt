package pt.hdn.contract.schemas

import com.google.gson.JsonObject
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType

interface Schema {
    @SourceType val source: Int
    @SchemaType val id: Int
    val isValid: Boolean

    interface Companion {
        fun deserialize(json: JsonObject): Schema
    }
}