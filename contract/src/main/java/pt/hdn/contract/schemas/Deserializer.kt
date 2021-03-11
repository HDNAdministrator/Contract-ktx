package pt.hdn.contract.schemas

import com.google.gson.JsonObject

interface Deserializer<T> {
    fun deserialize(json: JsonObject): T
}