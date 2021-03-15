package pt.hdn.contract.adapters

import android.util.Base64
import android.util.Base64.NO_WRAP
import com.google.gson.*
import java.lang.reflect.Type

class ByteArrayTypeAdapter : JsonSerializer<ByteArray>, JsonDeserializer<ByteArray> {
    override fun serialize(src: ByteArray?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? = src?.run { JsonPrimitive(Base64.encodeToString(this, NO_WRAP)) }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ByteArray? = json?.run { Base64.decode(asString, NO_WRAP) }
}