package pt.hdn.contract.schemas

import com.google.gson.JsonObject

interface Serializer<T> {
    fun serialize(json: JsonObject): T
}