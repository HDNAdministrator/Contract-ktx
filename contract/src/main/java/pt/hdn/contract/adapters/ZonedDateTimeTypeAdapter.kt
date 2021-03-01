package pt.hdn.contract.adapters

import com.google.gson.*
import java.lang.reflect.Type
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME
import java.time.format.DateTimeFormatterBuilder

class ZonedDateTimeTypeAdapter : JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

    //region vars
    private val hdnZonedDateTime = DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE_TIME)
        .appendOffset("+HH:MM", "+00:00")
        .optionalStart().appendLiteral('[').parseCaseSensitive().appendZoneRegionId().appendLiteral(']')
        .toFormatter()
    //endregion vars

    override fun serialize(src: ZonedDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? = src?.let { JsonPrimitive(it.format(hdnZonedDateTime)) }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ZonedDateTime? = json?.let { ZonedDateTime.parse(json.asString) }
}